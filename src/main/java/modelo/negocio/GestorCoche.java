package modelo.negocio;

import modelo.entidad.Coche;
import modelo.persistencia.DaoCocheMySql;
import modelo.persistencia.interfaces.DaoCoche;

public class GestorCoche {
    private DaoCoche daoCoche = new DaoCocheMySql();

    public int alta(Coche coche) {
        if (!coche.getMarca().isBlank() && !coche.getModelo().isBlank()) {
            boolean alta = daoCoche.alta(coche);
            if (alta) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 2;
        }
    }
}
