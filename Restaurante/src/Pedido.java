import java.util.concurrent.atomic.AtomicInteger;

public class Pedido {
    private static final AtomicInteger contador = new AtomicInteger(1);
    private final int id;
    private final double precio;

    public Pedido() {
        this.id = contador.getAndIncrement();
        this.precio = Math.round((10 + Math.random() * 20) * 100.0) / 100.0; // entre 10€ y 30€
    }

    public int getId() {
        return id;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "Pedido #" + id + " (" + precio + "€)";
    }
}
