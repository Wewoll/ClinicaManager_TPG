package modelo.personas.paciente;

public class PacienteMayor extends Paciente{

    public PacienteMayor(String nombre, String apellido, String dni , util.Domicilio domicilio, String telefono, String nroHistoriaMedica){
        super(nombre, apellido, dni, domicilio, telefono, nroHistoriaMedica);
    }

    @Override
    public String toString() {
        return super.toString()+"Tipo de paciente: Mayor";
    }
}
