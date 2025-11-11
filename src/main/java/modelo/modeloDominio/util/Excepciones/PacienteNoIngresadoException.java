package modelo.modeloDominio.util.Excepciones;

import modelo.modeloDominio.personas.paciente.Paciente;
/**
 * Excepción lanzada cuando se intenta acceder a un paciente que nunca fue ingresado en el sistema.
 * Contiene información sobre el paciente que causó la excepción.
 */
public class PacienteNoIngresadoException extends Exception
{
    Paciente p;
    /**
     * Constructor de la excepción.
     * <b>pre:</b> paciente != null
     * <b>post:</b> Crea una nueva excepción con un mensaje indicando que el paciente nunca fue ingresado.
     * @param paciente Paciente que nunca fue ingresado.
     */
    public PacienteNoIngresadoException(Paciente paciente)
    {
        super("Paciente nunca fue ingresado");
        this.p = paciente;
    }
    /**
     * Obtiene el paciente que nunca fue ingresado.
     * @return Paciente que nunca fue ingresado.
     */
    public Paciente getPaciente()
    {
        return p;
    }
}
