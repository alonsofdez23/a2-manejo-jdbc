package modelo.persistencia.interfaces;

import modelo.entidad.Coche;

import java.util.List;

/**
 * La interfaz DaoCoche define métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Borrar)
 * relacionadas con la entidad Coche en una base de datos.
 */
public interface DaoCoche {
    /**
     * Realiza el alta de un nuevo coche en la base de datos.
     *
     * @param c El objeto Coche que se va a dar de alta.
     * @return `true` si el alta se realizó correctamente, `false` en caso contrario.
     */
    public boolean alta(Coche c);

    /**
     * Borra un coche de la base de datos según su ID.
     *
     * @param id El ID del coche que se va a borrar.
     * @return `true` si el borrado se realizó correctamente, `false` en caso contrario.
     */
    public boolean borrar(int id);

    /**
     * Modifica la información de un coche en la base de datos.
     *
     * @param c El objeto Coche con la nueva información.
     * @return `true` si la modificación se realizó correctamente, `false` en caso contrario.
     */
    public boolean modificar(Coche c);

    /**
     * Obtiene un coche de la base de datos según su ID.
     *
     * @param id El ID del coche que se desea obtener.
     * @return El objeto Coche correspondiente al identificador, o `null` si no se encuentra.
     */
    public Coche obtener(int id);

    /**
     * Obtiene una lista de todos los coches almacenados en la base de datos.
     *
     * @return Una lista de objetos Coche, o una lista vacía si no hay coches en la base de datos.
     */
    public List<Coche> listar();

}
