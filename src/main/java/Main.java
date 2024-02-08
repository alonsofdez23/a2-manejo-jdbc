import modelo.entidad.Coche;
import modelo.persistencia.DaoCocheMySql;

import java.util.InputMismatchException;
import java.util.List;
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
                    agregarCoche(daoCoche, scanner);
                    break;
                case 2:
                    borrarCoche(daoCoche, scanner);
                    break;
                case 3:
                    consultarCoche(daoCoche, scanner);
                    break;
                case 4:
                    modificarCoche(daoCoche, scanner);
                    break;
                case 5:
                    listarCoches(daoCoche);
                    break;
                case 6:
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

    public static void agregarCoche(DaoCocheMySql daoCoche, Scanner scanner) {
        System.out.println("Introduzca los datos del nuevo coche:");

        String marca;
        String modelo;
        int anyoFabricacion;
        int km;

        scanner.nextLine();
        System.out.println("Introduce la marca del coche:");
        marca = scanner.nextLine();
        System.out.println("Introduce el modelo del coche:");
        modelo = scanner.nextLine();
        System.out.println("Introduce el año de fabricación del coche:");
        anyoFabricacion = scanner.nextInt();
        System.out.println("Introduce los km del coche:");
        km = scanner.nextInt();

        Coche nuevoCoche = new Coche();
        nuevoCoche.setMarca(marca);
        nuevoCoche.setModelo(modelo);
        nuevoCoche.setAnyoFabricacion(anyoFabricacion);
        nuevoCoche.setKm(km);

        boolean query = daoCoche.alta(nuevoCoche);

        if (!query) {
            System.out.println("Error al agregar nuevo coche");
        } else {
            System.out.println("Coche añadido correctamente");
        }
    }

    public static void borrarCoche(DaoCocheMySql daoCoche, Scanner scanner) {
        System.out.println("Inserte el ID del coche que desea borrar: ");
        int idBorrar = scanner.nextInt();

        boolean query = daoCoche.borrar(idBorrar);

        if (!query) {
            System.out.println("Error al borrar coche con ID " + idBorrar);
        } else {
            System.out.println("Coche con ID " + idBorrar + " borrado correctamente");
        }
    }

    public static void consultarCoche(DaoCocheMySql daoCoche, Scanner scanner) {
        System.out.println("Inserte el ID del coche que desea consultar: ");
        int idConsultar = scanner.nextInt();

        Coche cocheQuery = daoCoche.obtener(idConsultar);

        if (cocheQuery == null) {
            System.out.println("No existe coche con el ID " + idConsultar);
        } else {
            System.out.println(cocheQuery);
        }
    }

    public static void modificarCoche(DaoCocheMySql daoCoche, Scanner scanner) {
        System.out.println("Inserte el ID del coche que desea modificar: ");
        int idModificar = scanner.nextInt();

        Coche cocheQuery = daoCoche.obtener(idModificar);

        if (cocheQuery == null) {
            System.out.println("No existe coche con el ID " + idModificar);
        } else {
            System.out.println(cocheQuery);

            String marca;
            String modelo;
            int anyoFabricacion;
            int km;

            scanner.nextLine();
            System.out.println("Introduce la nueva marca del coche:");
            marca = scanner.nextLine();
            System.out.println("Introduce el nuevo modelo del coche:");
            modelo = scanner.nextLine();
            System.out.println("Introduce el nuevo año de fabricación del coche:");
            anyoFabricacion = scanner.nextInt();
            System.out.println("Introduce los nuevos km del coche:");
            km = scanner.nextInt();

            Coche cocheModificado = new Coche();
            cocheModificado.setId(idModificar);
            cocheModificado.setMarca(marca);
            cocheModificado.setModelo(modelo);
            cocheModificado.setAnyoFabricacion(anyoFabricacion);
            cocheModificado.setKm(km);

            daoCoche.modificar(cocheModificado);

            System.out.println("Coche con ID " + idModificar + " modificado correctamente");
        }
    }

    private static void listarCoches(DaoCocheMySql daoCoche) {
        List<Coche> listaCoches = daoCoche.listar();

        if (listaCoches.isEmpty()) {
            System.out.println("No existe ningún coche");
        } else {
            for (Coche coche :
                    listaCoches) {
                System.out.println(coche);
            }
        }
    }
}
