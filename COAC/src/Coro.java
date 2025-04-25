public class Coro extends AgrupacionOficial {
    private int numBandurrias;
    private int numGuitarras;

    public Coro(String nombre, String autor, String autorMusica, String autorLetras,
                String tipoDisfraz, int numBandurrias, int numGuitarras, int puntos) {
        super(nombre, autor, autorMusica, autorLetras, tipoDisfraz, puntos);
        this.numBandurrias = numBandurrias;
        this.numGuitarras = numGuitarras;
    }

    @Override
    public void cantar_la_presentacion() {
        System.out.println("Cantando la presentación del Coro con nombre " + nombre);
    }

    @Override
    public void hacer_tipo() {
        System.out.println("El Coro " + nombre + " va de " + tipoDisfraz);
    }

    @Override
    public void caminito_del_falla() {
        System.out.println("El coro " + nombre + " va caminito del falla");
    }

    @Override
    public String toString() {
        return "Coro: " + super.toString() +
                ", Bandurrias: " + numBandurrias +
                ", Guitarras: " + numGuitarras;
    }
}
