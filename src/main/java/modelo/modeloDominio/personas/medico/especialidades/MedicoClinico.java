package modelo.modeloDominio.personas.medico.especialidades;

import modelo.modeloDominio.personas.medico.IMedico;
import modelo.modeloDominio.personas.medico.Medico;
import modelo.modeloDominio.util.Domicilio;

public class MedicoClinico extends Medico {
    private static final double INCREMENTO = 1.05;

    public MedicoClinico(String nombre, String apellido, String dni , Domicilio domicilio,String telefono, String nroMatricula){
        super(nombre, apellido, dni, domicilio,telefono, nroMatricula);
    }
    @Override
    public double getSueldo(){
        return IMedico.getSueldoBase() *  INCREMENTO;
    }
    @Override
    public String toString() {
        return "Medico clinico";
    }
}
