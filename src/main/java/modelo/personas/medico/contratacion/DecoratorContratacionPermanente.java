package modelo.personas.medico.contratacion;

import modelo.personas.medico.Medico;
import modelo.personas.medico.postgrado.DecoratorPostgrado;

public class DecoratorContratacionPermanente extends DecoratorContratacion {
    private final static double INCREMENTO = 1.1;
    public DecoratorContratacionPermanente(DecoratorPostgrado medico) {
        super(medico);
    }

    @Override
    public double getSueldo(){
        return this.encapsulado.getSueldo() *  INCREMENTO;
    }
}
