import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClienteJugador {

    private static final int PUERTO = 5555;
    private static final String SERVER = "localhost";

    public static void main(String[] args) {
        try {
            // 1. Creación del socket cliente
            Socket clientSocket = new Socket(SERVER, PUERTO);

            // 2. Flujo de entrada y salida del servidor
            DataInputStream flujoEntrada = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(clientSocket.getOutputStream());

            Scanner sc = new Scanner(System.in);
            boolean acertado = false;

            while (!acertado) {
                System.out.print("Introduce un número: ");
                int numero = sc.nextInt();

                // Enviar el número al servidor
                flujoSalida.writeInt(numero);

                // Recibir la respuesta del servidor
                String respuesta = flujoEntrada.readUTF();
                System.out.println(respuesta);

                // Si acertó, salir del bucle
                if (respuesta.equals("¡Felicidades! Has acertado el número") || respuesta.equals("Has agotado tus intentos.")) {
                    acertado = true;
                }
            }

            // 3. Cierre de conexiones
            flujoEntrada.close();
            flujoSalida.close();
            clientSocket.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
