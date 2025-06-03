import java.io.Serializable;

public class ClientIdentification implements Serializable {
    private int idCliente;
    private int idPartida;
    private char letra; // 'A' o 'B'
    private Tablero tablero;

    public ClientIdentification(int idCliente, int idPartida, Tablero tablero, char letra) {
        this.idCliente = idCliente;
        this.idPartida = idPartida;
        this.tablero = tablero;
        this.letra = letra;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdPartida() {
        return idPartida;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public char getLetra() {
        return letra;
    }
}
