import java.io.Serializable;

public class Posicion implements Serializable {
    private int fila;
    private int columna;

    public Posicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Posicion other = (Posicion) obj;
        return fila == other.fila && columna == other.columna;
    }

    @Override
    public int hashCode() {
        return 31 * fila + columna;
    }
}