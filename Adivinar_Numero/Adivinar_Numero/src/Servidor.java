import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Servidor {
    private static final int PUERTO = 5555;
    private static final int NJUGADAS = 10;
    private static final int LIMITE_MIN = 1;
    private static final int LIMITE_MAX = 100;

    public static void main(String[] args) {
        try {
            // 1. Creación del socket servidor (puerto localhost)
            ServerSocket serverSocket = new ServerSocket(PUERTO);

            // 2. Espera/escucha del cliente (LISTEN) y aceptación de conexiones
            System.out.println("SERVIDOR: Escuchando por el puerto " + PUERTO);
            Socket clientSocket = serverSocket.accept();

            // 3. Flujo de entrada del cliente (recepción del mensaje)
            DataInputStream flujoEntrada = new DataInputStream(clientSocket.getInputStream());

            // 4. Flujo de salida del servidor (envío del mensaje)
            DataOutputStream flujoSalida = new DataOutputStream(clientSocket.getOutputStream());

            // 5. Generar un número aleatorio
            int numeroAleatorio = numeroAleatoria(LIMITE_MIN, LIMITE_MAX);
            System.out.println("El número aleatorio es: " + numeroAleatorio);

            boolean acierto = false;
            int nIntentos = 0;

            // 5. Interacción con el cliente
            while (!acierto) {
                // Recibir el intento del cliente
                int numero = flujoEntrada.readInt();
                System.out.println("Recibido: " + numero);

                // Comparar el intento con el número aleatorio
                if (numero < numeroAleatorio) {
                    // Incrementar el número de intentos
                    nIntentos++;
                    if (nIntentos == NJUGADAS) {
                        flujoSalida.writeUTF("Has agotado tus intentos.");
                    }else{
                        flujoSalida.writeUTF("El número es mayor");
                    }
                } else if (numero > numeroAleatorio) {
                    // Incrementar el número de intentos
                    nIntentos++;
                    if (nIntentos == NJUGADAS) {
                        flujoSalida.writeUTF("Has agotado tus intentos.");
                    } else {
                        flujoSalida.writeUTF("El número es menor");
                    }
                } else {
                    flujoSalida.writeUTF("¡Felicidades! Has acertado el número");
                    acierto = true;
                }
            }

            // 6. Cerrar los flujos y la conexión
            flujoEntrada.close();
            flujoSalida.close();
            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    private static int numeroAleatoria(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
