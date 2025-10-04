package modelo.personas.medico.especialidades;

import modelo.personas.medico.Medico;
import util.Domicilio;

public class MedicoClinico extends Medico {
    private static final double INCREMENTO = 1.05;

    public MedicoClinico(String nombre, String apellido, String dni , Domicilio domicilio,String telefono, String nroMatricula){
        super(nombre, apellido, dni, domicilio,telefono, nroMatricula);
    }
    @Override
    public double getSueldo(){
        return Medico.getSueldoBase() *  INCREMENTO;
    }
    @Override
    public String toString() {
        return super.toString()+"Especialidad: Medico clinico";
    }
}
