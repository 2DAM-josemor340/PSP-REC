import java.io.*;
import java.net.*;
import java.util.Scanner;

public class AdminClient {
    private static final long ADMIN_CODE = 9999999999L;

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5555);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Ingrese la operación (REG/DEL): ");
            String operation = scanner.nextLine().toUpperCase();

            // Enviar token
            out.writeObject(new Token(ADMIN_CODE, operation));

            // Leer mensaje del servidor
            String serverMessage = (String) in.readObject();
            System.out.println("Servidor: " + serverMessage);

            // Enviar nombre del dominio
            String domain = scanner.nextLine();
            out.writeObject(domain);

            // Leer respuesta del servidor
            String response = (String) in.readObject();
            System.out.println("Servidor: " + response);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
