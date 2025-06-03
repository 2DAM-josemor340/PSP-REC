import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Mesero implements Runnable {
    private final int id;
    private final BlockingQueue<Pedido> colaCocina;
    private final BlockingQueue<Pedido> colaListos;
    private final Ganancias ganancias;

    public Mesero(int id, BlockingQueue<Pedido> cocina, BlockingQueue<Pedido> listos, Ganancias ganancias) {
        this.id = id;
        this.colaCocina = cocina;
        this.colaListos = listos;
        this.ganancias = ganancias;
    }

    @Override
    public void run() {
        long ultimoPedido = System.currentTimeMillis();

        while (!Thread.currentThread().isInterrupted()) {
            try {
                // Entregar pedidos listos
                Pedido listo = colaListos.poll();
                if (listo != null) {
                    System.out.printf("🚶‍♂️ Mesero %d entregando %s...\n", id, listo);
                    Thread.sleep(1000);
                    ganancias.agregar(listo.getPrecio());
                    System.out.printf("💵 Mesero %d entregó %s. Ganancia: %.2f €\n", id, listo, listo.getPrecio());
                }

                // Cada 5-10 segundos, tomar un nuevo pedido
                long ahora = System.currentTimeMillis();
                if (ahora - ultimoPedido >= (5 + (int)(Math.random() * 6)) * 1000) {
                    Pedido nuevo = new Pedido();
                    colaCocina.put(nuevo);
                    System.out.printf("📝 Mesero %d tomó nuevo %s\n", id, nuevo);
                    ultimoPedido = ahora;
                }

                Thread.sleep(500); // Pequeña pausa para evitar CPU alta
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.println("❌ Mesero " + id + " ha terminado su turno.");
    }
}
