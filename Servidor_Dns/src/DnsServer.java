import java.io.*;
import java.net.*;
import java.util.*;


public class DnsServer {
    private static final int PORT = 5555;
    private static final String IP_PREFIX = "192.168.0.";
    private static final int MAX_IPS = 14;
    private static final long ADMIN_CODE = 9999999999L;
    private static final long USER_CODE = 1111111111L;
    private static Map<String, String> domainIpMap = new HashMap<>();
    private static Set<String> usedIps = new HashSet<>();
    private static int suspiciousCount = 0;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("DNS Server listening on port " + PORT);
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                     ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {

                    Token token = (Token) in.readObject();
                    if (token.clientCode == ADMIN_CODE) {
                        handleAdminClient(token.operation, in, out);
                        suspiciousCount = 0;
                    } else if (token.clientCode == USER_CODE) {
                        handleUserClient(token.operation, in, out);
                        suspiciousCount = 0;
                    } else {
                        out.writeObject("Atención!! Usted no es un cliente autorizado para el uso del servidor");
                        suspiciousCount++;
                        if (suspiciousCount >= 3) {
                            System.out.println("ATTACK DETECTED!!! Se ha detectado un posible ataque al servidor con lo que se procederá a su finalización");
                            break;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleAdminClient(String operation, ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException {
        if (operation.equalsIgnoreCase("REG")) {
            out.writeObject("Ingrese el nombre del dominio a registrar:");
            String domain = ((String) in.readObject()).toLowerCase();
            if (domainIpMap.containsKey(domain)) {
                out.writeObject("El dominio ya está registrado.");
                return;
            }
            if (usedIps.size() >= MAX_IPS) {
                out.writeObject("No hay IPs disponibles para registrar el dominio.");
                return;
            }
            for (int i = 1; i <= MAX_IPS; i++) {
                String ip = IP_PREFIX + i;
                if (!usedIps.contains(ip)) {
                    domainIpMap.put(domain, ip);
                    usedIps.add(ip);
                    out.writeObject("Dominio registrado con IP: " + ip);
                    return;
                }
            }
        } else if (operation.equalsIgnoreCase("DEL")) {
            out.writeObject("Ingrese el nombre del dominio a eliminar:");
            String domain = ((String) in.readObject()).toLowerCase();
            if (domainIpMap.containsKey(domain)) {
                String ip = domainIpMap.remove(domain);
                usedIps.remove(ip);
                out.writeObject("Dominio eliminado correctamente.");
            } else {
                out.writeObject("El dominio no existe.");
            }
        } else {
            out.writeObject("Operación no reconocida.");
        }
    }

    private static void handleUserClient(String operation, ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException {
        if (operation.equalsIgnoreCase("CON")) {
            out.writeObject("Ingrese el nombre del dominio a conectar:");
            String domain = ((String) in.readObject()).toLowerCase();
            if (domainIpMap.containsKey(domain)) {
                out.writeObject("Conexión correcta a " + domain + " y en la dirección IP: " + domainIpMap.get(domain));
            } else {
                out.writeObject("ERROR HTTP 404 (Page Not Found): " + domain + " no encontrado en el servidor DNS");
            }
        } else if (operation.equalsIgnoreCase("ALL")) {
            TreeMap<String, String> sorted = new TreeMap<>(domainIpMap);
            out.writeObject(sorted);
        } else {
            out.writeObject("Operación no reconocida.");
        }
    }
}
