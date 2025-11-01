package modelo.util.Excepciones;

/**
 * Excepción lanzada cuando un paciente no tiene consultas médicas registradas.
 */
public class PacienteSinConsultasMedicasException extends Exception {

    /**
     * Constructor de la excepción.
     * <b>pre:</b> mensaje != null
     * <b>post:</b> Crea una nueva excepción con el mensaje proporcionado
     * @param mensaje Mensaje de error.
     */
    public PacienteSinConsultasMedicasException(String mensaje) {
        super(mensaje);
    }
}
