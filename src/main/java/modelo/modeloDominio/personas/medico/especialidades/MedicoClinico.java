package modelo.modeloDominio.personas.medico.especialidades;

import modelo.modeloDominio.personas.medico.IMedico;
import modelo.modeloDominio.personas.medico.Medico;
import modelo.modeloDominio.util.Domicilio;
/**
 * Clase MedicoClinico que representa a un medico clinico.
 * Hereda de la clase Medico.
 */
public class MedicoClinico extends Medico {
    private static final double INCREMENTO = 1.05;
    /**
     * Constructor de MedicoClinico
     * <b>post:</b> se crea un medico clinico con los datos proporcionados.
     * @param nombre Nombre del medico.
     * @param apellido Apellido del medico.
     * @param dni DNI del medico.
     * @param domicilio Domicilio del medico.
     * @param telefono Telefono del medico.
     * @param nroMatricula Numero de matricula del medico.
     */
    public MedicoClinico(String nombre, String apellido, String dni , Domicilio domicilio,String telefono, String nroMatricula){
        super(nombre, apellido, dni, domicilio,telefono, nroMatricula);
    }
    /**
     * Metodo double que calcula el sueldo del medico clinico.
     * <b>post:</b> retorna el sueldo del medico clinico.
     * @return sueldo del medico clinico.
     */
    @Override
    public double getSueldo(){
        return IMedico.getSueldoBase() *  INCREMENTO;
    }
    /**
     * Metodo toString sobreescrito para representar al medico clinico.
     * @return "Medico clinico" como tipo de medico.
     */
    @Override
    public String toString() {
        return "Medico clinico";
    }
}
