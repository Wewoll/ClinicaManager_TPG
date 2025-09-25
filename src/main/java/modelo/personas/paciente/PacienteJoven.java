package modelo.personas.paciente;

public class PacienteJoven extends Paciente{

    public PacienteJoven(String nombre, String apellido, String dni , util.Domicilio domicilio,String telefono, String nroHistoriaMedica){
        super(nombre, apellido, dni, domicilio, telefono, nroHistoriaMedica);
    }

    @Override
    public String toString() {
        return super.toString()+"Tipo de paciente: Joven";
    }
}
