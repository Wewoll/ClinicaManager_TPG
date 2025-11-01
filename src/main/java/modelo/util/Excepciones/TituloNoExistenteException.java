package modelo.util.Excepciones;
/**
 * Excepción lanzada cuando se intenta asignar un título que no existe.
 */
public class TituloNoExistenteException extends Exception {
    /**
     * Constructor de la excepción.
     * <b>pre:</b> mensaje != null
     * <b>post:</b> Crea una nueva excepción con el mensaje proporcionado
     * @param mensaje Mensaje de error.
     */
    public TituloNoExistenteException(String mensaje) {
        super(mensaje);
    }
}
