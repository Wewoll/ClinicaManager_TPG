package modelo.personas.medico.postgrado;

import modelo.personas.medico.Medico;

/**
 * Decorador que representa un título de posgrado de grado.
 * Aplica un incremento del 0% al sueldo del médico.
 * Hereda de DecoratorPostgrado.
 */
public class DecoratorPostgradoGrado extends DecoratorPostgrado {
    private static final double INCREMENTO = 1;
    public DecoratorPostgradoGrado(Medico medico){
        super(medico);
    }
    @Override
    public double getSueldo(){
        return this.encapsulado.getSueldo() *  INCREMENTO;
    }
}
