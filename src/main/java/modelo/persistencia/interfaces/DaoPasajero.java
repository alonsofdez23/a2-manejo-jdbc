package modelo.persistencia.interfaces;

import modelo.entidad.Pasajero;

import java.util.List;

/**
 * La interfaz DaoPasajero define métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Borrar)
 * relacionadas con la entidad Pasajero en una base de datos, así como operaciones relacionadas con
 * la asociación entre pasajeros y coches.
 */
public interface DaoPasajero {
    /**
     * Realiza el alta de un nuevo pasajero en la base de datos.
     *
     * @param p El objeto Pasajero que se va a dar de alta.
     * @return `true` si el alta se realizó correctamente, `false` en caso contrario.
     */
    public boolean alta(Pasajero p);

    /**
     * Borra un pasajero de la base de datos según su ID.
     *
     * @param id El ID del pasajero que se va a borrar.
     * @return `true` si el borrado se realizó correctamente, `false` en caso contrario.
     */
    public boolean borrar(int id);

    /**
     * Obtiene un pasajero de la base de datos según su ID.
     *
     * @param id El ID del pasajero que se desea obtener.
     * @return El objeto Pasajero correspondiente al identificador, o `null` si no se encuentra.
     */
    public Pasajero obtener(int id);

    /**
     * Obtiene una lista de todos los pasajeros almacenados en la base de datos.
     *
     * @return Una lista de objetos Pasajero, o una lista vacía si no hay pasajeros en la base de datos.
     */
    public List<Pasajero> listar();

    /**
     * Asocia un pasajero a un coche en la base de datos.
     *
     * @param idPasajero El ID del pasajero.
     * @param idCoche El ID del coche al que se va a asociar el pasajero.
     * @return `true` si la asociación se realizó correctamente, `false` en caso contrario.
     */
    public boolean agregarPasajeroACoche(int idPasajero, int idCoche);

    /**
     * Desasocia un pasajero de un coche en la base de datos.
     *
     * @param idPasajero El ID del pasajero que se va a desasociar del coche.
     * @return `true` si la desasociación se realizó correctamente, `false` en caso contrario.
     */
    public boolean borrarPasajeroDeCoche(int idPasajero);

    /**
     * Obtiene una lista de pasajeros asociados a un coche en la base de datos.
     *
     * @param idCoche El ID del coche.
     * @return Una lista de objetos Pasajero asociados al coche, o una lista vacía si no hay asociaciones.
     */
    public List<Pasajero> listarPasajerosDeCoche(int idCoche);
}
