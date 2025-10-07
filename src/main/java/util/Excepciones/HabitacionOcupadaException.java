package util.Excepciones;

import modelo.habitaciones.Habitacion;

public class HabitacionOcupadaException extends Exception
{
    private Habitacion habitacion;
    public HabitacionOcupadaException(Habitacion h)
    {
        super("La habitacion ya esta ocupada: ");
        this.habitacion = h;
    }
    public Habitacion getHabitacion() {
        return habitacion;
    }
}
