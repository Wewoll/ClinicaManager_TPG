package util.Excepciones;

import modelo.personas.paciente.Paciente;
/**
 * Excepción lanzada cuando se intenta internar a un paciente que ya está internado.
 * Contiene información sobre el paciente que ya está internado.
 */
public class PacienteYaInternado extends Exception
{
    Paciente paciente;

    /**
     * Constructor de la excepción.
     * <b>pre:</b> p != null
     * <b>post:</b> Crea una nueva excepción con un mensaje indicando que el paciente ya fue internado.
     * @param p Paciente que ya está internado.
     */
    public PacienteYaInternado(Paciente p)
    {
        super("El paciente ya fue internado");
        this.paciente = p;
    }
    /**
     * Obtiene el paciente que ya está internado.
     * @return Paciente ya internado.
     */
    public Paciente getPaciente() {
        return paciente;
    }
}
