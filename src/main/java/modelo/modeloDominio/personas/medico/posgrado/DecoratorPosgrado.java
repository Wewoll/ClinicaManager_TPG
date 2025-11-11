package modelo.modeloDominio.personas.medico.posgrado;

import modelo.modeloDominio.personas.medico.IMedico;
import modelo.modeloDominio.personas.medico.Medico;

/**
 * Clase abstracta que implementa el patrón Decorator para agregar posgrados a un médico.
 * Contiene un atributo encapsulado de tipo Medico.
 * Implementa los métodos de la interfaz IMedico.
 */
public abstract class DecoratorPosgrado implements IMedico {
    Medico encapsulado;

    /**
     * Constructor de la clase DecoratorPosgrado.
     * <b>pre:</b> medico no debe ser nulo.
     * <b>post:</b> se crea un DecoratorPosgrado con el medico
     * @param medico El medico al que se le aplicará el posgrado.
     */
    public DecoratorPosgrado(Medico medico) {
        assert medico != null;
        this.encapsulado = medico;
    }

    /**
     * Metodo que retorna el numero de matricula del medico.
     * @return El numero de matricula del medico.
     */
    @Override
    public String getNroMatricula() {
        return this.encapsulado.getNroMatricula();
    }

    /**
     * Metodo que retorna el nombre del medico.
     * @return El nombre del medico.
     */
    @Override
    public String getNombre() {
        return this.encapsulado.getNombre();
    }
    /**
     * Metodo que retorna el apellido del medico.
     * @return El apellido del medico.
     */
    @Override
    public String getApellido() {
        return this.encapsulado.getApellido();
    }
    @Override
    public String toString()
    {
        return  this.encapsulado.toString();
    }
}
