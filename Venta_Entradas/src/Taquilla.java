public class Taquilla {
    private int entradas;
    private int turno;

    public Taquilla(int entradas) {
        this.entradas = entradas;
        this.turno = 0;
    }

    public synchronized void esperarTurno(int id) {
        while (id != turno) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Persona " + id + " interrumpida");
            }
        }
    }

    public synchronized void finTurno(int cantidad) {
        if (entradas >= cantidad) {
            entradas -= cantidad;
            System.out.println("Persona " + turno + " ha comprado " + cantidad + " entradas. Entradas restantes: " + entradas);
        } else {
            System.out.println("Error: No hay suficientes entradas disponibles");
        }
        turno++;
        notifyAll();
    }
}
