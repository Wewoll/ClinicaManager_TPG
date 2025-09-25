package modelo.clinica;

import modelo.personas.medico.Medico;
import modelo.personas.paciente.Paciente;

import java.util.HashMap;

public class Clinica implements Cloneable{
    private static Clinica instancia = null;
    private String nombre;
    private String direccion;
    private String telefono;
    private String ciudad;
    private HashMap<String, Medico> medicos;
    private HashMap<String, Paciente> pacientes;

    private Clinica(String nombre, String direccion, String telefono, String ciudad) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
    }

    public static Clinica getInstancia() {
        if (instancia == null) {
            instancia = new Clinica("Clinica Central", "Calle Falsa 123", "123456789", "Ciudad Ejemplo");
            instancia.medicos = new HashMap<>();
            instancia.pacientes = new HashMap<>();
        }
        return instancia;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
