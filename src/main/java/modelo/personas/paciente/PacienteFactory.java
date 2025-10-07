package modelo.personas.paciente;

import util.Domicilio;

import java.time.LocalDate;

/**
 * Clase PacienteFactory que crea pacientes segun su edad
 */
public class PacienteFactory
{
    /**
     * Metodo que crea un paciente segun su edad
     * <b>pre:</b> edad debe ser mayor o igual a 0
     * <b>post:</b> se retorna una instancia de Paciente correspondiente a la edad
     * @param dni
     * @param nombre
     * @param apellido
     * @param domicilio
     * @param telefono
     * @param nroHistoriaMedica
     * @param edad
     * @param fechaIngreso
     * @return Paciente (PacienteNino, PacienteJoven o PacienteMayor)
     */
    public Paciente crearPaciente(String dni, String nombre, String apellido, Domicilio domicilio, String telefono, String nroHistoriaMedica, int edad, LocalDate fechaIngreso)
    {
        if (edad < 15)
        {
            return new PacienteNino(nombre, apellido, dni, domicilio, telefono, nroHistoriaMedica, fechaIngreso);
        }
        else if (edad < 60)
        {
            return new PacienteJoven(nombre, apellido, dni, domicilio, telefono, nroHistoriaMedica, fechaIngreso);
        }
        else
        {
            return new PacienteMayor(nombre, apellido, dni, domicilio, telefono, nroHistoriaMedica, fechaIngreso);
        }

    }
}
