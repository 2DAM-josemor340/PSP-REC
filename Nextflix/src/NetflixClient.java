import java.io.*;
import java.net.*;
import java.util.*;

public class NetflixClient {
    private static final String HOST = "localhost";
    private static final int PORT = 6000;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            boolean loginCorrecto = false;
            for (int i = 0; i < 2; i++) {
                System.out.print("Usuario: ");
                String user = scanner.nextLine();
                System.out.print("Password: ");
                String pass = scanner.nextLine();

                Map<String, String> login = new HashMap<>();
                login.put("user", user);
                login.put("pass", pass);
                out.writeObject(login);

                String respuesta = (String) in.readObject();
                System.out.println(respuesta);
                if (!respuesta.equals("Usuario y password incorrectos")) {
                    loginCorrecto = true;
                    break;
                }
            }

            if (!loginCorrecto) {
                System.out.println("Demasiados intentos fallidos. Finalizando cliente.");
                return;
            }

            String mensaje = (String) in.readObject();
            System.out.println(mensaje);
            String accion = scanner.nextLine();
            out.writeObject(accion);

            if (accion.equalsIgnoreCase("ALTA")) {
                while (true) {
                    String solicitud = (String) in.readObject();
                    System.out.println(solicitud);
                    String pelicula = scanner.nextLine();
                    out.writeObject(pelicula);

                    String respuesta = (String) in.readObject();
                    System.out.println(respuesta);

                    String continuar = (String) in.readObject();
                    System.out.println(continuar);
                    String respuestaUsuario = scanner.nextLine();
                    out.writeObject(respuestaUsuario);
                    if (respuestaUsuario.equalsIgnoreCase("NO")) break;
                }
            } else if (accion.equalsIgnoreCase("BAJA")) {
                String solicitud = (String) in.readObject();
                System.out.println(solicitud);
                String pelicula = scanner.nextLine();
                out.writeObject(pelicula);

                String respuesta = (String) in.readObject();
                System.out.println(respuesta);
            } else if (accion.equalsIgnoreCase("VER")) {
                String solicitud = (String) in.readObject();
                System.out.println(solicitud);
                String pelicula = scanner.nextLine();
                out.writeObject(pelicula);

                String respuesta = (String) in.readObject();
                System.out.println(respuesta);
            } else if (accion.equalsIgnoreCase("BUSCAR")) {
                List<String> listado = (List<String>) in.readObject();
                for (String peli : listado) {
                    System.out.println(peli);
                }

                String pregunta = (String) in.readObject();
                System.out.println(pregunta);
                String respuestaUsuario = scanner.nextLine();
                out.writeObject(respuestaUsuario);

                if (!respuestaUsuario.equalsIgnoreCase("NO")) {
                    String respuesta = (String) in.readObject();
                    System.out.println(respuesta);
                }
            } else {
                String respuesta = (String) in.readObject();
                System.out.println(respuesta);
            }

        } catch (Exception e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
