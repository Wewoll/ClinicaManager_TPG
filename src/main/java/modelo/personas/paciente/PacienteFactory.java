package modelo.personas.paciente;

import util.Domicilio;

import java.time.LocalDate;

public class PacienteFactory
{
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
