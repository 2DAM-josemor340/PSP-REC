public class MailBox {
    public static void main(String[] args) {
        Buzon buzon = new Buzon();
        Escritor escritor = new Escritor(buzon, "Alejandro");
        Escritor escritor1 = new Escritor(buzon, "Ana");
        Escritor escritor2 = new Escritor(buzon, "Adrian");

        Lector lector = new Lector(buzon, "Dani");
        Lector lector1 = new Lector(buzon, "Chema");

        escritor.start();
        escritor1.start();
        escritor2.start();
        lector.start();
        lector1.start();
        lector.setPriority(Thread.MAX_PRIORITY);
        lector1.setPriority(Thread.MIN_PRIORITY);



    }
}