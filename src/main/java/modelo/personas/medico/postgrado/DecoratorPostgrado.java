package modelo.personas.medico.postgrado;

import modelo.personas.medico.IMedico;
import modelo.personas.medico.Medico;

public abstract class DecoratorPostgrado implements IMedico {
    Medico encapsulado;

    public DecoratorPostgrado(Medico medico) {
        this.encapsulado = medico;
    }
}
