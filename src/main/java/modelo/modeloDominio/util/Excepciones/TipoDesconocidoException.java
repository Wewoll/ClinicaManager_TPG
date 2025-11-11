package modelo.modeloDominio.util.Excepciones;
/**
 * Excepción lanzada cuando se encuentra un tipo desconocido.
 */
public class TipoDesconocidoException extends Exception {
    /**
     * Constructor de la excepción.
     * <b>pre:</b> message != null
     * <b>post:</b> Crea una nueva excepción con el mensaje proporcionado.
     * @param message Mensaje de error.
     */
    public TipoDesconocidoException(String message) {
        super(message);
    }
}
