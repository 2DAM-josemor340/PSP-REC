import java.util.Random;

public class Equipo implements Runnable {

    private String nombre;
    private int puntuacion;

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.puntuacion = 0;
    }
    public String getNombre() {
        return nombre;
    }
    public int getPuntuacion() {
        return puntuacion;
    }

    @Override
    public void run() {
        for (int i = 0; i < 60; i++) {
            int puntos = puntuacionAleatoria(0, 3);
            puntuacion += puntos;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static int puntuacionAleatoria(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
