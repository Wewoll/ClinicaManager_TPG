package modelo.util.Excepciones;

import modelo.habitaciones.Habitacion;
/**
 * Excepcion lanzada cuando se intenta asignar una habitacion que ya esta ocupada.
 * Contiene la habitacion que esta ocupada.
 */
public class HabitacionOcupadaException extends Exception
{
    private Habitacion habitacion;
    /**
     * Constructor de la excepcion.
     * <b>pre:</b> h != null
     * <b>post:</b> Crea una nueva excepcion con un mensaje que incluye la habitacion ocupada.
     * @param h Habitacion que ya esta ocupada.
     */
    public HabitacionOcupadaException(Habitacion h)
    {
        super("La habitacion ya esta ocupada: ");
        this.habitacion = h;
    }

    /**
     * Obtiene la habitacion que esta ocupada.
     * @return Habitacion ocupada.
     */
    public Habitacion getHabitacion() {
        return habitacion;
    }
}
