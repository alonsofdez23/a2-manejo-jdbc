import modelo.entidad.Coche;
import modelo.persistencia.DaoCocheMySql;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DaoCocheMySql daoCoche = new DaoCocheMySql();
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            mostrarMenu();
            opcion = obtenerOpcion(scanner);

            switch (opcion) {
                case 1:
                    // TODO
                    break;
                case 2:
                    // TODO
                    break;
                case 3:
                    // TODO
                    break;
                case 4:
                    // TODO
                    break;
                case 5:
                    // TODO
                    break;
                case 6:
                    // TODO
                    System.out.println("¡Programa terminado!");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }

        } while (opcion != 6);
    }

    private static void mostrarMenu() {
        System.out.println("\n*** Menú ***");
        System.out.println("1. Añadir nuevo coche");
        System.out.println("2. Borrar coche por ID");
        System.out.println("3. Consultar coche por ID");
        System.out.println("4. Modificar coche por ID");
        System.out.println("5. Lista de coches");
        System.out.println("6. Terminar el programa");
    }

    private static int obtenerOpcion(Scanner scanner) {
        System.out.print("Seleccione una opción: ");
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Limpiar el buffer del scanner
            return -1;
        }
    }

//        Coche c = new Coche();
//        c.setId(1);
//        c.setMarca("Lamborghini");
//        c.setModelo("Aventador");
//        c.setAnyoFabricacion(2019);
//        c.setKm(13500);
//
//        DaoCocheMySql sql = new DaoCocheMySql();
//        sql.alta(c);
//    }
}
