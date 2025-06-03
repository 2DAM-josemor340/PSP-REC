public class Ganancias {
    private double total = 0.0;

    public synchronized void agregar(double cantidad) {
        total += cantidad;
    }

    public synchronized double obtenerTotal() {
        return total;
    }
}
