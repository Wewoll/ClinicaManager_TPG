package util.Excepciones;
/**
 * Excepción lanzada cuando se intenta acceder a una contratación que no existe.
 */
public class ContratacionNoExistenteException extends Exception
{
    /**
     * Constructor de la excepción.
     * <b>pre:</b> message != null
     * <b>post:</b> Crea una nueva excepción con el mensaje proporcionado
      * @param message Mensaje de error.
     */
    public ContratacionNoExistenteException(String message) {
        super(message);
    }
}
