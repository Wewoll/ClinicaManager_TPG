package modelo.personas.paciente;

import modelo.habitaciones.Habitacion;
import modelo.personas.Persona;

public abstract class Paciente extends Persona {
    private final String nroHistoriaMedica;
    private int diasInternado; //TODO inicializar
    private Habitacion habitacion;
    public Paciente(String nombre, String apellido, String dni, util.Domicilio domicilio,String telefono, String nroHistoriaMedica) {
        super(nombre, apellido, dni, domicilio, telefono);
        this.nroHistoriaMedica = nroHistoriaMedica;
        this.diasInternado = 0;
        this.habitacion = null;
    }
    public String getNroHistoriaMedica() {
        return nroHistoriaMedica;
    }

    public int getDiasInternado() {
        return diasInternado;
    }

    public void setDiasInternado(int diasInternado) {
        this.diasInternado = diasInternado;
    }
    public Habitacion getHabitacion() {
        return habitacion;
    }
    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }
    public abstract boolean prioridad(Paciente paciente);
    public abstract boolean prioridadConJoven();
    public abstract boolean prioridadConMayor();
    public abstract boolean prioridadConNino();

    @Override
    public String toString() {
        return "Paciente{" +
                "nroHistoriaMedica='" + nroHistoriaMedica + '\'' +
                '}';
    }
}
