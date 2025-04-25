public class Main {
    public static void main(String[] args) {
        // Crear integrantes para probar la gestión
        Integrante i1 = new Integrante(1, "Juan", 30, "Cádiz");
        Integrante i2 = new Integrante(2, "María", 25, "Jerez");

        // Crear agrupaciones oficiales
        Coro coro = new Coro("Los Cantores", "Paco", "Luis", "Manuel", "Tradicional", 3, 2, 85);
        Comparsa comparsa = new Comparsa("La Comparsa", "Ana", "Roberto", "José", "Moderno", "Atrezzo S.A.", 90);
        Chirigota chirigota = new Chirigota("Los Chistes", "Carlos", "Fernando", "Miguel", "Disfraz cómico", 5, 95);
        Cuarteto cuarteto = new Cuarteto("Los Cuatro", "Elena", "Sergio", "Laura", "Elegante", 4, 88);

        // Insertar integrantes a alguna agrupación oficial
        coro.insertar_integrante(i1);
        coro.insertar_integrante(i2);

        // Crear agrupación no oficial
        Romancero romancero = new Romancero("El Romancero", "Rosa", "Antonio", "Carmen", "Épico", "Historia de Cádiz");

        // Probar métodos de presentación y tipo
        coro.cantar_la_presentacion();
        coro.hacer_tipo();

        comparsa.cantar_la_presentacion();
        comparsa.hacer_tipo();

        chirigota.cantar_la_presentacion();
        chirigota.hacer_tipo();
        chirigota.amo_a_escucha();

        cuarteto.cantar_la_presentacion();
        cuarteto.hacer_tipo();
        cuarteto.amo_a_escucha();

        romancero.cantar_la_presentacion();
        romancero.hacer_tipo();
        romancero.amo_a_escucha();

        // Probar caminito del falla en oficiales
        coro.caminito_del_falla();
        comparsa.caminito_del_falla();
        chirigota.caminito_del_falla();
        cuarteto.caminito_del_falla();

        // Crear COAC e inscribir agrupaciones oficiales
        COAC coac = new COAC();
        coac.inscribir_agrupacion(coro);
        coac.inscribir_agrupacion(comparsa);
        coac.inscribir_agrupacion(chirigota);
        coac.inscribir_agrupacion(cuarteto);

        System.out.println("\n--- Inscripciones en COAC ---");
        coac.mostrarInscripciones();

        // Ordenar por puntos
        coac.ordenar_por_puntos();
        System.out.println("\n--- Ordenados por puntos ---");
        coac.mostrarInscripciones();

        // Ordenar por nombre
        coac.ordenar_por_nombre();
        System.out.println("\n--- Ordenados por nombre ---");
        coac.mostrarInscripciones();

        // Ordenar por autor
        coac.ordenar_por_autor();
        System.out.println("\n--- Ordenados por autor ---");
        coac.mostrarInscripciones();

        // Mostrar número total de agrupaciones creadas (oficiales y no oficiales)
        System.out.println("\nTotal de Agrupaciones creadas: " + Agrupacion.getTotalAgrupaciones());
    }
}
