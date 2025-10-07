package util.Excepciones;
/**
 * Excepción lanzada cuando se intenta acceder a una especialidad médica que no existe.
 */
public class EspecialidadNoExistenteException extends Exception {

    /**
     * Constructor de la excepción.
     * <b>pre:</b> mensaje != null
     * <b>post:</b> Crea una nueva excepción con el mensaje proporcionado
     * @param mensaje Mensaje de error.
     */
    public EspecialidadNoExistenteException(String mensaje) {
        super(mensaje);
    }
}
