package modelo.personas;

import util.Domicilio;

/**
 * Clase abstracta que representa a una persona.
 * Contiene como atributos el nombre, apellido, dni, domicilio y teléfono.
 */
public abstract class Persona {
    private String nombre;
    private String apellido;
    private String dni;
    private Domicilio domicilio;
    private String telefono;
    /** Constructor de la clase Persona.
     * <b>pre:</b> Los datos de la persona deben ser válidos (Strings y domicilio !=null).
     * <b>post:</b> Se crea un objeto Persona con los datos proporcionados.
     * @param nombre Nombre de la persona.
     * @param apellido Apellido de la persona.
     * @param dni DNI de la persona.
     * @param domicilio Domicilio de la persona.
     * @param telefono Teléfono de la persona.
     */
    public Persona(String nombre, String apellido, String dni, Domicilio domicilio, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.domicilio = domicilio;
        this.telefono = telefono;
    }

    /**
     * Getter del nombre.
     * @return Nombre de la persona.
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Getter del teléfono.
     * @return Teléfono de la persona.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * getter del apellido
     * @return apellido de la persona
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * getter del dni
     * @return dni de la persona
     */
    public String getDni() {
        return dni;
    }

    /**
     * getter del domicilio
     * @return domicilio de la persona
     */
    public Domicilio getDomicilio() {
        return domicilio;
    }

    /**
     * setter del domicilio en caso de cambio
     * pre: domicilio != null
     * post: se actualiza el domicilio de la persona
     */
    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }
}
