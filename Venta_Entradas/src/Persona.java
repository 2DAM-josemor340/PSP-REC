public class Persona extends Thread {
    private Taquilla taquilla;
    private int hiloID;
    private int entradas;

    public Persona(int hiloID, Taquilla taquilla, int entradas) {
        this.hiloID = hiloID;
        this.taquilla = taquilla;
        this.entradas = entradas;
    }

    @Override
    public void run() {
        // Esperar el turno
        taquilla.esperarTurno(hiloID);
        System.out.println("Persona " + hiloID + " está comprando " + entradas + " entradas");

        // Comprar entradas
        taquilla.finTurno(entradas);
    }
}
