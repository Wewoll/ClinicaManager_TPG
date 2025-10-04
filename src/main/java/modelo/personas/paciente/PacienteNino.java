package modelo.personas.paciente;

public class PacienteNino extends Paciente{

    public PacienteNino(String nombre, String apellido, String dni , util.Domicilio domicilio, String telefono, String nroHistoriaMedica){
        super(nombre, apellido, dni, domicilio, telefono, nroHistoriaMedica);
    }
    @Override
    public boolean prioridad(Paciente paciente){
        return paciente.prioridadConNino();
    }
    @Override
    public boolean prioridadConJoven(){
        return true; // la sala queda para ninio
    }
    @Override
    public boolean prioridadConMayor(){
        return false; // el ninio va para el patio
    }
    @Override
    public boolean prioridadConNino(){
        return false; // el ninio anterior va para el patio
    }
    @Override
    public String toString() {
        return super.toString()+"Tipo de paciente: Nino";
    }
}
