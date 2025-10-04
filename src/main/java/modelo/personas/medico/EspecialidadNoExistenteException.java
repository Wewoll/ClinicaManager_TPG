package modelo.personas.medico;

public class EspecialidadNoExistenteException extends Exception {
    public EspecialidadNoExistenteException(String mensaje) {
        super(mensaje);
    }
}
