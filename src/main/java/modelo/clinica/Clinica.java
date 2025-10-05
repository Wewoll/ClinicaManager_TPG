package modelo.clinica;

import modelo.clinica.modulos.egreso.SistemaDeEgreso;
import modelo.clinica.modulos.egreso.facturacion.Factura;
import modelo.clinica.modulos.ingreso.PacienteNoRegistradoException;
import modelo.clinica.modulos.reportes.SistemaDeReportes;
import modelo.clinica.modulos.ingreso.SistemaIngreso;
import modelo.personas.medico.IMedico;
import modelo.personas.paciente.Paciente;
import util.registros.RegistroMedico;

import java.time.LocalDate;
import java.util.HashMap;

public class Clinica implements Cloneable
{
    private static Clinica instancia = null;
    private final String nombre;
    private final String direccion;
    private final String telefono;
    private final String ciudad;
    private HashMap<String, IMedico> medicos;
    private HashMap<String, Paciente> pacientesRegistrados;
    private SistemaIngreso sistemaDeIngreso;
    private SistemaDeReportes sistemaDeReportes;
    private SistemaDeEgreso sistemaDeEgreso;

    private Clinica(String nombre, String direccion, String telefono, String ciudad)
    {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
    }

    public static Clinica getInstancia()
    {
        if (instancia == null)
        {
            instancia = new Clinica("Clinica Central", "Calle Falsa 123", "123456789", "Ciudad Ejemplo");
            instancia.medicos = new HashMap<>();
            instancia.pacientesRegistrados = new HashMap<>();
            instancia.sistemaDeIngreso = new SistemaIngreso();
            instancia.sistemaDeReportes = new SistemaDeReportes();
            instancia.sistemaDeEgreso = new SistemaDeEgreso();
        }
        return instancia;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

    public void registrarMedico(IMedico medico)
    {
        medicos.put(medico.getNroMatricula(), medico);
    }
    public void registrarPaciente(Paciente paciente)
    {
        pacientesRegistrados.put(paciente.getNroHistoriaMedica(), paciente);
    }

    public void ingresarPaciente(Paciente paciente) throws PacienteNoRegistradoException
    {
        if(pacientesRegistrados.containsKey(paciente.getNroHistoriaMedica()))
            this.sistemaDeIngreso.ingresaPaciente(paciente);
        else
            throw new PacienteNoRegistradoException(paciente);
    }
    public Factura egresarPaciente(Paciente paciente)
    {
        return sistemaDeEgreso.egresarPaciente(paciente, this.sistemaDeReportes.obtenerRegistrosPorPaciente(paciente));
    }
    public Factura egresarPaciente(Paciente paciente, int diasInternado)
    {
        paciente.setDiasInternado(diasInternado);
        return sistemaDeEgreso.egresarPaciente(paciente, this.sistemaDeReportes.obtenerRegistrosPorPaciente(paciente));
    }
    public void atenderPaciente(IMedico medico, Paciente paciente)
    {
        boolean analisis = false, derivado = false;
        do
        {
            this.sistemaDeIngreso.sacarPacienteSalaDeEspera(paciente);
            listaAtencion.add(paciente);
            this.sistemaDeReportes.agregarRegistro(medico, paciente, LocalDate.now()); // debe ir fecha
            paciente.setInternado(analisis);
            if ()
        } while (derivado = true);
    }
    public String reportesMedicos(IMedico medico)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Reportes del Medico: ").append(medico.getNombre()).append(" ").append(medico.getApellido()).append("\n");
        for (RegistroMedico registro : this.sistemaDeReportes.obtenerRegistrosPorMedico(medico))
        {
            sb.append(registro).append("\n");
        }
        return sb.toString();
    }
}
