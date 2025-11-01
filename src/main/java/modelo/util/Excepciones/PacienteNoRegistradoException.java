package modelo.util.Excepciones;

import modelo.personas.paciente.Paciente;

/**
 * Excepción lanzada cuando se intenta acceder a un paciente que no está registrado en el sistema.
 */
public class PacienteNoRegistradoException extends Exception
{
    Paciente paciente;

    /**
     * Constructor de la excepción.
     * <b>pre:</b> p != null
     * <b>post:</b> Crea una nueva excepción con un mensaje que incluye el nombre, apellido y DNI del paciente no registrado.
     * @param p Paciente que no está registrado.
     */
    public PacienteNoRegistradoException(Paciente p)
    {
        super("Paciente no registrado: " + p.getNombre() + " " + p.getApellido() + ", DNI: " + p.getDni());
    }
    /**
     * Obtiene el paciente que no está registrado.
     * @return Paciente no registrado.
     */
    public Paciente getPaciente() {
        return paciente;
    }
}
