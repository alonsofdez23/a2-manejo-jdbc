package modelo.persistencia;

import modelo.entidad.Coche;
import modelo.persistencia.interfaces.DaoCoche;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase DaoCocheMySql implementa la interfaz DaoCoche y proporciona
 * implementaciones específicas para operaciones CRUD relacionadas con la entidad Coche
 * utilizando una base de datos MySQL.
 */
public class DaoCocheMySql implements DaoCoche {
    private Connection connection;

    /**
     * Abre una conexión a la base de datos utilizando la configuración proporcionada.
     *
     * @return `true` si la conexión se estableció correctamente, `false` en caso contrario.
     */
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

    /**
     * Cierra la conexión a la base de datos.
     *
     * @return `true` si la conexión se cerró correctamente, `false` en caso contrario.
     */
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
    public boolean alta(Coche c) {
        // Verifica y establece la conexión a la base de datos.
        if (!abrirConexion()) {
            return false;
        }
        boolean alta = true;

        // Query SQL para insertar un nuevo coche en la tabla 'coches'.
        String query = "INSERT INTO coches (marca, modelo, anyo_fabricacion, km)"
                + " VALUES(?, ?, ?, ?)";

        try {
            // Prepara la sentencia SQL con los valores del coche.
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, c.getMarca());
            ps.setString(2, c.getModelo());
            ps.setInt(3, c.getAnyoFabricacion());
            ps.setInt(4, c.getKm());

            // Ejecuta la sentencia y verifica si se afectaron filas en la base de datos.
            int numeroFilasAfectadas = ps.executeUpdate();
            if (numeroFilasAfectadas == 0) {
                alta = false;
            }
        } catch (SQLException e) {
            System.out.println("Alta -> Error al insertar: " + c);
            alta = false;
            e.printStackTrace();
        } finally {
            // Cierra la conexión a la base de datos.
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

        String query = "DELETE FROM coches WHERE id = ?";

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
    public boolean modificar(Coche c) {
        if (!abrirConexion()) {
            return false;
        }
        boolean modificado = true;

        String query = "UPDATE coches SET marca=?, modelo=?, anyo_fabricacion=?, km=? "
                + "WHERE id=?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, c.getMarca());
            ps.setString(2, c.getModelo());
            ps.setInt(3, c.getAnyoFabricacion());
            ps.setInt(4, c.getKm());
            ps.setInt(5, c.getId());

            int numerosFilasAfectadas = ps.executeUpdate();
            if (numerosFilasAfectadas == 0) {
                modificado = false;
            }
        } catch (SQLException e) {
            System.out.println("Modificar -> Error al modificar el coche " + c);
            modificado = false;
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return modificado;
    }

    @Override
    public Coche obtener(int id) {
        if (!abrirConexion()) {
            return null;
        }
        Coche coche = null;

        String query = "SELECT * FROM coches WHERE id=?";

        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                coche = new Coche();
                coche.setId(rs.getInt(1));
                coche.setMarca(rs.getString(2));
                coche.setModelo(rs.getString(3));
                coche.setAnyoFabricacion(rs.getInt(4));
                coche.setKm(rs.getInt(5));
            }
        } catch (SQLException e) {
            System.out.println("Obtener -> Error al obtener el coche con ID " + id);
            coche = null;
            throw new RuntimeException(e);
        } finally {
            cerrarConexion();
        }
        return coche;
    }

    @Override
    public List<Coche> listar() {
        if (!abrirConexion()) {
            return null;
        }
        List<Coche> listaCoches = new ArrayList<>();

        String query = "SELECT id, marca, modelo, anyo_fabricacion, km FROM coches";

        try {
            PreparedStatement ps = connection.prepareStatement(query);

            // Ejecuta la sentencia y procesa los resultados.
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Crea un objeto Coche para cada resultado y lo agrega a la lista.
                Coche coche = new Coche();
                coche.setId(rs.getInt(1));
                coche.setMarca(rs.getString(2));
                coche.setModelo(rs.getString(3));
                coche.setAnyoFabricacion(rs.getInt(4));
                coche.setKm(rs.getInt(5));

                listaCoches.add(coche);
            }
        } catch (SQLException e) {
            System.out.println("Listar -> Error a obtener los coches");
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return listaCoches;
    }
}
