package vista;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.negocio.GestorCoche;
import modelo.persistencia.DaoCocheMySql;
import modelo.persistencia.DaoPasajeroMySql;
import modelo.persistencia.interfaces.DaoCoche;
import modelo.persistencia.interfaces.DaoPasajero;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    /**
     * Método principal que inicia la aplicación y maneja las opciones del menú principal.
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        DaoCocheMySql daoCoche = new DaoCocheMySql();
        GestorCoche gestorCoche = new GestorCoche();
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            mostrarMenu();
            opcion = obtenerOpcion(scanner);

            switch (opcion) {
                case 1:
                    agregarCoche(gestorCoche, scanner);
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
                    gestionPasajeros(daoCoche, scanner);
                case 7:
                    System.out.println("¡Programa terminado!");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }

        } while (opcion != 7);
    }

    private static void mostrarMenu() {
        System.out.println("\n*** Menú ***");
        System.out.println("1. Añadir nuevo coche");
        System.out.println("2. Borrar coche por ID");
        System.out.println("3. Consultar coche por ID");
        System.out.println("4. Modificar coche por ID");
        System.out.println("5. Lista de coches");
        System.out.println("6. Gestión de pasajeros");
        System.out.println("7. Terminar el programa");
    }

    private static void gestionPasajeros(DaoCocheMySql daoCoche, Scanner scanner) {
        DaoPasajero daoPasajero = new DaoPasajeroMySql();

        while (true) {
            System.out.println("\n*** Gestión de pasajeros ***");
            System.out.println("1. Crear nuevo pasajero");
            System.out.println("2. Borrar pasajero por ID");
            System.out.println("3. Consultar pasajero por ID");
            System.out.println("4. Listar todos los pasajeros");
            System.out.println("5. Añadir pasajero a coche");
            System.out.println("6. Eliminar pasajero de un coche");
            System.out.println("7. Listar todos los pasajeros de un coche");
            System.out.println("8. Atrás");

            int opcionPasajero = scanner.nextInt();
            scanner.nextLine();

            switch (opcionPasajero) {
                case 1:
                    agregarPasajero(daoPasajero, scanner);
                    break;
                case 2:
                    borrarPasajero(daoPasajero, scanner);
                    break;
                case 3:
                    consultarPasajero(daoPasajero, scanner);
                    break;
                case 4:
                    listarPasajeros(daoPasajero);
                    break;
                case 5:
                    agregarPasajeroACoche(daoCoche, daoPasajero, scanner);
                    break;
                case 6:
                    borrarPasajeroDeCoche(daoCoche, daoPasajero, scanner);
                    break;
                case 7:
                    listarPasajerosDeCoche(daoPasajero, scanner);
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }

    /**
     * Agrega un nuevo pasajero a la base de datos.
     *
     * @param daoPasajero El objeto DaoPasajero utilizado para interactuar con la base de datos.
     * @param scanner     El objeto Scanner utilizado para la entrada del usuario.
     */
    private static void agregarPasajero(DaoPasajero daoPasajero, Scanner scanner) {
        System.out.println("Introduzca los datos del nuevo pasajero:");

        String nombre;
        int edad;
        double peso;

        System.out.println("Introduce el nombre del pasajero:");
        nombre = scanner.nextLine();
        System.out.println("Introduce la edad del pasajero:");
        edad = scanner.nextInt();
        System.out.println("Introduce el peso del pasajero:");
        peso = scanner.nextDouble();

        Pasajero nuevoPasajero = new Pasajero();
        nuevoPasajero.setNombre(nombre);
        nuevoPasajero.setEdad(edad);
        nuevoPasajero.setPeso(peso);

        boolean query = daoPasajero.alta(nuevoPasajero);

        if (!query) {
            System.out.println("Error al agregar nuevo pasajero");
        } else {
            System.out.println("Pasajero añadido correctamente");
        }
    }

    /**
     * Borra un pasajero de la base de datos por su ID.
     *
     * @param daoPasajero El objeto DaoPasajero utilizado para interactuar con la base de datos.
     * @param scanner     El objeto Scanner utilizado para la entrada del usuario.
     */
    private static void borrarPasajero(DaoPasajero daoPasajero, Scanner scanner) {
        System.out.println("Inserte el ID del pasajero que desea borrar: ");
        int idBorrar = scanner.nextInt();

        boolean query = daoPasajero.borrar(idBorrar);

        if (!query) {
            System.out.println("Error al borrar pasajero con ID " + idBorrar);
        } else {
            System.out.println("Pasajero con ID " + idBorrar + " borrado correctamente");
        }
    }

    /**
     * Consulta y muestra la información de un pasajero por su ID.
     *
     * @param daoPasajero El objeto DaoPasajero utilizado para interactuar con la base de datos.
     * @param scanner     El objeto Scanner utilizado para la entrada del usuario.
     */
    private static void consultarPasajero(DaoPasajero daoPasajero, Scanner scanner) {
        System.out.println("Inserte el ID del pasajero que desea consultar: ");
        int idConsultar = scanner.nextInt();

        Pasajero pasajeroQuery = daoPasajero.obtener(idConsultar);

        if (pasajeroQuery == null) {
            System.out.println("No existe pasajero con el ID " + idConsultar);
        } else {
            System.out.println(pasajeroQuery);
        }
    }

    /**
     * Lista todos los pasajeros almacenados en la base de datos.
     *
     * @param daoPasajero El objeto DaoPasajero utilizado para interactuar con la base de datos.
     */
    private static void listarPasajeros(DaoPasajero daoPasajero) {
        List<Pasajero> listaPasajeros = daoPasajero.listar();

        if (listaPasajeros.isEmpty()) {
            System.out.println("No existe ningún pasajero");
        } else {
            for (Pasajero pasajero :
                    listaPasajeros) {
                System.out.println(pasajero);
            }
        }
    }

    /**
     * Agrega un pasajero a un coche en la base de datos.
     *
     * @param daoCoche    El objeto DaoCoche utilizado para interactuar con la base de datos.
     * @param daoPasajero El objeto DaoPasajero utilizado para interactuar con la base de datos.
     * @param scanner     El objeto Scanner utilizado para la entrada del usuario.
     */
    private static void agregarPasajeroACoche(DaoCocheMySql daoCoche, DaoPasajero daoPasajero, Scanner scanner) {
        System.out.println("Introduzca ID del pasajero:");
        int idPasajero = scanner.nextInt();
        System.out.println("Eliga un coche por ID entre los siguientes:");
        listarCoches(daoCoche);
        int idCoche = scanner.nextInt();

        boolean query = daoPasajero.agregarPasajeroACoche(idPasajero, idCoche);

        if (!query) {
            System.out.println("Error al agregar pasajero al coche");
        } else {
            System.out.println("Pasajero añadido al coche correctamente");
        }
    }

    /**
     * Borra un pasajero de un coche en la base de datos.
     *
     * @param daoCoche    El objeto DaoCoche utilizado para interactuar con la base de datos.
     * @param daoPasajero El objeto DaoPasajero utilizado para interactuar con la base de datos.
     * @param scanner     El objeto Scanner utilizado para la entrada del usuario.
     */
    private static void borrarPasajeroDeCoche(DaoCoche daoCoche, DaoPasajero daoPasajero, Scanner scanner) {
        // Listar todos los coches y sus pasajeros asociados
        List<Coche> listaCoches = daoCoche.listar();

        // Recorremos la lista de coches, mostramos cada uno por pantalla y si este tuviera pasajeros los lista y muestra por pantalla también.
        for (Coche coche : listaCoches) {
            System.out.println(coche);
            List<Pasajero> listaPasajeros = daoPasajero.listarPasajerosDeCoche(coche.getId());
            for (Pasajero pasajero : listaPasajeros) {
                System.out.println("   -> " + pasajero);
            }
        }

        System.out.println("Introduzca ID del pasajero:");
        int idPasajero = scanner.nextInt();

        boolean query = daoPasajero.borrarPasajeroDeCoche(idPasajero);

        if (!query) {
            System.out.println("Error al borrar pasajero del coche");
        } else {
            System.out.println("Pasajero borrado del coche correctamente");
        }
    }

    /**
     * Lista los pasajeros asociados a un coche en la base de datos.
     *
     * @param daoPasajero El objeto DaoPasajero utilizado para interactuar con la base de datos.
     * @param scanner     El objeto Scanner utilizado para la entrada del usuario.
     */
    private static void listarPasajerosDeCoche(DaoPasajero daoPasajero, Scanner scanner) {
        System.out.println("Introduzca ID del coche:");
        int idCoche = scanner.nextInt();

        List<Pasajero> listaPasajeros = daoPasajero.listarPasajerosDeCoche(idCoche);

        if (listaPasajeros.isEmpty()) {
            System.out.println("No existe ningún pasajero para el coche con ID " + idCoche);
        } else {
            for (Pasajero pasajero : listaPasajeros) {
                System.out.println(pasajero);
            }
        }
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

    public static void agregarCoche(GestorCoche gestorCoche, Scanner scanner) {
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

        // Implementamos la capa de negocio para cumplir el requerimiento 3.
        int query = gestorCoche.alta(nuevoCoche);

        if (query == 0) {
            System.out.println("Coche añadido correctamente");
        } else if (query == 1) {
            System.out.println("Error al agregar nuevo coche");
        } else {
            System.out.println("Los campos Marca y Modelo son obligatorios");
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
            for (Coche coche : listaCoches) {
                System.out.println(coche);
            }
        }
    }
}
