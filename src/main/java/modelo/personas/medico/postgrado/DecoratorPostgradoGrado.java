package modelo.personas.medico.postgrado;

import modelo.personas.medico.Medico;

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
