package modelo.habitaciones;

public class HabitacionCompartida extends Habitacion{
    private static final int MAXIMO_PACIENTES = 2;

    public HabitacionCompartida() {
        super(MAXIMO_PACIENTES);
    }


}
