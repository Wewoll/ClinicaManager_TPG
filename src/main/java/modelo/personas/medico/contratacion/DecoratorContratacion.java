package modelo.personas.medico.contratacion;

import modelo.personas.medico.IMedico;
import modelo.personas.medico.Medico;

public abstract class DecoratorContratacion implements IMedico {
    protected Medico encapsulado;

    public DecoratorContratacion(Medico medico) {
        this.encapsulado = medico;
    }
}
