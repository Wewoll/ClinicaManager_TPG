package modelo.personas.medico;

import modelo.personas.Persona;
import modelo.personas.paciente.Paciente;
import util.Domicilio;

/**
 * Clase abstracta que representa a un médico.
 * Hereda de la clase Persona e implementa la interfaz IMedico.
 * Contiene como atributos el número de matrícula y el sueldo base.
 */
public abstract class Medico extends Persona implements IMedico{
    private final String nroMatricula;
    private static double sueldoBase = 20000;

    /**
     * Constructor de la clase Medico.
     * <b>pre:</b> Los datos del médico deben ser válidos (Strings y domicilio !=null).
     * <b>post:</b> Se crea un objeto Medico con los datos proporcionados.
     * @param nombre Nombre del médico.
     * @param apellido Apellido del médico.
     * @param dni DNI del médico.
     * @param domicilio Domicilio del médico.
     * @param telefono Teléfono del médico.
     * @param nroMatricula Número de matrícula del médico.
     */
    public Medico(String nombre, String apellido, String dni, Domicilio domicilio,String telefono, String nroMatricula) {
        super(nombre, apellido, dni, domicilio,telefono);
        this.nroMatricula = nroMatricula;
    }

    /**
     * Getter del número de matrícula.
     * @return Número de matrícula del médico.
     */
    public String getNroMatricula() {
        return nroMatricula;
    }

    /**
     * Método para obtener el sueldo base del médico.
     * @return Sueldo base del médico.
     */
    public static double getSueldoBase() {
        return sueldoBase;
    }

    /**
     * Setter del sueldo base del médico en caso de modificacion.
     * @param sueldoBase Nuevo sueldo base del médico.
     */
    public static void setSueldoBase(double sueldoBase) {
        Medico.sueldoBase = sueldoBase;
    }
}
