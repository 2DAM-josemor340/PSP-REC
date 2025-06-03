import java.util.*;

public class ManejadorPartidas {
    private Queue<ClientHandler> colaJugadores = new LinkedList<>();
    private List<PartidaData> partidasActivas = new ArrayList<>();

    public synchronized void agregarJugador(ClientHandler jugador) {
        colaJugadores.add(jugador);
        if (colaJugadores.size() >= 2) {
            ClientHandler jugadorA = colaJugadores.poll();
            ClientHandler jugadorB = colaJugadores.poll();
            iniciarPartida(jugadorA, jugadorB);
        }
    }

    private void iniciarPartida(ClientHandler a, ClientHandler b) {
        PartidaData partida = new PartidaData(a, b);
        partidasActivas.add(partida);

        a.enviarIdentificacion(partida.getIdPartida(), 'A');
        b.enviarIdentificacion(partida.getIdPartida(), 'B');
    }
}
