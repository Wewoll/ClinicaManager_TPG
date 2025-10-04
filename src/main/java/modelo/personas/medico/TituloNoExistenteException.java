package modelo.personas.medico;
    /**
     * Excepción lanzada cuando se intenta asignar un título de posgrado que no existe.
     */
public class TituloNoExistenteException extends Exception {
    public TituloNoExistenteException(String mensaje) {
        super(mensaje);
    }
}
