public class Escritor extends Thread {

    private Buzon buzon;
    private String nombre;

    public Escritor(Buzon buzon, String nombre) {
        this.buzon = buzon;
        this.nombre = nombre;

    }

    public void run() {
        int contador = 0;
        while (true) {
            System.out.println("Soy el Escritor " + nombre + " y escribo el mensaje numero " + contador);
            buzon.escribir("msg " + contador);
            try {
                Thread.sleep(1);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            contador++;
        }
    }
}
