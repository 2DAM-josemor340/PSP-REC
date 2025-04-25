public class Comparsa extends AgrupacionOficial {
    private String empresaAtrezzo;

    public Comparsa(String nombre, String autor, String autorMusica, String autorLetras,
                    String tipoDisfraz, String empresaAtrezzo, int puntos) {
        super(nombre, autor, autorMusica, autorLetras, tipoDisfraz, puntos);
        this.empresaAtrezzo = empresaAtrezzo;
    }

    @Override
    public void cantar_la_presentacion() {
        System.out.println("Cantando la presentación de la Comparsa con nombre " + nombre);
    }

    @Override
    public void hacer_tipo() {
        System.out.println("La Comparsa " + nombre + " va de " + tipoDisfraz);
    }

    @Override
    public void caminito_del_falla() {
        System.out.println("La comparsa " + nombre + " va caminito del falla");
    }

    @Override
    public String toString() {
        return "Comparsa: " + super.toString() +
                ", Empresa de atrezzo: " + empresaAtrezzo;
    }
}
