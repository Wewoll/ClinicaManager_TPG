package modelo.personas.medico;

import modelo.personas.Persona;
import modelo.personas.paciente.Paciente;
import util.Domicilio;

public abstract class Medico extends Persona implements IMedico{
    private final String nroMatricula;
    private static double sueldoBase = 20000;
    public Medico(String nombre, String apellido, String dni, Domicilio domicilio,String telefono, String nroMatricula) {
        super(nombre, apellido, dni, domicilio,telefono);
        this.nroMatricula = nroMatricula;
    }
    public String getNroMatricula() {
        return nroMatricula;
    }

    public static double getSueldoBase() {
        return sueldoBase;
    }

    public static void setSueldoBase(double sueldoBase) {
        Medico.sueldoBase = sueldoBase;
    }

    public void atenderPaciente(Paciente paci) {
        //preguntar como seria este metodo
    }
}
