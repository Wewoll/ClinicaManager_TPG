package modelo.personas.paciente;

import modelo.personas.Persona;

/**
 * Clase abstracta que representa a un paciente. Extiende de la clase Persona.
 * Contiene como atributos el número de historia médica y los días internado (de ser necesaria la internacion).
 */
public abstract class Paciente extends Persona {
    private final String nroHistoriaMedica;
    private int diasInternado; //TODO inicializar

    /**
     * Constructor de la clase Paciente.
     * <b>pre: </b> Los datos del paciente deben ser válidos (Strings y domicilio !=null).
     * <b>post: </b> Se crea un objeto Paciente con los datos proporcionados.
     * @param nombre Nombre del paciente.
     * @param apellido Apellido del paciente.
     * @param dni DNI del paciente.
     * @param domicilio Domicilio del paciente.
     * @param telefono Teléfono del paciente.
     * @param nroHistoriaMedica Número de historia médica del paciente.
     */
    public Paciente(String nombre, String apellido, String dni, util.Domicilio domicilio,String telefono, String nroHistoriaMedica) {
        super(nombre, apellido, dni, domicilio, telefono);
        this.nroHistoriaMedica = nroHistoriaMedica;
    }

    /**
     * Getter del número de historia médica.
     * @return Número de historia médica del paciente.
     */
    public String getNroHistoriaMedica() {
        return nroHistoriaMedica;
    }

    /**
     * Getter de los días internado.
     * @return Días internado del paciente.
     */
    public int getDiasInternado() {
        return diasInternado;
    }

    //TODO setter de dias internado


    /**
     * Método toString que devuelve una representación en cadena del paciente.
     * @return Cadena con el número de historia médica del paciente.
     */
    @Override
    public String toString() {
        return "Paciente{" +
                "nroHistoriaMedica='" + nroHistoriaMedica + '\'' +
                '}';
    }
}
