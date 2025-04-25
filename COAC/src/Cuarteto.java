public class Cuarteto extends AgrupacionOficial implements Callejera {
    private int numMiembros;

    public Cuarteto(String nombre, String autor, String autorMusica, String autorLetras,
                    String tipoDisfraz, int numMiembros, int puntos) {
        super(nombre, autor, autorMusica, autorLetras, tipoDisfraz, puntos);
        this.numMiembros = numMiembros;
    }

    @Override
    public void cantar_la_presentacion() {
        System.out.println("Cantando la presentación del Cuarteto con nombre " + nombre);
    }

    @Override
    public void hacer_tipo() {
        System.out.println("El Cuarteto " + nombre + " va de " + tipoDisfraz);
    }

    @Override
    public void caminito_del_falla() {
        System.out.println("El cuarteto " + nombre + " va caminito del falla");
    }

    @Override
    public void amo_a_escucha() {
        System.out.println("Amo a escucha el Cuarteto " + nombre);
    }

    @Override
    public String toString() {
        return "Cuarteto: " + super.toString() +
                ", Número de miembros: " + numMiembros;
    }
}
