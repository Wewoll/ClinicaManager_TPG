package modelo.clinica.modulos.ingreso;

import modelo.personas.paciente.Paciente;

public class PacienteNoRegistradoException extends Exception
{
    Paciente paciente;
    public PacienteNoRegistradoException(Paciente p)
    {
        super("Paciente no registrado: " + p.getNombre() + " " + p.getApellido() + ", DNI: " + p.getDni());
    }

    public Paciente getPaciente() {
        return paciente;
    }
}
