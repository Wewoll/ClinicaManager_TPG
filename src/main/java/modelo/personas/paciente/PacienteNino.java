package modelo.personas.paciente;

import java.time.LocalDate;
/**
 * Clase PacienteNino que extiende Paciente
 */
public class PacienteNino extends Paciente{
    /**
     * Constructor de PacienteNino
     * <b>pre:</b> los parametros no deben ser nulos
     * <b>post:</b> se crea un paciente nino
      * @param nombre
     * @param apellido
     * @param dni
     * @param domicilio
     * @param telefono
     * @param nroHistoriaMedica
     * @param fechaIngreso
     */
    public PacienteNino(String nombre, String apellido, String dni , util.Domicilio domicilio, String telefono, String nroHistoriaMedica, LocalDate fechaIngreso){
        super(nombre, apellido, dni, domicilio, telefono, nroHistoriaMedica,fechaIngreso);
    }

    /**
     * Metodo que define la prioridad de atencion entre pacientes
     * @param paciente paciente a comparar
     * @return true si el paciente actual tiene prioridad sobre el paciente pasado por parametro, false en caso contrario
     */
    @Override
    public boolean prioridad(Paciente paciente){
        return paciente.prioridadConNino();
    }

    /**
     * Metodo que define la prioridad de atencion entre pacientes ninos y jovenes
     * @return true, el paciente nino va para la sala
     */
    @Override
    public boolean prioridadConJoven(){
        return true; // la sala queda para ninio
    }
    /**
     * Metodo que define la prioridad de atencion entre pacientes ninos y mayores
     * @return false, el paciente nino va para el patio
     */
    @Override
    public boolean prioridadConMayor(){
        return false; // el ninio va para el patio
    }
    /**
     * Metodo que define la prioridad de atencion entre pacientes ninos
     * @return false, el paciente que llego primero tiene prioridad
     */
    @Override
    public boolean prioridadConNino(){
        return false; // el ninio anterior va para el patio
    }
    /**
     * Metodo toString que retorna la representacion en String del paciente nino
     * @return String con la representacion del paciente nino
     */
    @Override
    public String toString() {
        return super.toString()+"Tipo de paciente: Nino";
    }
}
