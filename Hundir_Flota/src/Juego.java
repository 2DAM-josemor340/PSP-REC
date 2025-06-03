import java.util.Scanner;

public class Juego {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Tablero jugador = new Tablero();
        Tablero enemigo = new Tablero();

        // Colocar barcos manualmente o automáticamente
        colocarBarcosJugador(jugador, scanner);
        colocarBarcosIA(enemigo); // Coloca automáticamente para IA

        System.out.println("¡Comienza el juego!");

        while (!enemigo.todosLosBarcosHundidos()) {
            System.out.println("\nTu tablero:");
            jugador.imprimirTablero(false);

            System.out.println("\nTablero enemigo:");
            enemigo.imprimirTablero(true);

            System.out.print("\nIngresa coordenada para disparar (ej. B3): ");
            String input = scanner.nextLine().toUpperCase();

            if (!input.matches("^[A-J](10|[0-9])$")) {
                System.out.println("Formato inválido. Usa A0 - J9.");
                continue;
            }

            int col = input.charAt(0) - 'A';
            int fila = Integer.parseInt(input.substring(1));

            char resultado = enemigo.disparar(fila, col);
            switch (resultado) {
                case 'X': System.out.println("¡Tocado!"); break;
                case '!': System.out.println("¡Tocado y hundido!"); break;
                case 'O': System.out.println("Agua."); break;
                case '*': System.out.println("Ya has disparado ahí."); break;
                default: System.out.println("Coordenada no válida."); break;
            }
        }

        System.out.println("\n¡Felicidades! Has hundido todos los barcos enemigos.");
        scanner.close();
    }

    private static void colocarBarcosJugador(Tablero tablero, Scanner scanner) {
        System.out.println("Coloca tus barcos.");
        int[] tamaños = {5, 4, 3, 3, 2}; // típico en Batalla Naval

        for (int t : tamaños) {
            boolean colocado = false;
            while (!colocado) {
                System.out.printf("Barco de longitud %d (ej. B2 H): ", t);
                String entrada = scanner.nextLine().toUpperCase();
                if (!entrada.matches("^[A-J](10|[0-9]) [HV]$")) {
                    System.out.println("Formato incorrecto. Usa A0 H o B3 V.");
                    continue;
                }

                String[] partes = entrada.split(" ");
                int col = partes[0].charAt(0) - 'A';
                int fila = Integer.parseInt(partes[0].substring(1));
                boolean horizontal = partes[1].equals("H");

                colocado = tablero.colocarBarco(fila, col, t, horizontal);
                if (!colocado) {
                    System.out.println("No se puede colocar ahí. Intenta otra posición.");
                } else {
                    tablero.imprimirTablero(false);
                }
            }
        }
    }

    private static void colocarBarcosIA(Tablero tablero) {
        int[] tamaños = {5, 4, 3, 3, 2};
        for (int t : tamaños) {
            boolean colocado = false;
            while (!colocado) {
                int fila = (int) (Math.random() * 10);
                int col = (int) (Math.random() * 10);
                boolean horizontal = Math.random() < 0.5;
                colocado = tablero.colocarBarco(fila, col, t, horizontal);
            }
        }
    }
}
