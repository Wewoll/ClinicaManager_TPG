package vista;
/**
 * Excepción lanzada cuando los datos del asociado proporcionados son incorrectos.
 */
public class datosAsociadoDTOIncorrectoException extends Exception {
    public datosAsociadoDTOIncorrectoException(String message) {
        super(message);
    }
}
