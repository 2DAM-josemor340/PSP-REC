import java.util.*;
import java.util.concurrent.*;

public class RestauranteSimulacion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar cocineros y meseros
        int numCocineros = solicitarNumero(scanner, "cocineros", 2, 4);
        int numMeseros = solicitarNumero(scanner, "meseros", 3, 6);

        // Colas compartidas
        BlockingQueue<Pedido> colaCocina = new LinkedBlockingQueue<>();
        BlockingQueue<Pedido> colaListos = new LinkedBlockingQueue<>();

        Ganancias ganancias = new Ganancias();

        // Crear e iniciar cocineros
        List<Thread> hilos = new ArrayList<>();
        for (int i = 1; i <= numCocineros; i++) {
            Cocinero c = new Cocinero(i, colaCocina, colaListos);
            Thread t = new Thread(c);
            hilos.add(t);
            t.start();
        }

        // Crear e iniciar meseros
        for (int i = 1; i <= numMeseros; i++) {
            Mesero m = new Mesero(i, colaCocina, colaListos, ganancias);
            Thread t = new Thread(m);
            hilos.add(t);
            t.start();
        }

        long startTime = System.currentTimeMillis();
        long duration = 60_000;

        while (System.currentTimeMillis() - startTime < duration) {
            try {
                Thread.sleep(10_000); // cada 10 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("📝 Reporte parcial:");
            System.out.println("🍽️ Pedidos en cocina: " + colaCocina.size());
            System.out.println("✅ Pedidos listos: " + colaListos.size());
            System.out.printf("💰 Ganancias totales: %.2f €\n", ganancias.obtenerTotal());
            System.out.println("----------------------------");
        }

        // Finaliza la simulación
        for (Thread t : hilos) {
            t.interrupt();
        }

        System.out.println("\n📊 Estadísticas finales:");
        System.out.printf("💰 Ganancias totales: %.2f €\n", ganancias.obtenerTotal());
        System.out.println("🍽️ Pedidos restantes en cocina: " + colaCocina.size());
        System.out.println("✅ Pedidos listos sin entregar: " + colaListos.size());
    }

    private static int solicitarNumero(Scanner sc, String tipo, int min, int max) {
        int n;
        do {
            System.out.printf("Ingrese el número de %s (%d-%d): ", tipo, min, max);
            n = sc.nextInt();
        } while (n < min || n > max);
        return n;
    }
}
