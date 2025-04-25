import java.util.Random;

public class Atleta extends Thread {

    private int dorsal;
    private String nombre;
    private double tiempo;
    private boolean lesionado = false;


    public Atleta(int dorsal, String nombre) {
        this.dorsal = dorsal;
        this.nombre = nombre;
        this.tiempo = 8 + new Random().nextDouble() * 3.5;//11.5 - 8 = 3.5
    }

    public int getDorsal() {
        return dorsal;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean estaLesionado() {
        return lesionado;
    }

    public double getTiempo() {
        return tiempo;
    }

    public void run() {

        try {
            if (new Random().nextInt(5) == 0) {
                lesionado = true;
                Thread.sleep(2000);
                System.out.println( nombre + " grita: Ahhh!!! Me ha dado un tirón");
                //System.out.println("Carrera Legendaria: " + nombre + " ha quedado eliminado de la carrera.");
                return;
            }
            Thread.sleep((long) (tiempo * 1000));
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("El atleta: " + nombre + " con el dorsal " + dorsal + " ha finalizado la carrera con un tiempo de: " + tiempo + " segundos");
    }
}


