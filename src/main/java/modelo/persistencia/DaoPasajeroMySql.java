package modelo.persistencia;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.persistencia.interfaces.DaoPasajero;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void agregarPasajeroACoche(int idPasajero, int idCoche) {

    }

    @Override
    public void borrarPasajeroDeCoche(int idPasajero) {

    }

    @Override
    public List<Pasajero> listarPasajerosDeCoche(int idCoche) {
        return null;
    }
}
