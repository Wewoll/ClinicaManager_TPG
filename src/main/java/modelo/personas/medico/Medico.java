package modelo.personas.medico;

import modelo.personas.Persona;
import util.Domicilio;

import java.util.Random;
/**
 * Clase abstracta Medico que representa a un medico
 * Contiene atributos nroMatricula y sueldoBase
 * Hereda de Persona e implementa la interfaz IMedico
 */
public abstract class Medico extends Persona implements IMedico
{
    private final String nroMatricula;
    protected static double sueldoBase = 20000;

    /**
     * Constructor de Medico
     * <b>pre:</b> nombre, apellido, dni, domicilio, telefono y nroMatricula no deben ser nulos ni vacios.
     * <b>post:</b> se crea un medico con los atributos proporcionados.
     * @param nombre
     * @param apellido
     * @param dni
     * @param domicilio
     * @param telefono
     * @param nroMatricula
     */
    public Medico(String nombre, String apellido, String dni, Domicilio domicilio, String telefono, String nroMatricula)
    {
        super(nombre, apellido, dni, domicilio, telefono);
        this.nroMatricula = nroMatricula;
    }

    /**
     * Metodo publico String para preguntar cual es el numero de matricula del medico.
     * @return nroMatricula del medico
     */
    @Override
    public String getNroMatricula()
    {
        return nroMatricula;
    }

//    public static double getSueldoBase() {
//        return sueldoBase;
//    }
//
//    public static void setSueldoBase(double sueldoBase) {
//        Medico.sueldoBase = sueldoBase;
//    }

}
