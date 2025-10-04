package modelo.personas.medico.posgrado;

import modelo.personas.medico.Medico;

public class DecoratorPosgradoGrado extends DecoratorPosgrado {
    private static final double INCREMENTO = 1;
    
    public DecoratorPosgradoGrado(Medico medico){
        super(medico);
    }
    @Override
    public double getSueldo(){
        return this.encapsulado.getSueldo() *  INCREMENTO;
    }
}
