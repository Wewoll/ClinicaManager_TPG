package modelo.modeloDominio.personas;


import modelo.modeloDominio.util.Domicilio;

import java.util.Observable;

/**
 * Clasee abastracta Persona que representa a una persona
 * Contiene atributos nombre, apellido, dni, domicilio y telefono
 */
public abstract class PersonaObservable extends Observable
{
    private String nombre;
    private String apellido;
    private String dni;
    private Domicilio domicilio;
    private String telefono;

    /**
     * Constructor de Persona
     * <b>pre:</b> nombre, apellido, dni, domicilio y telefono no deben ser nulos ni vacios.
     * <b>post:</b> se crea una persona con los atributos proporcionados.
     * @param nombre nombre de la persona
     * @param apellido apellido de la persona
     * @param dni dni de la persona
     * @param domicilio domicilio de la persona
     * @param telefono telefono de la persona
     */
    public PersonaObservable(String nombre, String apellido, String dni, Domicilio domicilio, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.domicilio = domicilio;
        this.telefono = telefono;
    }

    /**
     * Metodo publico String para preguntar cual es el nombre de la persona.
     *
     * @return nombre de la persona
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo publico void para setear el nombre de la persona.
     * <b>pre:</b> nombre no debe ser nulo ni vacio.
     * <b>post:</b> se setea el nombre de la persona.
     *
     * @param nombre
     */
    public void setNombre(String nombre)
    {
        assert  nombre != null;
        this.nombre = nombre;
    }

    /**
     * Metodo publico String para preguntar cual es el apellido de la persona.
     *
     * @return apellido de la persona
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Metodo publico void para setear el apellido de la persona.
     * <b>pre:</b> apellido no debe ser nulo ni vacio.
     * <b>post:</b> se setea el apellido de la persona.
     *
     * @param apellido
     */
    public void setApellido(String apellido)
    {
        assert   apellido != null;
        this.apellido = apellido;
    }

    /**
     * Metodo publico String para preguntar cual es el dni de la persona.
     *
     * @return dni de la persona
     */
    public String getDni()
    {
        return dni;
    }

    /**
     * Metodo publico Domicilio para preguntar cual es el domicilio de la persona.
     *
     * @return domicilio de la persona
     */
    public Domicilio getDomicilio()
    {
        return domicilio;
    }

    /**
     * Metodo publico void para setear el domicilio de la persona.
     * <b>pre:</b> domicilio no debe ser nulo.
     * <b>post:</b> se setea el domicilio de la persona.
     *
     * @param domicilio
     */
    public void setDomicilio(Domicilio domicilio)
    {
        assert  domicilio != null;
        this.domicilio = domicilio;
    }

    /**
     * Metodo publico String para preguntar cual es el telefono de la persona.
     *
     * @return telefono de la persona
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Metodo publico void para setear el telefono de la persona.
     * <b>pre:</b> telefono no debe ser nulo ni vacio.
     * <b>post:</b> se setea el telefono de la persona.
     *
     * @param telefono
     */
    public void setTelefono(String telefono)
    {
        assert  telefono != null;
        this.telefono = telefono;
    }
}

