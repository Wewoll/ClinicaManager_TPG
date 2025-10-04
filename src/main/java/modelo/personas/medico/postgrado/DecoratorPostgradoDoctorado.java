package modelo.personas.medico.postgrado;

import modelo.personas.medico.Medico;

/**
 * Clase concreta que representa un decorador para médicos con título de doctorado.
 * Hereda de DecoratorPostgrado y aplica un incremento específico al sueldo del médico.
 * El incremento es del 10% sobre el sueldo base al implementar getSueldo().
 */
public class DecoratorPostgradoDoctorado extends DecoratorPostgrado {
    private static final double INCREMENTO = 1.1;
    public DecoratorPostgradoDoctorado(Medico medico) {
        super(medico);
    }
    @Override
    public double getSueldo(){
        return this.encapsulado.getSueldo() *  INCREMENTO;
    }
}
