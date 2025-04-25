import java.util.Random;
import java.util.Scanner;

public class OrdenHilos {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Entradas hay disponibles: ");
        int numEntradas = sc.nextInt();
        System.out.println("Dime el numero de personas: ");
        int numPersonas = sc.nextInt();

        Taquilla taquilla = new Taquilla(numEntradas);

        for (int i = 0; i < numPersonas; i++) {
            int entradasAComprar = aleatorio(1, 5);
            Persona persona = new Persona(i, taquilla, entradasAComprar);
            persona.start();
        }

    }

    private static int aleatorio(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
