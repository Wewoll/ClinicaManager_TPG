package modelo.modeloDominio.personas.medico.contratacion;

import modelo.modeloDominio.personas.medico.IMedico;
import modelo.modeloDominio.personas.medico.posgrado.DecoratorPosgrado;

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

    /**
     * Metodo publico String para preguntar cual es el numero de matricula del medico.
     *
     * @return nroMatricula del medico
     */
    @Override
    public String getNroMatricula()
    {
        return this.encapsulado.getNroMatricula();
    }

    /**
     * Metodo publico String para preguntar cual es el nombre del medico.
     *
     * @return nombre del medico
     */
    @Override
    public String getNombre()
    {
        return this.encapsulado.getNombre();
    }

    /**
     * Metodo publico String para preguntar cual es el apellido del medico.
     *
     * @return apellido del medico
     */
    @Override
    public String getApellido()
    {
        return this.encapsulado.getApellido();
    }

    @Override
    public String toString()
    {
        return this.encapsulado.toString();
    }
}
