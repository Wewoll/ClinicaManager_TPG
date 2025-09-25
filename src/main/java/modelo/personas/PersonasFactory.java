package modelo.personas;

import modelo.personas.medico.IMedico;
import modelo.personas.medico.Medico;
import modelo.personas.medico.contratacion.DecoratorContratacion;
import modelo.personas.medico.contratacion.DecoratorContratacionPermanente;
import modelo.personas.medico.contratacion.DecoratorContratacionResidente;
import modelo.personas.medico.especialidades.MedicoCirugia;
import modelo.personas.medico.especialidades.MedicoClinico;
import modelo.personas.medico.especialidades.MedicoPediatra;

public class PersonasFactory {

    public Persona crearPersona(String especialidad, String contratacion, String titulo, String nombre, String apellido, String dni, String telefono, String calle, int numero, String ciudad, String nroMatricula) {
        Persona persona = null;
        IMedico medico = null;
        switch (especialidad.toLowerCase()) {
            case "cirugia":
                medico = new MedicoCirugia(nombre, apellido, dni, new util.Domicilio(calle, numero, ciudad), telefono, nroMatricula);
                break;
            case "pediatra":
                medico = new MedicoPediatra(nombre, apellido, dni, new util.Domicilio(calle, numero, ciudad), telefono, nroMatricula);
                break;
            case "clinico":
                medico = new MedicoClinico(nombre, apellido, dni, new util.Domicilio(calle, numero, ciudad), telefono, nroMatricula);
                break;
            default:
                throw new IllegalArgumentException("Tipo de medico no reconocido: " + especialidad);
        }
        switch (contratacion.toLowerCase()) {
            case "residente":
                 medico = new DecoratorContratacionResidente((Medico) medico);
                break;
            case "permanente":
                 medico = new DecoratorContratacionPermanente((Medico) medico);
                break;
            default:
                throw new IllegalArgumentException("Tipo de contratacion no reconocido: " + contratacion);
        }
        switch (titulo.toLowerCase()) {
            case "postgrado":
                // medico = new Postgrado((Medico) persona);
                break;
            case "doctorado":
                // medico = new Doctorado((Medico) persona);
                break;
            case "grado":
                // medico = new Grado((Medico) persona);
                break;

        }
        persona = (Persona) medico;
        return persona;

    }

    public Persona crearPersona(String nombre, String apellido, String dni, String telefono, String calle, int numero, String ciudad, String nroHistorialClinica) {
        Persona persona = null;
        switch (tipo.toLowerCase()) {
            case "medico":
                persona = new modelo.personas.medico.Medico(nombre, apellido, dni, new util.Domicilio(direccion), telefono, "N/A");
                break;
            case "paciente":
                persona = new modelo.personas.paciente.Paciente(nombre, apellido, dni, new util.Domicilio(direccion), telefono);
                break;
            default:
                throw new IllegalArgumentException("Tipo de persona no reconocido: " + tipo);
        }
        return persona;
    }
}
