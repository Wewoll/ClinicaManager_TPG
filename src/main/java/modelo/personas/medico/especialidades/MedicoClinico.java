package modelo.personas.medico.especialidades;

import modelo.personas.medico.Medico;
import util.Domicilio;

/**
 * Clase que representa a un médico clínico.
 * Hereda de la clase Medico y aplica un incremento del 5% al sueldo base
 */
public class MedicoClinico extends Medico {
    private static final double INCREMENTO = 1.05;

    /**
     * Constructor de la clase MedicoClinico.
     * <b>pre:</b> Los parámetros nombre, apellido, dni, domicilio, telefono y nroMatricula no deben ser nulos o vacíos.
     * <b>post:</b> Se crea un objeto MedicoClinico con los datos proporcionados.
     * @param nombre
     * @param apellido
     * @param dni
     * @param domicilio
     * @param telefono
     * @param nroMatricula
     */
    public MedicoClinico(String nombre, String apellido, String dni , Domicilio domicilio,String telefono, String nroMatricula){
        super(nombre, apellido, dni, domicilio,telefono, nroMatricula);
    }

    @Override
    public double getSueldo(){
        return Medico.getSueldoBase() *  INCREMENTO;
    }
    /**
     * Método toString que devuelve una representación en cadena del médico clínico.
     * Incluye la especialidad del médico.
     * @return Cadena con la información del médico y su especialidad.
     */
    @Override
    public String toString() {
        return super.toString()+"Especialidad: Medico clinico";
    }
}
