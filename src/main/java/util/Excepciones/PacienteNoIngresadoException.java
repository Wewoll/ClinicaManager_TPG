package util.Excepciones;

import modelo.personas.paciente.Paciente;

public class PacienteNoIngresadoException extends Exception
{
    Paciente p;

    public PacienteNoIngresadoException(Paciente paciente)
    {
        super("Paciente nunca fue ingresado");
        this.p = paciente;
    }

    public Paciente getPaciente()
    {
        return p;
    }
}
