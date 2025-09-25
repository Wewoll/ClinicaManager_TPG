package modelo.personas.paciente;

import modelo.personas.Persona;

public abstract class Paciente extends Persona {
    private final String nroHistoriaMedica;

    public Paciente(String nombre, String apellido, String dni, util.Domicilio domicilio,String telefono, String nroHistoriaMedica) {
        super(nombre, apellido, dni, domicilio, telefono);
        this.nroHistoriaMedica = nroHistoriaMedica;
    }
    public String getNroHistoriaMedica() {
        return nroHistoriaMedica;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "nroHistoriaMedica='" + nroHistoriaMedica + '\'' +
                '}';
    }
}
