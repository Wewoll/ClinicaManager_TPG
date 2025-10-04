package modelo.personas.medico.contratacion;

import modelo.personas.medico.posgrado.DecoratorPosgrado;

public class DecoratorContratacionPermanente extends DecoratorContratacion {
    private final static double INCREMENTO = 1.1;
    public DecoratorContratacionPermanente(DecoratorPosgrado medico) {
        super(medico);
    }

    @Override
    public double getSueldo(){
        return this.encapsulado.getSueldo() *  INCREMENTO;
    }
}
