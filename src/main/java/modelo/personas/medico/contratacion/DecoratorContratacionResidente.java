package modelo.personas.medico.contratacion;

import modelo.personas.medico.Medico;
import modelo.personas.medico.postgrado.DecoratorPostgrado;
/**
 * Decorador concreto que representa una contratación de residente.
 * Aplica un incremento del 5% al sueldo del médico.
 * Hereda de DecoratorContratacion.
 */
public class DecoratorContratacionResidente extends DecoratorContratacion {
    private static final double INCREMENTO = 1.05;
    /**
     * Constructor que recibe un objeto DecoratorPostgrado.
     * <b>pre:</b> medico debe ser una instancia válida de DecoratorPostgrado.
     * <b>post:</b> Se crea una instancia de DecoratorContratacionResidente que decora al médico proporcionado.
     * @param medico El objeto DecoratorPostgrado a ser decorado.
     */
    public DecoratorContratacionResidente(DecoratorPostgrado medico) {
        super(medico);
    }
    @Override
    public double getSueldo() {
        return this.encapsulado.getSueldo() * INCREMENTO;
    }
}
