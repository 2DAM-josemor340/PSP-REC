public class Romancero extends Agrupacion implements Callejera {
    private String tematicaCartelon;

    public Romancero(String nombre, String autor, String autorMusica, String autorLetras,
                     String tipoDisfraz, String tematicaCartelon) {
        super(nombre, autor, autorMusica, autorLetras, tipoDisfraz);
        this.tematicaCartelon = tematicaCartelon;
    }

    @Override
    public void cantar_la_presentacion() {
        System.out.println("Cantando la presentación del Romancero con nombre " + nombre);
    }

    @Override
    public void hacer_tipo() {
        System.out.println("El Romancero " + nombre + " va de " + tipoDisfraz);
    }

    @Override
    public void amo_a_escucha() {
        System.out.println("Amo a escucha el Romancero " + nombre);
    }

    @Override
    public String toString() {
        return "Romancero: " + super.toString() +
                ", Temática del cartelón: " + tematicaCartelon;
    }
}
