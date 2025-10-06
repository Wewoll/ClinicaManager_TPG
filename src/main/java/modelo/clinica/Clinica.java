package modelo.clinica;

import modelo.clinica.modulos.egreso.SistemaDeEgreso;
import modelo.clinica.modulos.egreso.facturacion.Factura;
import modelo.clinica.modulos.reportes.SistemaDeReportes;
import modelo.clinica.modulos.ingreso.SistemaIngreso;
import modelo.personas.medico.IMedico;
import modelo.personas.paciente.Paciente;
import util.registros.RegistroMedico;
import util.registros.RegistroPaciente;
import util.Excepciones.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.ArrayList;

public class Clinica
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


    /**
     * Constructor privado para el patrón Singleton
     *
     * <b>Pre</b>: Los parámetros no deben ser nulos o vacíos.
     * <b>Post</b>: Se crea una instancia de Clinica con los datos proporcionados.
     *
     * @param nombre   Nombre de la clínica.
     * @param direccion Dirección de la clínica.
     * @param telefono  Teléfono de contacto de la clínica.
     * @param ciudad    Ciudad donde se encuentra la clínica.
     * @return Unica instancia de Clinica.
     */
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

    /**
     * Registra un nuevo médico en la clínica.
     * <b>Pre</b>: El médico no debe ser nulo y en caso de que ya exista, se sobreescribe.
     * <b>Post</b>: El médico se agrega al registro de médicos de la clínica.
     * @param medico El médico a registrar.
     */
    public void registrarMedico(IMedico medico)
    {
        medicos.put(medico.getNroMatricula(), medico);
    }

    /**
     * Registra un nuevo paciente en la clínica.
     * <b>Pre</b>: El paciente no debe ser nulo y en caso de que ya exista, se sobreescribe.
     * <b>Post</b>: El paciente se agrega al registro de pacientes de la clínica.
     * @param paciente El paciente a registrar.
     */
    public void registrarPaciente(Paciente paciente)
    {
        pacientesRegistrados.put(paciente.getNroHistoriaMedica(), paciente);
    }

    /**
     * Ingresa un paciente a la clínica.
     * En caso de que el paciente no esté registrado, lanza una excepción PacienteNoRegistradoException.
     * Si el paciente está registrado, se procede a ingresarlo al sistema de ingreso
     * @param paciente
     * @throws PacienteNoRegistradoException
     */
    public void ingresarPaciente(Paciente paciente) throws PacienteNoRegistradoException
    {
        if(pacientesRegistrados.containsKey(paciente.getNroHistoriaMedica()))
            this.sistemaDeIngreso.ingresaPaciente(paciente);
        else
            throw new PacienteNoRegistradoException(paciente);
    }

    /**
     * Egresar un paciente de la clínica.
     * <b>Pre</b>: El paciente debe estar registrado en la clínica.
     * <b>Post</b>: Se genera una factura para el paciente egresado
     * @param paciente
     * @return Factura generada al egresar el paciente
     * @throws PacienteSinConsultasMedicasException Si el paciente no tiene consultas méd
     */
    public Factura egresarPaciente(Paciente paciente) throws PacienteNoRegistradoException, PacienteSinConsultasMedicasException
    {
        ArrayList<RegistroPaciente> consultasMedicas = this.sistemaDeReportes.obtenerRegistrosPorPaciente(paciente);
        if (consultasMedicas == null)
            throw new PacienteSinConsultasMedicasException("El paciente no tiene consultas médicas registradas.");
        return sistemaDeEgreso.egresarPaciente(paciente, consultasMedicas);
    }


    /**
     * Egresar un paciente de la clínica con días internado.
     * <b>Post</b> : Se genera una factura para el paciente egresado, incluyendo los días internado.
     * @param paciente
     * @param diasInternado
     * @return
     * @throws PacienteNoRegistradoException
     * @throws PacienteSinConsultasMedicasException
     */
    public Factura egresarPaciente(Paciente paciente, int diasInternado) throws PacienteNoRegistradoException, PacienteSinConsultasMedicasException
    {
        ArrayList<RegistroPaciente> consultasMedicas = this.sistemaDeReportes.obtenerRegistrosPorPaciente(paciente);
        Paciente p = this.pacientesRegistrados.get(paciente.getNroHistoriaMedica());
        if (p == null)
            throw new PacienteNoRegistradoException(p);
        paciente.setDiasInternado(diasInternado);
        if (consultasMedicas == null)
            throw new PacienteSinConsultasMedicasException("El paciente no tiene consultas médicas registradas.");
        return sistemaDeEgreso.egresarPaciente(paciente, consultasMedicas);
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
