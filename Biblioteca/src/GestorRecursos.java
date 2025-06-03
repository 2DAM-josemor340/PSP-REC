import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class GestorRecursos {
    private int librosDisponibles;
    private final Queue<Usuario> colaEspera = new LinkedList<>();
    private final Map<Usuario, List<Long>> prestamos = new ConcurrentHashMap<>();
    private final AtomicInteger multasGeneradas = new AtomicInteger();
    private final AtomicInteger librosPrestados = new AtomicInteger();

    public GestorRecursos(int librosIniciales) {
        this.librosDisponibles = librosIniciales;
    }

    public synchronized boolean prestarLibro(Usuario usuario) {
        if (librosDisponibles > 0) {
            librosDisponibles--;
            librosPrestados.incrementAndGet();
            prestamos.computeIfAbsent(usuario, k -> new ArrayList<>()).add(System.currentTimeMillis());
            return true;
        } else {
            colaEspera.add(usuario);
            return false;
        }
    }

    public synchronized void devolverLibro(Usuario usuario) {
        List<Long> tiempos = prestamos.getOrDefault(usuario, new ArrayList<>());
        if (!tiempos.isEmpty()) {
            long tiempoPrestamo = System.currentTimeMillis() - tiempos.remove(0);
            if (tiempoPrestamo > 12000) {
                multasGeneradas.incrementAndGet();
            }
            librosDisponibles++;
        }
    }

    public synchronized int getLibrosPrestados() {
        return librosPrestados.get();
    }

    public synchronized int getUsuariosEnCola() {
        return colaEspera.size();
    }

    public synchronized int getMultasGeneradas() {
        return multasGeneradas.get();
    }

    public synchronized void retirarDeCola(Usuario u) {
        colaEspera.remove(u);
    }
}
