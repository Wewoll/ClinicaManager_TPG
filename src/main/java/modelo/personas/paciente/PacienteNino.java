package modelo.personas.paciente;

/**
 * Clase que representa a un paciente niño. Extiende de la clase Paciente.
 */
public class PacienteNino extends Paciente{
    /**
     * Constructor de la clase PacienteNino.
     * <b>pre:</b> Los datos del paciente deben ser válidos (Strings y domicilio !=null).
     * <b>post:</b> Se crea un objeto PacienteNino con los datos proporcionados.
     * @param nombre Nombre del paciente.
     * @param apellido Apellido del paciente.
     * @param dni DNI del paciente.
     * @param domicilio Domicilio del paciente.
     * @param telefono Teléfono del paciente.
     * @param nroHistoriaMedica Número de historia médica del paciente.
     */
    public PacienteNino(String nombre, String apellido, String dni , util.Domicilio domicilio, String telefono, String nroHistoriaMedica){
        super(nombre, apellido, dni, domicilio, telefono, nroHistoriaMedica);
    }
    /**
     * Método toString que devuelve una representación en cadena del paciente niño.
     * @return Cadena con el tipo de paciente.
     */
    @Override
    public String toString() {
        return super.toString()+"Tipo de paciente: Nino";
    }
}
