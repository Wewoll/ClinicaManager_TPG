package modelo.personas.medico;
    /**
     * Excepción lanzada cuando se intenta acceder a una contratación que no existe.
     */
public class ContratacionNoExistenteException extends Exception {
    public ContratacionNoExistenteException(String message) {
        super(message);
    }
}
