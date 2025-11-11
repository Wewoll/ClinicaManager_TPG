package modelo.modeloDominio.personas.paciente;

import java.time.LocalDate;
/**
 * Clase PacienteJoven que extiende Paciente
 */
public class PacienteJoven extends Paciente{
    /**
     * Constructor de PacienteJoven
     * <b>pre:</b> los parametros no deben ser nulos
     * <b>post:</b> se crea un paciente joven
     * @param nombre
     * @param apellido
     * @param dni
     * @param domicilio
     * @param telefono
     * @param nroHistoriaMedica
     * @param fechaIngreso
     */
    public PacienteJoven(String nombre, String apellido, String dni , modelo.modeloDominio.util.Domicilio domicilio, String telefono, String nroHistoriaMedica, LocalDate fechaIngreso){
        super(nombre, apellido, dni, domicilio, telefono, nroHistoriaMedica, fechaIngreso);
    }

    /**
     * Metodo que define la prioridad de atencion entre pacientes
     * @param paciente paciente a comparar
     * @return true si el paciente actual tiene prioridad sobre el paciente pasado por parametro, false en caso contrario
     */
    @Override
    public boolean prioridad(Paciente paciente){
        return paciente.prioridadConJoven();
    }

    /**
     * Metodo que define la prioridad de atencion entre pacientes jovenes
     * @return false, el paciente que llego primero tiene prioridad
     */
    @Override
    public boolean prioridadConJoven(){
        return false; // se queda el joven que llego antes
    }

    /**
     * Metodo que define la prioridad de atencion entre pacientes jovenes y mayores
     * @return false, el paciente joven va para el patio
     */
    @Override
    public boolean prioridadConMayor(){
        return false; // el joven va para el patio
    }

    /**
     * Metodo que define la prioridad de atencion entre pacientes jovenes y ninos
     * @return false, el paciente joven va para el patio
     */
    @Override
    public boolean prioridadConNino(){
        return false; // el joven va para el patio
    }

    /**
     * Metodo toString sobreescrito para representar al paciente joven
     * @return String con los datos del paciente joven
     */
    @Override
    public String toString() {
        return super.toString()+"Tipo de paciente: Joven";
    }
}
