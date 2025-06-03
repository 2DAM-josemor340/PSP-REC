import java.io.Serializable;
import java.util.Arrays;

public class Tablero implements Serializable {
    private static final int TAMANIO = 10;
    private char[][] casillas;
    private int barcosRestantes;

    public Tablero() {
        casillas = new char[TAMANIO][TAMANIO];
        for (char[] fila : casillas) {
            Arrays.fill(fila, '~'); // '~' representa agua
        }
        barcosRestantes = 0;
    }

    /**
     * Coloca un barco en el tablero.
     *
     * @param fila         fila inicial (0-9)
     * @param columna      columna inicial (0-9)
     * @param longitud     longitud del barco
     * @param horizontal   true para horizontal, false para vertical
     * @return true si se colocó correctamente, false si hubo conflicto
     */
    public boolean colocarBarco(int fila, int columna, int longitud, boolean horizontal) {
        if (!esValidaColocacion(fila, columna, longitud, horizontal)) return false;

        for (int i = 0; i < longitud; i++) {
            int f = fila + (horizontal ? 0 : i);
            int c = columna + (horizontal ? i : 0);
            casillas[f][c] = 'B';
        }

        barcosRestantes++;
        return true;
    }

    private boolean esValidaColocacion(int fila, int columna, int longitud, boolean horizontal) {
        for (int i = 0; i < longitud; i++) {
            int f = fila + (horizontal ? 0 : i);
            int c = columna + (horizontal ? i : 0);

            if (f >= TAMANIO || c >= TAMANIO || casillas[f][c] != '~') {
                return false;
            }
        }
        return true;
    }

    /**
     * Dispara a una casilla.
     *
     * @param fila    fila del disparo
     * @param columna columna del disparo
     * @return 'X' si acierto, 'O' si agua, '*' si ya disparado, '!' si hunde barco
     */
    public char disparar(int fila, int columna) {
        if (fila < 0 || fila >= TAMANIO || columna < 0 || columna >= TAMANIO) return ' ';
        char objetivo = casillas[fila][columna];

        switch (objetivo) {
            case 'B':
                casillas[fila][columna] = 'X'; // acierto
                if (barcoHundido(fila, columna)) {
                    barcosRestantes--;
                    return '!';
                }
                return 'X';
            case '~':
                casillas[fila][columna] = 'O'; // agua
                return 'O';
            case 'X':
            case 'O':
                return '*'; // ya disparado
            default:
                return ' ';
        }
    }

    private boolean barcoHundido(int fila, int columna) {
        // Buscar hacia todas las direcciones para comprobar si hay partes restantes del barco
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
        for (int[] d : dirs) {
            int f = fila + d[0], c = columna + d[1];
            while (f >= 0 && f < TAMANIO && c >= 0 && c < TAMANIO) {
                if (casillas[f][c] == 'B') return false;
                if (casillas[f][c] == '~' || casillas[f][c] == 'O') break;
                f += d[0];
                c += d[1];
            }
        }
        return true;
    }

    public boolean todosLosBarcosHundidos() {
        return barcosRestantes == 0;
    }

    public void imprimirTablero(boolean ocultarBarcos) {
        System.out.println("  A B C D E F G H I J");
        for (int i = 0; i < TAMANIO; i++) {
            System.out.printf("%d ", i);
            for (int j = 0; j < TAMANIO; j++) {
                char c = casillas[i][j];
                if (ocultarBarcos && c == 'B') {
                    System.out.print("~ ");
                } else {
                    System.out.print(c + " ");
                }
            }
            System.out.println();
        }
    }

    public char[][] getCasillas() {
        return casillas;
    }
}
