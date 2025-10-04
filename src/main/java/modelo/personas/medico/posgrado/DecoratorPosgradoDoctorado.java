package modelo.personas.medico.posgrado;

import modelo.personas.medico.Medico;

public class DecoratorPosgradoDoctorado extends DecoratorPosgrado {
    private static final double INCREMENTO = 1.1;
    public DecoratorPosgradoDoctorado(Medico medico) {
        super(medico);
    }
    @Override
    public double getSueldo(){
        return this.encapsulado.getSueldo() *  INCREMENTO;
    }
}
