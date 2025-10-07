package util.Excepciones;

import modelo.personas.paciente.Paciente;

public class PacienteYaInternado extends Exception
{
    Paciente paciente;
    public PacienteYaInternado(Paciente p)
    {
        super("El paciente ya fue internado");
        this.paciente = p;
    }

    public Paciente getPaciente() {
        return paciente;
    }
}
