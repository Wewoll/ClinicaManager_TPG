package vista;

public class VistaAsociadoDTO {
    private String nombre;
    private String apellido;
    private String dni;
    private String ciudad;
    private String calle;
    private String numero;
    private String telefono;

    public VistaAsociadoDTO(String nombre, String apellido, String dni, String ciudad, String calle, String numero, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.ciudad = ciudad;
        this.calle = calle;
        this.numero = numero;
        this.telefono = telefono;
    }
}
