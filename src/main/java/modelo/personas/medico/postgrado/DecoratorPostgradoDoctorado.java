package modelo.personas.medico.postgrado;

import modelo.personas.medico.Medico;

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
