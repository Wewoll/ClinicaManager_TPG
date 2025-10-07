package modelo.habitaciones;

import modelo.personas.paciente.Paciente;
import util.Excepciones.HabitacionOcupadaException;

/**
 * Clase abstracta Habitacion
 */
public abstract class Habitacion
{
    private static int nroHabitacionSiguiente = 1;
    private final int nroHabitacion;
    protected boolean ocupada;
    private int maximoPacientes;
    private int cantidadPacientes;

    /**
     * Constructor de habitacion
     *
     * @param maximoPacientes parametro para setear el maximo de pacientes en la habitacion
     *                        pre: maximoPaciente mayor o igual a 1
     */
    public Habitacion(int maximoPacientes)
    {
        this.maximoPacientes = maximoPacientes;
        this.cantidadPacientes = 0;
        this.nroHabitacion = nroHabitacionSiguiente;
        this.ocupada = false;
        nroHabitacionSiguiente++;
    }

    /**
     * Metodo publico int para preguntar cual es el numero de la habitacion
     *
     * @return numero de la habitacion
     */
    public int getNroHabitacion()
    {
        return this.nroHabitacion;
    }

    /**
     * Metodo publico boolean para preguntar si la habitacion esta ocupada
     *
     * @return boolean ocupada
     */
    public boolean isOcupada()
    {
        return this.ocupada;
    }

    /**
     * Metodo publico int que pregunta cual es el maximo de pacientes que puede tener la habitacion
     *
     * @return el maximo de la habitacion
     */
    public int getMaximoPacientes()
    {
        return this.maximoPacientes;
    }

    /**
     * Metodo publico int que pregunta la cantidad de pacientes actuales en la habitacion
     *
     * @return la cantidad actual de pacientes
     */
    public int getCantidadPacientes()
    {
        return this.cantidadPacientes;
    }

    /**
     * Metodo protected void que setea la cantidad de pacientes
     *
     * @param cantidadPacientes valor incrementado/decrementado de pacientes
     */
    protected void setCantPacientes(int cantidadPacientes)
    {
        this.cantidadPacientes = cantidadPacientes;
    }

    /**
     * Metodo publico void para ocupa la habitacion
     *
     */
    public void ocupar() throws HabitacionOcupadaException
    {
        if (!this.ocupada)
        {
            this.ocupada = true;
            this.cantidadPacientes = 1;
        }else
            throw new HabitacionOcupadaException(this);
    }

    /**
     * Metodo publico void para desocupar la habitacion
     */
    public void desocupar()
    {
        this.ocupada = false;
        this.cantidadPacientes = 0;
    }

    public abstract double calcularArancelInternacion(Paciente p);
}
