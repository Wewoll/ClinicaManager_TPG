package modelo.personas.medico.especialidades;

import modelo.personas.medico.Medico;
import util.Domicilio;

public class MedicoPediatra extends Medico {
    // atributos para el calculo de honorarios

    public MedicoPediatra(String nombre, String apellido, String dni , Domicilio domicilio, String telefono, String nroMatricula){
        super(nombre, apellido, dni, domicilio,telefono, nroMatricula);
    }

    @Override
    public String toString() {
        return super.toString()+"Especialidad: Medico pediatro";
    }

    public double getSueldo() {
        return getSueldoBase() * 1.07;
    }
}
