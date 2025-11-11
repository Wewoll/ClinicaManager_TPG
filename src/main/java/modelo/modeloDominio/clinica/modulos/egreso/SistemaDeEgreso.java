package modelo.modeloDominio.clinica.modulos.egreso;

import modelo.modeloDominio.clinica.modulos.egreso.facturacion.Factura;
import modelo.modeloDominio.personas.paciente.Paciente;
import modelo.modeloDominio.util.registros.RegistroPaciente;

import java.util.ArrayList;

/**
 * Clase SistemaDeEgreso que representa el sistema de egreso de pacientes de la clinica.
 */
public class SistemaDeEgreso
{
    /**
     * Metodo publico egresarPaciente que recibe un paciente y una lista de registros de consultas medicas.
     * Crea una factura para el paciente con los detalles de las consultas medicas.
     * <b>pre:</b> El paciente y la lista de registros no deben ser nulos.
     * <b>post:</b> Se genera una factura para el paciente con los detalles
     * @param p Paciente que se va a egresar.
     * @param consultasMedicas Lista de registros de consultas medicas del paciente.
     * @return Factura generada para el paciente.
     */
    public Factura egresarPaciente(Paciente p, ArrayList<RegistroPaciente> consultasMedicas)
    {
        assert p != null : "El paciente no puede ser nulo";
        assert consultasMedicas != null : "La lista de consultas medicas no puede ser nula";
        Factura f = new Factura(p);
        f.setDetalle(consultasMedicas);
        return f;
    }

}
