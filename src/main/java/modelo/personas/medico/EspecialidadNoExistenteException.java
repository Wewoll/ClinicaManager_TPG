package modelo.personas.medico;
    /**
     * Excepción lanzada cuando se intenta asignar una especialidad que no existe.
     */
public class EspecialidadNoExistenteException extends Exception {
    public EspecialidadNoExistenteException(String mensaje) {
        super(mensaje);
    }
}
