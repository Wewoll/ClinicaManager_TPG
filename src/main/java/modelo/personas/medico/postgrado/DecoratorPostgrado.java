package modelo.personas.medico.postgrado;

import modelo.personas.medico.IMedico;
import modelo.personas.medico.Medico;
    /**
     * Clase abstracta que implementa el patrón Decorator para agregar funcionalidades
     * relacionadas con títulos de posgrado a un médico.
     * Hereda de la interfaz IMedico y contiene una referencia a un objeto Medico.
     */
public abstract class DecoratorPostgrado implements IMedico {
    Medico encapsulado;

    /**
     * Constructor de la clase DecoratorPostgrado.
     * <b>pre:</b> El objeto Medico no debe ser nulo.
     * <b>post:</b> Se crea un objeto DecoratorPostgrado que envuelve al médico proporcionado.
     * @param medico Objeto Medico a ser decorado.
     */
    public DecoratorPostgrado(Medico medico) {
        this.encapsulado = medico;
    }
}
