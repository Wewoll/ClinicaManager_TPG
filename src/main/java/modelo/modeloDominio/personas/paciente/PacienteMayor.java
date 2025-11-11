package modelo.modeloDominio.personas.paciente;

import java.time.LocalDate;
/**
 * Clase PacienteMayor que extiende Paciente
 */
public class PacienteMayor extends Paciente{
    /**
     * Constructor de PacienteMayor
     * <b>pre:</b> los parametros no deben ser nulos
     * <b>post:</b> se crea un paciente mayor
     * @param nombre
     * @param apellido
     * @param dni
     * @param domicilio
     * @param telefono
     * @param nroHistoriaMedica
     * @param fechaIngreso
     */
    public PacienteMayor(String nombre, String apellido, String dni, modelo.modeloDominio.util.Domicilio domicilio, String telefono, String nroHistoriaMedica, LocalDate fechaIngreso)
    {
        super(nombre, apellido, dni, domicilio, telefono, nroHistoriaMedica, fechaIngreso);
    }

    /**
     * Metodo que define la prioridad de atencion entre pacientes
     * @param paciente paciente a comparar
     * @return true si el paciente actual tiene prioridad sobre el paciente pasado por parametro, false en caso contrario
     */
    @Override
    public boolean prioridad(Paciente paciente){
        assert  paciente != null;
        return paciente.prioridadConMayor();
    }

    /**
     * Metodo que define la prioridad de atencion entre pacientes mayores y jovenes
     * @return false, el paciente mayor va para la sala
     */
    @Override
    public boolean prioridadConJoven(){
        return false; // el joven va para la sala
    }

    /**
     * Metodo que define la prioridad de atencion entre pacientes mayores
     * @return false, el paciente que llego primero tiene prioridad
     */
    @Override
    public boolean prioridadConMayor(){
        return false; // el mayor que estaba se queda
    }
    /**
     * Metodo que define la prioridad de atencion entre pacientes mayores y ninos
     * @return true, el paciente mayor va para la sala
     */
    @Override
    public boolean prioridadConNino(){
        return true; // el mayor va para la sala
    }

    /**
     * Metodo toString sobreescrito para representar al paciente mayor.
     * @return String con los datos del paciente mayor.
     */
    @Override
    public String toString() {
        return super.toString()+"Tipo de paciente: Mayor";
    }
}
