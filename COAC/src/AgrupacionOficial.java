import java.util.ArrayList;
import java.util.List;

public abstract class AgrupacionOficial extends Agrupacion {
    protected int puntos;
    protected List<Integrante> integrantes;

    public AgrupacionOficial(String nombre, String autor, String autorMusica, String autorLetras, String tipoDisfraz, int puntos) {
        super(nombre, autor, autorMusica, autorLetras, tipoDisfraz);
        this.puntos = puntos;
        this.integrantes = new ArrayList<>();
    }

    public abstract void caminito_del_falla();

    public void insertar_integrante(Integrante i) {
        integrantes.add(i);
    }

    public boolean eliminar_integrante(Integrante i) {
        return integrantes.remove(i);
    }

    public int getPuntos() {
        return puntos;
    }

    @Override
    public String toString() {
        return super.toString() + ", Puntos: " + puntos + ", Integrantes: " + integrantes.size();
    }
}
