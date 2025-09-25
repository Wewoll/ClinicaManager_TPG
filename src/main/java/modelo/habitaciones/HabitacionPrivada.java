package modelo.habitaciones;

public class HabitacionPrivada extends Habitacion{
    private static final int MAXIMO_PACIENTES = 1;

    public HabitacionPrivada() {
        super(MAXIMO_PACIENTES);
    }


}
