package modelo.negocio;

import modelo.entidad.Coche;
import modelo.persistencia.DaoCocheMySql;
import modelo.persistencia.interfaces.DaoCoche;

/**
 * Reglas de negocio para la entidad Coche a través de DaoCoche
 */
public class GestorCoche {
    // Instancia del objeto DaoCoche para interactuar con la base de datos.
    private DaoCoche daoCoche = new DaoCocheMySql();

    /**
     * Controlamos que el alta de un nuevo coche cumpla lo pedido
     * en el requerimiento 3. El campo de modelo y marca no pueden estar vacíos.
     *
     * @param coche Entidad Coche.
     * @return Código de estado:
     *      - 0 si el alta se realizó correctamente.
     *      - 1 si hubo un error al dar de alta el coche.
     *      - 2 si el campo marca o modelo están vacíos.
     */
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
