public class Chirigota extends AgrupacionOficial implements Callejera {
    private int numCuples;

    public Chirigota(String nombre, String autor, String autorMusica, String autorLetras,
                     String tipoDisfraz, int numCuples, int puntos) {
        super(nombre, autor, autorMusica, autorLetras, tipoDisfraz, puntos);
        this.numCuples = numCuples;
    }

    @Override
    public void cantar_la_presentacion() {
        System.out.println("Cantando la presentación de la Chirigota con nombre " + nombre);
    }

    @Override
    public void hacer_tipo() {
        System.out.println("La Chirigota " + nombre + " va de " + tipoDisfraz);
    }

    @Override
    public void caminito_del_falla() {
        System.out.println("La chirigota " + nombre + " va caminito del falla");
    }

    @Override
    public void amo_a_escucha() {
        System.out.println("Amo a escucha la Chirigota " + nombre);
    }

    @Override
    public String toString() {
        return "Chirigota: " + super.toString() +
                ", Número de cuplés: " + numCuples;
    }
}
