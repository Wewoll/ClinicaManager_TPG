package modelo.personas.medico.contratacion;

import modelo.personas.medico.IMedico;
import modelo.personas.medico.postgrado.DecoratorPostgrado;

public abstract class DecoratorContratacion implements IMedico {
    protected DecoratorPostgrado encapsulado;

    public DecoratorContratacion(DecoratorPostgrado medico) {
        this.encapsulado = medico;
    }
}
