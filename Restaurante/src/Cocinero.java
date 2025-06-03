import java.util.concurrent.BlockingQueue;

public class Cocinero implements Runnable {
    private final int id;
    private final BlockingQueue<Pedido> colaCocina;
    private final BlockingQueue<Pedido> colaListos;

    public Cocinero(int id, BlockingQueue<Pedido> cocina, BlockingQueue<Pedido> listos) {
        this.id = id;
        this.colaCocina = cocina;
        this.colaListos = listos;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Pedido pedido = colaCocina.take();
                int tiempo = 3 + (int) (Math.random() * 6); // 3-8 segundos
                System.out.printf("👨‍🍳 Cocinero %d preparando %s durante %d segundos...\n", id, pedido, tiempo);
                Thread.sleep(tiempo * 1000);
                colaListos.put(pedido);
                System.out.printf("✅ Cocinero %d completó %s\n", id, pedido);
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.println("❌ Cocinero " + id + " ha terminado su turno.");
    }
}
