import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {
    private static final int PUERTO = 5555;
    private List<Jugador> jugadores = new ArrayList<>();
    private int turno = 0;
    private int numeroSecreto;
    private boolean juegoTerminado = false;

    public static void main(String[] args) throws IOException {
        new Servidor().ejecutar();
    }

    public void ejecutar() throws IOException {
        ServerSocket serverSocket = new ServerSocket(PUERTO);
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el número de jugadores: ");
        int numJugadores = sc.nextInt();

        System.out.println("Esperando a " + numJugadores + " jugadores...");

        while (jugadores.size() < numJugadores) {
            Socket socket = serverSocket.accept();
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            String nombre = entrada.readUTF();
            Jugador jugador = new Jugador(socket, nombre, entrada, salida, this);
            jugadores.add(jugador);
            System.out.println("Jugador conectado: " + nombre);
        }

        numeroSecreto = new Random().nextInt(100) + 1;
        System.out.println("Número secreto generado: " + numeroSecreto);

        for (Jugador j : jugadores) {
            j.start();
        }
    }

    public synchronized boolean esTurno(Jugador j) {
        return jugadores.get(turno) == j;
    }

    public synchronized void siguienteTurno() {
        turno = (turno + 1) % jugadores.size();
    }

    public synchronized String comprobarNumero(int intento, Jugador j) {
        if (intento == numeroSecreto) {
            juegoTerminado = true;
            for (Jugador jugador : jugadores) {
                try {
                    if (jugador != j)
                        jugador.getSalida().writeUTF("¡" + j.getNombre() + " ha acertado el número!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return "¡Has acertado el número!";
        } else if (intento < numeroSecreto) {
            return "El número es mayor.";
        } else {
            return "El número es menor.";
        }
    }

    public synchronized boolean juegoTerminado() {
        return juegoTerminado;
    }
}
