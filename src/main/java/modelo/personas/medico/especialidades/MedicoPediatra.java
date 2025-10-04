package modelo.personas.medico.especialidades;

import modelo.personas.medico.Medico;
import util.Domicilio;
    /**
     * Clase que representa a un médico pediatra.
     * Hereda de la clase Medico y aplica un incremento del 5% al sueldo base
     */
public class MedicoPediatra extends Medico {
    private static final double INCREMENTO = 1.05;
    /** Constructor de la clase MedicoPediatra.
     * <b>pre:</b> Los parámetros nombre, apellido, dni, domicilio, telefono y nroMatricula no deben ser nulos o vacíos.
     * <b>post:</b> Se crea un objeto MedicoPediatra con los datos proporcionados.
     * @param nombre Nombre del médico.
     * @param apellido Apellido del médico.
     * @param dni Documento Nacional de Identidad del médico.
     * @param domicilio Domicilio del médico.
     * @param telefono Teléfono del médico.
     * @param nroMatricula Número de matrícula del médico.
     */
    public MedicoPediatra(String nombre, String apellido, String dni , Domicilio domicilio, String telefono, String nroMatricula){
        super(nombre, apellido, dni, domicilio,telefono, nroMatricula);
    }
    @Override
    public double getSueldo(){
        return Medico.getSueldoBase() *  INCREMENTO;
    }

        /**
         * Método toString que devuelve una representación en cadena del médico pediatra.
         * Incluye la especialidad del médico.
         * @return Cadena con la información del médico y su especialidad.
         */
    @Override
    public String toString() {
        return super.toString()+"Especialidad: Medico pediatro";
    }
}
