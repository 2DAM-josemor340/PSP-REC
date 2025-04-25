public class Lector extends Thread{

    private Buzon buzon;
    private String nombre;

    public Lector(Buzon buzon, String nombre) {
        this.buzon = buzon;
        this.nombre = nombre;
    }

    public void run() {
        while (true) {
            System.out.println("Soy el Lector " + nombre + " y leo: "+buzon.leer());
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
