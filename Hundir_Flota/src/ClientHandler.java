import java.io.*;
import java.net.*;

public class ClientHandler extends Thread {
    private static int nextId = 1;
    private int idCliente;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Tablero tablero;

    private ManejadorPartidas manejador;

    public ClientHandler(Socket socket, ManejadorPartidas manejador) {
        this.socket = socket;
        this.manejador = manejador;
        this.idCliente = nextId++;
        this.tablero = new Tablero(); // tablero vacío o con barcos
    }

    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            manejador.agregarJugador(this);

            // aquí manejar jugadas, etc.

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enviarIdentificacion(int idPartida, char letra) {
        try {
            ClientIdentification id = new ClientIdentification(idCliente, idPartida, tablero, letra);
            out.writeObject(id);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
