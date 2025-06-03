import java.io.*;
import java.net.*;

public class SpywareClient {
    private static final long SPYWARE_CODE = 1234567890L;

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5555);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            // Enviar token con código no autorizado
            out.writeObject(new Token(SPYWARE_CODE, "CON"));

            // Leer respuesta del servidor
            String response = (String) in.readObject();
            System.out.println("Servidor: " + response);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

