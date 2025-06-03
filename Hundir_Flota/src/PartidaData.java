public class PartidaData {
    private static int nextId = 1;

    private int idPartida;
    private ClientHandler jugadorA;
    private ClientHandler jugadorB;

    public PartidaData(ClientHandler jugadorA, ClientHandler jugadorB) {
        this.idPartida = nextId++;
        this.jugadorA = jugadorA;
        this.jugadorB = jugadorB;
    }

    public int getIdPartida() {
        return idPartida;
    }

    public ClientHandler getJugadorA() {
        return jugadorA;
    }

    public ClientHandler getJugadorB() {
        return jugadorB;
    }
}
