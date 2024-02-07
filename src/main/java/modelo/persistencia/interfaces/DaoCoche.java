package modelo.persistencia.interfaces;

import modelo.entidad.Coche;

import java.util.List;

public interface DaoCoche {
    public boolean alta(Coche c);
    public boolean baja(int id);
    public boolean modificar(Coche c);
    public Coche obtener(int id);
    public List<Coche> listar();

}
