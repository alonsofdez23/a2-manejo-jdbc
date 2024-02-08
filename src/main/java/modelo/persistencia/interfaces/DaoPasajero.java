package modelo.persistencia.interfaces;

import modelo.entidad.Pasajero;

import java.util.List;

public interface DaoPasajero {
    public boolean alta(Pasajero p);
    public boolean borrar(int id);
    public Pasajero obtener(int id);
    public List<Pasajero> listar();
    public void agregarPasajeroACoche(int idPasajero, int idCoche);
    public void borrarPasajeroDeCoche(int idPasajero);
    public List<Pasajero> listarPasajerosDeCoche(int idCoche);
}
