package modelo.personas.medico.contratacion;

import modelo.personas.medico.IMedico;
import modelo.personas.medico.posgrado.DecoratorPosgrado;

/**
 * Clase abstracta DecoratorContratacion que implementa la interfaz IMedico
 * Contiene un atributo encapsulado de tipo DecoratorPosgrado
 */
public abstract class DecoratorContratacion implements IMedico
{
    protected DecoratorPosgrado encapsulado;

    /**
     * Constructor de DecoratorContratacion
     * <b>pre:</b> medico no debe ser nulo.
     * <b>post:</b> se crea un DecoratorContratacion con el medico proporcionado.
     *
     * @param medico
     */
    public DecoratorContratacion(DecoratorPosgrado medico)
    {
        this.encapsulado = medico;
    }

    @Override
    public String getNroMatricula()
    {
        return this.encapsulado.getNroMatricula();
    }

    @Override
    public String getNombre()
    {
        return this.encapsulado.getNombre();
    }

    @Override
    public String getApellido()
    {
        return this.encapsulado.getApellido();
    }

}
