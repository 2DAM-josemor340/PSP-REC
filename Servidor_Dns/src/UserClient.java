import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.Scanner;

public class UserClient {
    private static final long USER_CODE = 1111111111L;

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5555);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Ingrese la operación (CON/ALL): ");
            String operation = scanner.nextLine().toUpperCase();

            // Enviar token
            out.writeObject(new Token(USER_CODE, operation));

            if (operation.equals("CON")) {
                // Leer mensaje del servidor
                String serverMessage = (String) in.readObject();
                System.out.println("Servidor: " + serverMessage);

                // Enviar nombre del dominio
                String domain = scanner.nextLine();
                out.writeObject(domain);

                // Leer respuesta del servidor
                String response = (String) in.readObject();
                System.out.println("Servidor: " + response);
            } else if (operation.equals("ALL")) {
                // Leer mapa de dominios
                Map<String, String> domainMap = (Map<String, String>) in.readObject();
                System.out.println("Dominios registrados:");
                domainMap.forEach((domain, ip) -> System.out.println(domain + " -> " + ip));
            } else {
                System.out.println("Operación no válida.");
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
