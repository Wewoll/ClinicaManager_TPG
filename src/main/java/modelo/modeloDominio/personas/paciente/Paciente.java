package modelo.modeloDominio.personas.paciente;

import modelo.modeloDominio.habitaciones.Habitacion;
import modelo.modeloDominio.personas.Persona;

import java.time.LocalDate;

/**
 * Clase abstracta Paciente que extiende Persona
 * Contiene atributos nroHistoriaMedica, diasInternado, habitacion asignada e boolean internado em caso de internacion,  y fechaIngreso a la clinica.
 */
public abstract class Paciente extends Persona
{
    private final String nroHistoriaMedica;
    private int diasInternado;
    private Habitacion habitacion;
    private boolean internado;
    private LocalDate fechaIngreso;

    /**
     * Constructor de Paciente
     *
     * @param nombre            parametro para setear el nombre del paciente.
     * @param apellido          parametro para setear el apellido del paciente.
     * @param dni               parametro para setear el dni del paciente.
     * @param domicilio         parametro para setear el domicilio del paciente.
     * @param telefono          parametro para setear el telefono del paciente.
     * @param nroHistoriaMedica parametro para setear el numero de historia clinica del paciente.
     * @param fechaIngreso      parametro para setear la fecha de ingreso del paciente a la clinica.
     *                          <b>pre:</b> nroHistoriaMedica no debe ser nulo ni vacio, fechaIngreso no debe ser nulo.
     *                          <b>post:</b> se crea un paciente con su historia clinica, dias internado en 0, sin habitacion asignada e internado en false.
     */
    public Paciente(String nombre, String apellido, String dni, modelo.modeloDominio.util.Domicilio domicilio, String telefono, String nroHistoriaMedica, LocalDate fechaIngreso)
    {
        super(nombre, apellido, dni, domicilio, telefono);
        this.nroHistoriaMedica = nroHistoriaMedica;
        this.diasInternado = 0;
        this.habitacion = null;
        this.internado = false;
        this.fechaIngreso = fechaIngreso;
    }

    /**
     * Metodo publico String para preguntar cual es el numero de historia medica del paciente.
     *
     * @return nroHistoriaMedica
     */
    public String getNroHistoriaMedica()
    {
        return nroHistoriaMedica;
    }

    /**
     * Metodo publico int para preguntar cuantos dias esta internado el paciente.
     *
     * @return diasInternado
     */
    public int getDiasInternado()
    {
        return diasInternado;
    }

    /**
     * Metodo publico boolean para preguntar si el paciente esta internado.
     *
     * @return
     */
    public boolean isInternado()
    {
        return internado;
    }

    /**
     * Metodo publico LocalDate para preguntar la fecha de ingreso del paciente a la clinica.
     *
     * @return fechaIngreso
     */
    public LocalDate getFechaIngreso()
    {
        return fechaIngreso;
    }
    /**
     * Metodo publico void para setear la fecha de ingreso.
     *
     * @param fechaIngreso parametro para setear la fecha de ingreso.
     *<b>post:</b> se setea el atributo internado del paciente.
     */
    public void setFechaIngreso(LocalDate fechaIngreso)
    {
        assert fechaIngreso != null;
        this.fechaIngreso = fechaIngreso;
    }

    /**
     * Metodo publico void para setear si el paciente esta internado.
     *
     * @param internado parametro para setear si el paciente esta internado.
     *<b>post:</b> se setea el atributo internado del paciente.
     */
    public void setInternado(boolean internado)
    {
        this.internado = internado;
    }

    /**
     * Metodo publico void para setear los dias que el paciente esta internado.
     * <b>pre:</b> diasInternado debe ser mayor o igual a 0.
     * <b>post:</b> se setea el atributo diasInternado del paciente.
     *
     * @param diasInternado
     */
    public void setDiasInternado(int diasInternado)
    {
        assert diasInternado >= 0;
        this.diasInternado = diasInternado;
    }

    /**
     * Metodo publico Habitacion para preguntar cual es la habitacion asignada al paciente.
     *
     * @return habitacion asignada al paciente.
     */
    public Habitacion getHabitacion()
    {
        return habitacion;
    }

    /**
     * Metodo publico void para setear la habitacion asignada al paciente.
     * <b>pre:</b> habitacion no debe ser nulo.
     * <b>post:</b> se setea la habitacion asignada al paciente.
     *
     * @param habitacion
     */
    public void setHabitacion(Habitacion habitacion)
    {
        assert habitacion != null;
        this.habitacion = habitacion;
    }


    /**
     * Metodo abstracto boolean para definir la prioridad de atencion del paciente.
     *
     * @param paciente parametro para comparar la prioridad de atencion con otro paciente.
     * @return true si el paciente tiene mayor prioridad que el otro paciente, false en caso contrario.
     */
    public abstract boolean prioridad(Paciente paciente);

    /**
     * Metodo abstracto boolean para definir la prioridad de atencion con un paciente joven.
     *
     * @return true si el paciente tiene prioridad con un paciente joven, false en caso contrario.
     */
    public abstract boolean prioridadConJoven();

    /**
     * Metodo abstracto boolean para definir la prioridad de atencion con un paciente mayor.
     *
     * @return true si el paciente tiene prioridad con un paciente mayor, false en caso contrario.
     */
    public abstract boolean prioridadConMayor();

    /**
     * Metodo abstracto boolean para definir la prioridad de atencion con un paciente nino.
     *
     * @return true si el paciente tiene prioridad con un paciente nino, false en caso contrario.
     */
    public abstract boolean prioridadConNino();

    /**
     * Metodo toString sobreescrito para representar al paciente.
     *
     * @return String con los datos del paciente.
     */
    @Override
    public String toString()
    {
        return "Paciente{" +
                "nroHistoriaMedica='" + nroHistoriaMedica + '\'' +
                '}';
    }
}
