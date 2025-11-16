package vista;

/**
 * Excepción lanzada cuando los datos de simulación proporcionados son incorrectos.
 */
public class datosSimulacionIncorrectosException extends Exception {
    public datosSimulacionIncorrectosException(String message) {
        super(message);
    }
}
