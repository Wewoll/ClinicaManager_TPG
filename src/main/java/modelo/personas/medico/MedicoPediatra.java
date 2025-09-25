package modelo.personas.medico;

import util.Domicilio;

public class MedicoPediatra extends Medico{
    // atributos para el calculo de honorarios

    public MedicoPediatra(String nombre, String apellido, String dni , Domicilio domicilio, String telefono, String nroMatricula){
        super(nombre, apellido, dni, domicilio,telefono, nroMatricula);
    }

    @Override
    public String toString() {
        return super.toString()+"Especialidad: Medico pediatro";
    }
}
