package modelo.personas.medico.contratacion;

import modelo.personas.medico.posgrado.DecoratorPosgrado;

public class DecoratorContratacionResidente extends DecoratorContratacion {
    private static final double INCREMENTO = 1.05;
    public DecoratorContratacionResidente(DecoratorPosgrado medico) {
        super(medico);
    }
    @Override
    public double getSueldo() {
        return this.encapsulado.getSueldo() * INCREMENTO;
    }
}
