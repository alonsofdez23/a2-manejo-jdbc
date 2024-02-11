package modelo.persistencia;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.persistencia.interfaces.DaoPasajero;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase DaoPasajeroMySql implementa la interfaz DaoPasajero y proporciona
 * implementaciones específicas para operaciones CRUD relacionadas con la entidad Pasajero
 * utilizando una base de datos MySQL.
 */
public class DaoPasajeroMySql implements DaoPasajero {
    private Connection connection;

    public boolean abrirConexion() {
        Configuracion conf = new Configuracion();
        conf.inicializar();

        String url = conf.getProperty("url");
        String user = conf.getProperty("user");
        String password = conf.getProperty("password");

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean cerrarConexion() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean alta(Pasajero p) {
        if (!abrirConexion()) {
            return false;
        }
        boolean alta = true;

        String query = "INSERT INTO pasajeros (nombre, edad, peso)"
                + " VALUES(?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, p.getNombre());
            ps.setInt(2, p.getEdad());
            ps.setDouble(3, p.getPeso());

            int numeroFilasAfectadas = ps.executeUpdate();
            if (numeroFilasAfectadas == 0) {
                alta = false;
            }
        } catch (SQLException e) {
            System.out.println("Alta -> Error al insertar: " + p);
            alta = false;
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return alta;
    }

    @Override
    public boolean borrar(int id) {
        if (!abrirConexion()) {
            return false;
        }
        boolean borrado = true;

        String query = "DELETE FROM pasajeros WHERE id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            int numeroFilasAfectadas = ps.executeUpdate();
            if (numeroFilasAfectadas == 0) {
                borrado = false;
            }
        } catch (SQLException e) {
            System.out.println("Baja -> No se ha podido dar de baja el ID " + id);
            borrado = false;
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return borrado;
    }

    @Override
    public Pasajero obtener(int id) {
        if (!abrirConexion()) {
            return null;
        }
        Pasajero pasajero = null;

        String query = "SELECT * FROM pasajeros WHERE id=?";

        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pasajero = new Pasajero();
                pasajero.setId(rs.getInt(1));
                pasajero.setNombre(rs.getString(2));
                pasajero.setEdad(rs.getInt(3));
                pasajero.setPeso(rs.getDouble(4));
            }
        } catch (SQLException e) {
            System.out.println("Obtener -> Error al obtener el pasajero con ID " + id);
            pasajero = null;
            throw new RuntimeException(e);
        } finally {
            cerrarConexion();
        }
        return pasajero;
    }

    @Override
    public List<Pasajero> listar() {
        if (!abrirConexion()) {
            return null;
        }
        List<Pasajero> listaPasajeros = new ArrayList<>();

        String query = "SELECT * FROM pasajeros";

        try {
            PreparedStatement ps = connection.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pasajero pasajero = new Pasajero();
                pasajero.setId(rs.getInt(1));
                pasajero.setNombre(rs.getString(2));
                pasajero.setEdad(rs.getInt(3));
                pasajero.setPeso(rs.getDouble(4));

                listaPasajeros.add(pasajero);
            }
        } catch (SQLException e) {
            System.out.println("Listar -> Error a obtener los pasajeros");
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return listaPasajeros;
    }

    /**
     * Asocia un pasajero a un coche en la base de datos.
     *
     * @param idPasajero El ID del pasajero.
     * @param idCoche El ID del coche al que se va a asociar el pasajero.
     * @return `true` si la asociación se realizó correctamente, `false` en caso contrario.
     */
    @Override
    public boolean agregarPasajeroACoche(int idPasajero, int idCoche) {
        if (!abrirConexion()) {
            return false;
        }
        boolean agregado = true;

        // Query SQL para actualizar el campo 'coche_id' del pasajero con el identificador dado en la tabla pasajeros.
        String query = "UPDATE pasajeros SET coche_id = ? WHERE id = ?";

        try {
            // Prepara la sentencia SQL.
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idCoche);
            ps.setInt(2, idPasajero);

            // Ejecuta la sentencia y verifica si se afectaron filas en la base de datos.
            int numeroFilasAfectadas = ps.executeUpdate();
            if (numeroFilasAfectadas == 0) {
                agregado = false;
            }
        } catch (SQLException e) {
            System.out.println("Agregar -> Error al agregar pasajero al coche con ID " + idCoche);
            agregado = false;
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return agregado;
    }

    /**
     * Desasocia un pasajero de un coche en la base de datos.
     *
     * @param idPasajero El ID del pasajero que se va a desasociar del coche.
     * @return `true` si la desasociación se realizó correctamente, `false` en caso contrario.
     */
    @Override
    public boolean borrarPasajeroDeCoche(int idPasajero) {
        if (!abrirConexion()) {
            return false;
        }
        boolean borrado = true;

        // Query SQL para actualizar el campo 'coche_id' del pasajero a NULL.
        String query = "UPDATE pasajeros SET coche_id = NULL WHERE id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idPasajero);

            int numeroFilasAfectadas = ps.executeUpdate();
            if (numeroFilasAfectadas == 0) {
                borrado = false;
            }
        } catch (SQLException e) {
            System.out.println("Agregar -> Error al borrar pasajero con ID " + idPasajero + " del coche");
            borrado = false;
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return borrado;
    }

    /**
     * Obtiene una lista de pasajeros asociados a un coche en la base de datos.
     *
     * @param idCoche El ID del coche.
     * @return Una lista de objetos Pasajero asociados al coche, o una lista vacía si no hay asociaciones.
     */
    @Override
    public List<Pasajero> listarPasajerosDeCoche(int idCoche) {
        if (!abrirConexion()) {
            return null;
        }
        List<Pasajero> listaPasajeros = new ArrayList<>();

        // Query SQL para seleccionar pasajeros de un coche específico.
        String query = "SELECT * FROM pasajeros WHERE coche_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idCoche);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pasajero pasajero = new Pasajero();
                pasajero.setId(rs.getInt(1));
                pasajero.setNombre(rs.getString(2));
                pasajero.setEdad(rs.getInt(3));
                pasajero.setPeso(rs.getDouble(4));

                listaPasajeros.add(pasajero);
            }
        } catch (SQLException e) {
            System.out.println("Listar -> Error a obtener los pasajeros");
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return listaPasajeros;
    }
}
