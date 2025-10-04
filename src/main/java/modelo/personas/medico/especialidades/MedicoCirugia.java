package modelo.personas.medico.especialidades;

import modelo.personas.medico.Medico;
import util.Domicilio;

/**
 * Clase que representa a un médico especialista en cirugía.
 * Hereda de la clase Medico y aplica un incremento del 10% al sueldo base
 */
public class MedicoCirugia extends Medico {
    private static final double INCREMENTO = 1.1;

    /**
     * Constructor de la clase MedicoCirugia.
     * <b>pre</b>: Los datos del médico deben ser válidos. El número de matrícula debe ser único. Strings y domicilio !=null.
     * <b>post:</b> Crea un médico cirujano con los datos proporcionados.
     * @param nombre Nombre del médico.
     * @param apellido Apellido del médico.
     * @param dni Documento Nacional de Identidad del médico.
     * @param domicilio Domicilio del médico.
     * @param telefono Teléfono del médico.
     * @param nroMatricula Número de matrícula del médico.
     */
    public MedicoCirugia(String nombre, String apellido, String dni , Domicilio domicilio,String telefono, String nroMatricula){
        super(nombre, apellido, dni, domicilio, telefono, nroMatricula);
    }


    @Override
    public double getSueldo(){
        return Medico.getSueldoBase() *  INCREMENTO;
    }
    /**
     * Método toString que devuelve una representación en cadena del médico cirujano.
     * Incluye la especialidad del médico.
     * @return Cadena con la información del médico y su especialidad.
     */
    @Override
    public String toString() {
        return super.toString()+"Especialidad: Medico cirugia";
    }
}
