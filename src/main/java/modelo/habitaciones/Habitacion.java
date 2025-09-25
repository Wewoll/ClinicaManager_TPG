package modelo.habitaciones;

public abstract class Habitacion {
    private static int nroHabitacionSiguiente = 1;
    private final int nroHabitacion;
    private boolean ocupada;
    private int maximoPacientes;
    private int cantidadPacientes;

    public Habitacion(int maximoPacientes) {
        this.maximoPacientes = maximoPacientes;
        this.cantidadPacientes = 0;
        this.nroHabitacion = nroHabitacionSiguiente;
        this.ocupada = false;
        nroHabitacionSiguiente++;
    }
}
