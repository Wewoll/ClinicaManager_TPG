package modelo.personas.paciente;

import util.Domicilio;

public class PacienteFactory
{
    public Paciente crearPaciente(String dni, String nombre, String apellido, Domicilio domicilio, String telefono,String nroHistoriaMedica, int edad)
    {
        if (edad < 15)
        {
            return new PacienteNino(nombre, apellido, dni, domicilio, telefono, nroHistoriaMedica);
        }
        else if (edad < 60)
        {
            return new PacienteJoven(nombre, apellido, dni, domicilio, telefono, nroHistoriaMedica);
        }
        else
        {
            return new PacienteMayor(nombre, apellido, dni, domicilio, telefono, nroHistoriaMedica);
        }

    }
}
