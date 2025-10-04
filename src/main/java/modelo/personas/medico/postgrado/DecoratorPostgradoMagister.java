package modelo.personas.medico.postgrado;

import modelo.personas.medico.Medico;

/**
 * Decorador que representa un título de posgrado de magíster.
 * Aplica un incremento del 5% al sueldo del médico.
 * Hereda de DecoratorPostgrado.
 */
public class DecoratorPostgradoMagister extends DecoratorPostgrado {
    private static final double INCREMENTO = 1.05;

    public DecoratorPostgradoMagister(Medico medico) {
        super(medico);
    }
    @Override
    public double getSueldo(){
        return this.encapsulado.getSueldo() *  INCREMENTO;
    }
}
