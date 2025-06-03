import java.io.*;
import java.net.Socket;

public class Jugador extends Thread {
    private String nombre;
    private Socket socket;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private Servidor servidor;

    public Jugador(Socket socket, String nombre, DataInputStream entrada, DataOutputStream salida, Servidor servidor) {
        this.socket = socket;
        this.nombre = nombre;
        this.entrada = entrada;
        this.salida = salida;
        this.servidor = servidor;
    }

    public String getNombre() {
        return nombre;
    }

    public DataOutputStream getSalida() {
        return salida;
    }

    public void run() {
        try {
            while (!servidor.juegoTerminado()) {
                synchronized (servidor) {
                    while (!servidor.esTurno(this) && !servidor.juegoTerminado()) {
                        servidor.wait();
                    }

                    if (servidor.juegoTerminado()) break;

                    salida.writeUTF("Tu turno, " + nombre + ". Introduce un número:");
                    int intento = entrada.readInt();
                    String resultado = servidor.comprobarNumero(intento, this);
                    salida.writeUTF(resultado);

                    servidor.siguienteTurno();
                    servidor.notifyAll();
                }
            }
            salida.writeUTF("Fin del juego.");
            socket.close();
        } catch (IOException | InterruptedException e) {
            System.out.println("Jugador desconectado: " + nombre);
        }
    }
}
