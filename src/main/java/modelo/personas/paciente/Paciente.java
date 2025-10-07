package modelo.personas.paciente;

import modelo.habitaciones.Habitacion;
import modelo.personas.Persona;

import java.time.LocalDate;

public abstract class Paciente extends Persona
{
    private final String nroHistoriaMedica;
    private int diasInternado; //TODO inicializar
    private Habitacion habitacion;
    private boolean internado;
    private LocalDate fechaIngreso;

    public Paciente(String nombre, String apellido, String dni, util.Domicilio domicilio, String telefono, String nroHistoriaMedica, LocalDate fechaIngreso)
    {
        super(nombre, apellido, dni, domicilio, telefono);
        this.nroHistoriaMedica = nroHistoriaMedica;
        this.diasInternado = 0;
        this.habitacion = null;
        this.internado = false;
        this.fechaIngreso = fechaIngreso;
    }

    public String getNroHistoriaMedica()
    {
        return nroHistoriaMedica;
    }

    public int getDiasInternado()
    {
        return diasInternado;
    }

    public boolean isInternado()
    {
        return internado;
    }

    public LocalDate getFechaIngreso()
    {
        return fechaIngreso;
    }

    public void setInternado(boolean internado)
    {
        this.internado = internado;
    }

    public void setDiasInternado(int diasInternado)
    {
        this.diasInternado = diasInternado;
    }

    public Habitacion getHabitacion()
    {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion)
    {
        this.habitacion = habitacion;
    }

    public abstract boolean prioridad(Paciente paciente);

    public abstract boolean prioridadConJoven();

    public abstract boolean prioridadConMayor();

    public abstract boolean prioridadConNino();

    @Override
    public String toString()
    {
        return "Paciente{" +
                "nroHistoriaMedica='" + nroHistoriaMedica + '\'' +
                '}';
    }
}
