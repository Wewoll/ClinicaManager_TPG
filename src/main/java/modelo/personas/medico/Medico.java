package modelo.personas.medico;

import modelo.personas.Persona;
import util.Domicilio;

public class Medico extends Persona implements IMedico{
    private final String nroMatricula;

    public Medico(String nombre, String apellido, String dni, Domicilio domicilio,String telefono, String nroMatricula) {
        super(nombre, apellido, dni, domicilio,telefono);
        this.nroMatricula = nroMatricula;
    }
}
