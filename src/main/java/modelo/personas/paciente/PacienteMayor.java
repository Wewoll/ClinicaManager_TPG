package modelo.personas.paciente;

import java.time.LocalDate;

public class PacienteMayor extends Paciente{

    public PacienteMayor(String nombre, String apellido, String dni, util.Domicilio domicilio, String telefono, String nroHistoriaMedica, LocalDate fechaIngreso)
    {
        super(nombre, apellido, dni, domicilio, telefono, nroHistoriaMedica, fechaIngreso);
    }
    @Override
    public boolean prioridad(Paciente paciente){
        return paciente.prioridadConMayor();
    }
    @Override
    public boolean prioridadConJoven(){
        return false; // el joven va para la sala
    }
    @Override
    public boolean prioridadConMayor(){
        return false; // el mayor que estaba se queda
    }
    @Override
    public boolean prioridadConNino(){
        return true; // el mayor va para la sala
    }
    @Override
    public String toString() {
        return super.toString()+"Tipo de paciente: Mayor";
    }
}
