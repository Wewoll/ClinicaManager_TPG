package modelo.personas.medico;

import modelo.personas.Persona;
import util.Domicilio;

public abstract class Medico extends Persona implements IMedico{
    private final String nroMatricula;
    protected static double sueldoBase = 20000;
    public Medico(String nombre, String apellido, String dni, Domicilio domicilio,String telefono, String nroMatricula) {
        super(nombre, apellido, dni, domicilio,telefono);
        this.nroMatricula = nroMatricula;
    }
    @Override
    public String getNroMatricula() {
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
