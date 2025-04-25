import java.util.Scanner;

public class Simulacion {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre del primer equipo: ");
        String nombre1 = sc.nextLine();
        System.out.println("Introduce el nombre del segundo equipo: ");
        String nombre2 = sc.nextLine();

        int victoria1, victoria2, partido;
        victoria1 = 0;
        victoria2 = 0;
        partido = 1;


        while (victoria1 < 2 && victoria2 < 2) {

            //Thread equipo1 = new Thread(new Equipo(nombre1));
            //Thread equipo2 = new Thread(new Equipo(nombre2));
            //equipo1.setName(nombre1);
            //equipo2.setName(nombre2);
            Equipo equipo1 = new Equipo(nombre1);
            Equipo equipo2 = new Equipo(nombre2);
            Thread hilo1 = new Thread(equipo1);
            Thread hilo2 = new Thread(equipo2);
            System.out.println("Comienza el partido entre " + equipo1.getNombre() + " y " + equipo2.getNombre() + "\n partido .: " + partido);

            hilo1.start();
            hilo2.start();

            for (int i = 1; i <= 4; i++) {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Cuarto " + (i) + ":");
                System.out.println("Puntuacion de " + equipo1.getNombre() + ": " + equipo1.getPuntuacion());
                System.out.println("Puntuacion de " + equipo2.getNombre() + ": " + equipo2.getPuntuacion());
            }

            try {
                hilo1.join();
                hilo2.join();

                partido++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (equipo1.getPuntuacion() > equipo2.getPuntuacion()) {
                System.out.println("El ganador es el " + equipo1.getNombre());
                victoria1++;
            } else if (equipo1.getPuntuacion() < equipo2.getPuntuacion()) {
                System.out.println("El ganador es el " + equipo2.getNombre());
                victoria2++;
            } else {
                System.out.println("Empate");
            }
        }
    }
}