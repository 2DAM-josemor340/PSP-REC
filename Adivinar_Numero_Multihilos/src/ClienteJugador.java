import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClienteJugador {
    private static final int PUERTO = 5555;
    private static final String SERVER = "localhost";

    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket(SERVER, PUERTO);

            DataInputStream flujoEntrada = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(clientSocket.getOutputStream());

            Scanner sc = new Scanner(System.in);
            System.out.print("Introduce tu nombre: ");
            String nombre = sc.nextLine();

            flujoSalida.writeUTF(nombre);

            boolean terminado = false;

            while (!terminado) {
                String mensaje = flujoEntrada.readUTF();
                if (mensaje.equals("Fin del juego.")) {
                    System.out.println("El servidor ha terminado el juego.");
                    terminado = true;
                } else if (mensaje.startsWith("Tu turno")) {
                    System.out.println(mensaje);
                    int numero = sc.nextInt();
                    flujoSalida.writeInt(numero);
                } else {
                    System.out.println(mensaje);
                }
            }

            flujoEntrada.close();
            flujoSalida.close();
            clientSocket.close();

        } catch (IOException e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}
