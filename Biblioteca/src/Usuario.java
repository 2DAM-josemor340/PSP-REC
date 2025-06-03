public class Usuario extends Thread {
    private final GestorRecursos gestor;
    private final int id;
    private final int librosALeer;

    public Usuario(GestorRecursos gestor, int id) {
        this.gestor = gestor;
        this.id = id;
        this.librosALeer = 3 + new Random().nextInt(3); // entre 3 y 5
    }

    @Override
    public void run() {
        int librosLeidos = 0;
        while (librosLeidos < librosALeer) {
            boolean prestado = gestor.prestarLibro(this);
            if (prestado) {
                try {
                    int lectura = 8000 + new Random().nextInt(8000); // entre 8 y 15 s
                    Thread.sleep(lectura);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                gestor.devolverLibro(this);
                librosLeidos++;
            } else {
                try {
                    Thread.sleep(10000); // espera hasta 10 segundos
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                gestor.retirarDeCola(this);
                break; // cancela si no hay libros
            }
        }
    }
}
