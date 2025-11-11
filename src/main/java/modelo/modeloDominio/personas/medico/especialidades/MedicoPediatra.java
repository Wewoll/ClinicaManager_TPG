package modelo.modeloDominio.personas.medico.especialidades;

import modelo.modeloDominio.personas.medico.IMedico;
import modelo.modeloDominio.personas.medico.Medico;
import modelo.modeloDominio.util.Domicilio;
/**
 * Clase MedicoPediatra que representa a un medico especialista en pediatria.
 * Hereda de la clase Medico.
 */
public class MedicoPediatra extends Medico {
    private static final double INCREMENTO = 1.05;
    /**
     * Constructor de MedicoPediatra
     * <b>pre:</b> nombre, apellido, dni, domicilio, telefono y nroMatricula no deben ser nulos.
     * <b>post:</b> Se crea un medico pediatra con los datos proporcionados.
     * @param nombre Nombre del medico pediatra.
     * @param apellido Apellido del medico pediatra.
     * @param dni DNI del medico pediatra.
     * @param domicilio Domicilio del medico pediatra.
     * @param telefono Telefono del medico pediatra.
     * @param nroMatricula Numero de matricula del medico pediatra.
     */
    public MedicoPediatra(String nombre, String apellido, String dni , Domicilio domicilio, String telefono, String nroMatricula){
        super(nombre, apellido, dni, domicilio,telefono, nroMatricula);
    }
    /**
     * Metodo publico getSueldo que retorna el sueldo del medico pediatra.
     * @return Sueldo del medico pediatra.
     */
    @Override
    public double getSueldo(){
        return IMedico.getSueldoBase() *  INCREMENTO;
    }
    /**
     * Metodo toString sobreescrito para representar el medico pediatra.
     * @return "Medico pediatro" como tipo de medico.
     */
    @Override
    public String toString() {
        return "Medico pediatro";
    }
}
