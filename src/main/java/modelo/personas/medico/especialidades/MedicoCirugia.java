package modelo.personas.medico.especialidades;

import modelo.personas.medico.Medico;
import util.Domicilio;

public class MedicoCirugia extends Medico {
    private static final double INCREMENTO = 1.1;

    public MedicoCirugia(String nombre, String apellido, String dni , Domicilio domicilio,String telefono, String nroMatricula){
        super(nombre, apellido, dni, domicilio, telefono, nroMatricula);
    }


    @Override
    public double getSueldo(){
        return Medico.getSueldoBase() *  INCREMENTO;
    }

    @Override
    public String toString() {
        return super.toString()+"Especialidad: Medico cirugia";
    }
}
