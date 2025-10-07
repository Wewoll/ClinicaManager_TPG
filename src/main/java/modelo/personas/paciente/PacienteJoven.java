package modelo.personas.paciente;

import java.time.LocalDate;

public class PacienteJoven extends Paciente{

    public PacienteJoven(String nombre, String apellido, String dni , util.Domicilio domicilio,String telefono, String nroHistoriaMedica, LocalDate fechaIngreso){
        super(nombre, apellido, dni, domicilio, telefono, nroHistoriaMedica, fechaIngreso);
    }
    @Override
    public boolean prioridad(Paciente paciente){
        return paciente.prioridadConJoven();
    }
    @Override
    public boolean prioridadConJoven(){
        return false; // se queda el joven que llego antes
    }
    @Override
    public boolean prioridadConMayor(){
        return false; // el joven va para el patio
    }
    @Override
    public boolean prioridadConNino(){
        return false; // el joven va para el patio
    }
    @Override
    public String toString() {
        return super.toString()+"Tipo de paciente: Joven";
    }
}
