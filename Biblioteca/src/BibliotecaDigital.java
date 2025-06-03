import java.util.*;

public class BibliotecaDigital {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese número de usuarios (5-10): ");
        int numUsuarios = scanner.nextInt();
        System.out.print("Ingrese número de libros disponibles (15-25): ");
        int numLibros = scanner.nextInt();

        if (numUsuarios < 5 || numUsuarios > 10 || numLibros < 15 || numLibros > 25) {
            System.out.println("Valores fuera de rango. Terminando.");
            return;
        }

        GestorRecursos gestor = new GestorRecursos(numLibros);
        List<Usuario> usuarios = new ArrayList<>();

        for (int i = 0; i < numUsuarios; i++) {
            Usuario u = new Usuario(gestor, i + 1);
            usuarios.add(u);
            u.start();
        }

        long inicio = System.currentTimeMillis();
        long tiempoSimulacion = 80000; // 80 segundos
        long intervaloEstadisticas = 20000;

        while (System.currentTimeMillis() - inicio < tiempoSimulacion) {
            try {
                Thread.sleep(intervaloEstadisticas);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("\n--- Estadísticas parciales ---");
            System.out.println("Libros prestados: " + gestor.getLibrosPrestados());
            System.out.println("Usuarios en cola: " + gestor.getUsuariosEnCola());
            System.out.println("Multas generadas: " + gestor.getMultasGeneradas());
        }

        System.out.println("\n*** Biblioteca cerrada. Generando reporte final ***");
        System.out.println("Libros prestados: " + gestor.getLibrosPrestados());
        System.out.println("Multas totales: " + gestor.getMultasGeneradas());
        System.out.println("Usuarios en cola final: " + gestor.getUsuariosEnCola());
    }
}
