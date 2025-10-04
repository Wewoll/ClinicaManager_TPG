package modelo.personas.medico.contratacion;

import modelo.personas.medico.Medico;
import modelo.personas.medico.postgrado.DecoratorPostgrado;

public class DecoratorContratacionResidente extends DecoratorContratacion {
    private static final double INCREMENTO = 1.05;
    public DecoratorContratacionResidente(DecoratorPostgrado medico) {
        super(medico);
    }
    @Override
    public double getSueldo() {
        return this.encapsulado.getSueldo() * INCREMENTO;
    }
}
