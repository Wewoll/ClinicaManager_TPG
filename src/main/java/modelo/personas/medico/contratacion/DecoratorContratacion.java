package modelo.personas.medico.contratacion;

import modelo.personas.medico.IMedico;
import modelo.personas.medico.postgrado.DecoratorPostgrado;
/**
 * Clase abstracta que representa un decorador para la contratación de un médico.
 * Implementa la interfaz IMedico y contiene una referencia a un objeto DecoratorPostgrado.
 */
public abstract class DecoratorContratacion implements IMedico {
    protected DecoratorPostgrado encapsulado;
    /**
     * Constructor de la clase DecoratorContratacion.
     * <b>pre:</b> El objeto DecoratorPostgrado no debe ser nulo.
     * <b>post:</b> Se crea un objeto DecoratorContratacion que envuelve al DecoratorPostgrado proporcionado.
     * @param medico Objeto DecoratorPostgrado a ser decorado.
     */
    public DecoratorContratacion(DecoratorPostgrado medico) {
        this.encapsulado = medico;
    }
}
