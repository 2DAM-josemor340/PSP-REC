import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Barco implements Serializable {
    public enum TipoBarco { PORTAAVIONES, ACORAZADO, SUBMARINO, DESTRUCTOR, FRAGATA }
    public enum ResultadoImpacto { AGUA, TOCADO, HUNDIDO }

    private TipoBarco tipo;
    private int tamanio;
    private int vida;
    private List<Posicion> posiciones;

    public Barco(TipoBarco tipo) {
        this.tipo = tipo;
        this.tamanio = obtenerTamanio(tipo);
        this.vida = tamanio;
        this.posiciones = new ArrayList<>();
    }

    private int obtenerTamanio(TipoBarco tipo) {
        return switch (tipo) {
            case PORTAAVIONES -> 5;
            case ACORAZADO -> 4;
            case SUBMARINO -> 3;
            case DESTRUCTOR -> 2;
            case FRAGATA -> 1;
        };
    }

    public void addPosicion(Posicion p) {
        posiciones.add(p);
    }

    public boolean isHundido() {
        return vida <= 0;
    }

    public boolean isInPosicion(Posicion p) {
        return posiciones.contains(p);
    }

    public ResultadoImpacto checkImpacto(Posicion p) {
        if (isInPosicion(p)) {
            vida--;
            return isHundido() ? ResultadoImpacto.HUNDIDO : ResultadoImpacto.TOCADO;
        }
        return ResultadoImpacto.AGUA;
    }

    public List<Posicion> getPosiciones() {
        return posiciones;
    }
}