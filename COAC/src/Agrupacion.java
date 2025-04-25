public abstract class Agrupacion implements Comparable<Agrupacion> {
    protected String nombre;
    protected String autor;
    protected String autorMusica;
    protected String autorLetras;
    protected String tipoDisfraz;

    // contador global de agrupaciones
    private static int contadorAgrupaciones = 0;

    public Agrupacion(String nombre, String autor, String autorMusica, String autorLetras, String tipoDisfraz) {
        this.nombre = nombre;
        this.autor = autor;
        this.autorMusica = autorMusica;
        this.autorLetras = autorLetras;
        this.tipoDisfraz = tipoDisfraz;
        contadorAgrupaciones++;
    }

    public static int getTotalAgrupaciones() {
        return contadorAgrupaciones;
    }

    public abstract void cantar_la_presentacion();
    public abstract void hacer_tipo();

    // Ordenación natural: por nombre
    @Override
    public int compareTo(Agrupacion o) {
        return this.nombre.compareToIgnoreCase(o.nombre);
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Autor: " + autor +
                ", Autor de la música: " + autorMusica +
                ", Autor de las letras: " + autorLetras +
                ", Tipo (disfraz): " + tipoDisfraz;
    }

    // Getters necesarios para comparadores (si se requieren)
    public String getAutor() {
        return autor;
    }
}
