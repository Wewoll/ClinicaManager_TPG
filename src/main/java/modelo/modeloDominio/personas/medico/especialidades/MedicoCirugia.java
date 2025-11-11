package modelo.modeloDominio.personas.medico.especialidades;

import modelo.modeloDominio.personas.medico.IMedico;
import modelo.modeloDominio.personas.medico.Medico;
import modelo.modeloDominio.util.Domicilio;
/**
 * Clase MedicoCirugia que representa a un medico especialista en cirugia.
 * Hereda de la clase Medico.
 */
public class MedicoCirugia extends Medico {
    private static final double INCREMENTO = 1.1;
    /**
     * Constructor de MedicoCirugia
     * <b>pre:</b> nombre, apellido, dni, domicilio, telefono y nroMatricula no deben ser nulos.
     * <b>post:</b> se crea un medico especialista en cirugia con los datos proporcionados.
     * @param nombre Nombre del medico.
     * @param apellido Apellido del medico.
     * @param dni DNI del medico.
     * @param domicilio Domicilio del medico.
     * @param telefono Telefono del medico.
     * @param nroMatricula Numero de matricula del medico.
     */
    public MedicoCirugia(String nombre, String apellido, String dni , Domicilio domicilio,String telefono, String nroMatricula){
        super(nombre, apellido, dni, domicilio, telefono, nroMatricula);
    }

    /**
     * Metodo publico double para obtener el sueldo del medico especialista en cirugia.
     * <b>post:</b> se retorna el sueldo del medico especialista en cirugia, que es el sueldo base incrementado por un factor especifico.
     * @return sueldo del medico especialista en cirugia.
     */
    @Override
    public double getSueldo(){
        return IMedico.getSueldoBase() *  INCREMENTO;
    }
    /**
     * Metodo toString sobreescrito para representar al medico especialista en cirugia.
     * @return "Medico cirugia" como tipo de medico.
     */
    @Override
    public String toString() {
        return "Medico cirugia";
    }
}
