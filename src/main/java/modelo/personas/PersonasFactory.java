package modelo.personas;

public class PersonasFactory {

    public Persona crearPersona(String especialidad,String contratacion,String titulo, String nombre, String apellido, String dni, String telefono, String calle, int numero, String ciudad,String nroMatricula) {
        Persona persona = null;
        switch (especialidad.toLowerCase()) {
            case "":
                persona = new modelo.personas.medico.Medico(nombre, apellido, dni, new util.Domicilio(calle, numero, ciudad), telefono, nroMatricula);
                break;
            case "":

                break;
            default:
                throw new IllegalArgumentException("Tipo de persona no reconocido: " + tipo);
        }
        switch (contratacion.toLowerCase()) {}
        switch (titulo.toLowerCase()) {}
        return persona;

    }

    public Persona crearPersona( String nombre, String apellido, String dni, String telefono, String calle, int numero, String ciudad,String nroHistorialClinica) {
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
