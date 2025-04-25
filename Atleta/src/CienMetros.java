
public class CienMetros {
    public static void main(String[] args) {


        Thread mains = Thread.currentThread();
        mains.setName("Carrera Legendaria");
        System.out.println("Inicio de carrera de 100m de nombre " + mains.getName());

        try {
            System.out.println("Preparados....");
            Thread.sleep(1000);
            System.out.println("Listos?");
            Thread.sleep(1000);
            System.out.println("¡Ya!");
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        Atleta[] atletas = {
                new Atleta(1, "Usain Bolt"),
                new Atleta(2, "Tyson Gay"),
                new Atleta(3, "Carl Lewis"),
                new Atleta(4, "Asafa Powell"),
                new Atleta(5, "Justin Gatlin")
        };
        for (Atleta atleta : atletas) {
            atleta.start();
        }

        for (Atleta atleta : atletas) {
            try {
                atleta.join();
                if (atleta.estaLesionado()) {
                    System.out.println("Carrera Legendaria: " + atleta.getNombre() + " ha quedado eliminado de la carrera.");
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        Atleta ganador = fotoFinish(atletas);
        if (ganador != null) {
            System.out.println("Tras la fotofinish, el ganador de la " + mains.getName() + " y MEDALLA DE ORO es: " + ganador.getNombre() + " con el dorsal " + ganador.getDorsal() + " con un tiempo de " + ganador.getTiempo() + " segundos");
        }

        System.out.println(mains.getName() + ": Fin de la carrera. Gracias por asistir a la " + mains.getName());
    }

    private static Atleta fotoFinish(Atleta[] atletas) {
        Atleta ganador = null;
        double mejorTiempo = Double.MAX_VALUE;

        for (Atleta atleta : atletas) {
            if (!atleta.estaLesionado() && atleta.getTiempo() < mejorTiempo) {
                mejorTiempo = atleta.getTiempo();
                ganador = atleta;
            }
        }
        return ganador;
    }
}
