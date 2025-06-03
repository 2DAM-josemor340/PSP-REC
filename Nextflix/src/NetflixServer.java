import java.io.*;
import java.net.*;
import java.util.*;

public class NetflixServer {
    private static final int PORT = 6000;
    private static Set<String> cartelera = new HashSet<>();
    private static Map<String, Set<String>> vistas = new HashMap<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("NetflixServer escuchando en el puerto " + PORT);

            while (true) {
                try (Socket cliente = serverSocket.accept();
                     ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());
                     ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream())) {

                    System.out.println("Cliente conectado.");
                    boolean correcto = false;
                    String user = null;

                    for (int i = 0; i < 2; i++) {
                        Map<String, String> login = (Map<String, String>) in.readObject();
                        user = login.get("user");
                        String pass = login.get("pass");

                        if ((user.equals("ADMIN") && pass.equals("ADMIN")) ||
                                (user.equals("TuNombre") && pass.equals("USER"))) {
                            correcto = true;
                            break;
                        } else {
                            out.writeObject("Usuario y password incorrectos");
                        }
                    }

                    if (!correcto) {
                        System.out.println("Demasiados intentos fallidos. Cerrando servidor por seguridad.");
                        System.exit(1);
                    }

                    if (user.equals("ADMIN")) {
                        out.writeObject("Bienvenido a Netflix Administrador!!! ¿Qué acción desea realizar ALTA o BAJA??");
                        String accion = (String) in.readObject();
                        if (accion.equalsIgnoreCase("ALTA")) {
                            while (true) {
                                out.writeObject("Introduce el nombre de la película a registrar:");
                                String pelicula = (String) in.readObject();
                                if (cartelera.contains(pelicula)) {
                                    out.writeObject("La película ya estaba en la cartelera");
                                } else {
                                    cartelera.add(pelicula);
                                    out.writeObject("Película registrada correctamente");
                                }
                                out.writeObject("¿Desea registrar otra película? (SI/NO):");
                                String respuesta = (String) in.readObject();
                                if (respuesta.equalsIgnoreCase("NO")) break;
                            }
                        } else if (accion.equalsIgnoreCase("BAJA")) {
                            out.writeObject("Introduce el nombre de la película a eliminar:");
                            String pelicula = (String) in.readObject();
                            if (cartelera.remove(pelicula)) {
                                out.writeObject("Película eliminada correctamente");
                            } else {
                                out.writeObject("No se ha podido eliminar porque no existía");
                            }
                        } else {
                            out.writeObject("Operación no válida");
                        }
                    } else if (user.equals("TuNombre")) {
                        out.writeObject("Bienvenido a Netflix TuNombre ¿Qué acción desea realizar VER o BUSCAR?");
                        String accion = (String) in.readObject();
                        if (accion.equalsIgnoreCase("VER")) {
                            out.writeObject("Introduce el nombre de la película que deseas ver:");
                            String pelicula = (String) in.readObject();
                            if (cartelera.contains(pelicula)) {
                                vistas.computeIfAbsent(user, k -> new HashSet<>()).add(pelicula);
                                out.writeObject("Usted ha seleccionado para ver la película " + pelicula);
                            } else {
                                out.writeObject("ERROR: La película " + pelicula + " no está actualmente en cartelera");
                            }
                        } else if (accion.equalsIgnoreCase("BUSCAR")) {
                            Set<String> vistasUsuario = vistas.getOrDefault(user, new HashSet<>());
                            List<String> noVistas = new ArrayList<>();
                            List<String> yaVistas = new ArrayList<>();

                            for (String peli : cartelera) {
                                if (vistasUsuario.contains(peli)) {
                                    yaVistas.add(peli);
                                } else {
                                    noVistas.add(peli);
                                }
                            }
                            Collections.sort(noVistas);
                            Collections.sort(yaVistas);
                            List<String> listado = new ArrayList<>();
                            for (String p : noVistas) listado.add(p + " → No visualizada");
                            for (String p : yaVistas) listado.add(p + " → VISTA");
                            out.writeObject(listado);

                            out.writeObject("¿Desea ver alguna de ellas? Indica la película o escribe NO:");
                            String pelicula = (String) in.readObject();
                            if (!pelicula.equalsIgnoreCase("NO")) {
                                if (cartelera.contains(pelicula)) {
                                    vistas.computeIfAbsent(user, k -> new HashSet<>()).add(pelicula);
                                    out.writeObject("Usted ha seleccionado para ver la película " + pelicula);
                                } else {
                                    out.writeObject("ERROR: La película " + pelicula + " no está actualmente en cartelera");
                                }
                            }
                        } else {
                            out.writeObject("Operación no válida");
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Error en la conexión: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("No se puede abrir el puerto: " + e.getMessage());
        }
    }
}
