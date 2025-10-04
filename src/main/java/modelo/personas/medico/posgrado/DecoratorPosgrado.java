package modelo.personas.medico.posgrado;

import modelo.personas.medico.IMedico;
import modelo.personas.medico.Medico;

public abstract class DecoratorPosgrado implements IMedico {
    Medico encapsulado;

    public DecoratorPosgrado(Medico medico) {
        this.encapsulado = medico;
    }
}
