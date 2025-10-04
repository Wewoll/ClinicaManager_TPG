package modelo.personas.medico.contratacion;

import modelo.personas.medico.IMedico;
import modelo.personas.medico.posgrado.DecoratorPosgrado;

public abstract class DecoratorContratacion implements IMedico {
    protected DecoratorPosgrado encapsulado;

    public DecoratorContratacion(DecoratorPosgrado medico) {
        this.encapsulado = medico;
    }
}
