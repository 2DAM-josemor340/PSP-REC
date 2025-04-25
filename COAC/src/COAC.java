import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class COAC {
    private List<AgrupacionOficial> inscripciones;

    public COAC() {
        inscripciones = new ArrayList<>();
    }

    public void inscribir_agrupacion(AgrupacionOficial agrupacion) {
        inscripciones.add(agrupacion);
    }

    public boolean eliminar_agrupacion(AgrupacionOficial agrupacion) {
        return inscripciones.remove(agrupacion);
    }

    public void ordenar_por_puntos() {
        Collections.sort(inscripciones, new Comparator<AgrupacionOficial>() {
            @Override
            public int compare(AgrupacionOficial o1, AgrupacionOficial o2) {
                return Integer.compare(o2.getPuntos(), o1.getPuntos()); // mayor a menor
            }
        });
    }

    public void ordenar_por_nombre() {
        Collections.sort(inscripciones);
    }

    public void ordenar_por_autor() {
        Collections.sort(inscripciones, new Comparator<AgrupacionOficial>() {
            @Override
            public int compare(AgrupacionOficial o1, AgrupacionOficial o2) {
                return o1.getAutor().compareToIgnoreCase(o2.getAutor());
            }
        });
    }

    public void mostrarInscripciones() {
        for (AgrupacionOficial a : inscripciones) {
            System.out.println(a);
        }
    }
}
