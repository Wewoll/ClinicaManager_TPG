package modelo.clinica;

import modelo.ambulancia.Ambulancia;
import modelo.clinica.modulos.egreso.SistemaDeEgreso;
import modelo.clinica.modulos.egreso.facturacion.Factura;
import modelo.clinica.modulos.reportes.SistemaDeReportes;
import modelo.clinica.modulos.ingreso.SistemaIngreso;
import modelo.habitaciones.Habitacion;
import modelo.personas.asociado.Asociado;
import modelo.personas.medico.IMedico;
import modelo.personas.paciente.Paciente;
import modelo.util.Domicilio;
import modelo.util.registros.RegistroMedico;
import modelo.util.registros.RegistroPaciente;
import modelo.util.Excepciones.*;
import persistencia.AsociadoDTO;
import persistencia.DataAccessObject;
import vista.VistaAsociadoDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Clase Clinica que representa una clinica medica.
 * Implementa el patrón Singleton para asegurar que solo exista una instancia de la clinica.
 * Contiene atributos como nombre, direccion, telefono, ciudad, medicos registrados, pacientes registrados,
 * y sistemas de ingreso, egreso y reportes.
 */
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
    private ArrayList<Paciente> listaAtencion;
    private ArrayList<Habitacion> listaHabitaciones;
    private ArrayList<Asociado> asociados;
    private Ambulancia ambulancia;
    private DataAccessObject dao;
    /**
     * Constructor privado para el patrón Singleton
     *
     * <b>Pre</b>: Los parámetros no deben ser nulos o vacíos.
     * <b>Post</b>: Se crea una instancia de Clinica con los datos proporcionados.
     *
     * @param nombre    Nombre de la clínica.
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

    /**
     * Método estático para obtener la única instancia de Clinica.
     * Si la instancia no existe, se crea una nueva con datos predeterminados.
     * <b>Pre</b>: Ninguna.
     * <b>Post</b>: Se retorna la única instancia de Clinica.
     *
     * @return Instancia única de Clinica.
     */
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
            instancia.listaAtencion = new ArrayList<>();
            instancia.listaHabitaciones = new ArrayList<>();
            instancia.asociados = new ArrayList<>();
            instancia.ambulancia = new Ambulancia();
        }
        return instancia;
    }

    /**
     * Método para evitar la clonación de la instancia Singleton.
     *
     * @throws CloneNotSupportedException Siempre lanzada para evitar la clonación.
     */
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

    /**
     * Registra un nuevo médico en la clínica.
     * <b>Pre</b>: El médico no debe ser nulo y en caso de que ya exista, se sobreescribe.
     * <b>Post</b>: El médico se agrega al registro de médicos de la clínica.
     *
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
     *
     * @param paciente El paciente a registrar.
     */
    public void registrarPaciente(Paciente paciente)
    {
        pacientesRegistrados.put(paciente.getNroHistoriaMedica(), paciente);
    }
    /**
     * Agrega una habitacion a la clinica.
     * <b>Pre</b>: La habitacion no debe ser nula y en caso de que ya exista, se sobreescribe.
     * <b>Post</b>: La habitacion se agrega al registro de habitaciones de la clínica.
     *
     * @param h La habitacion a registrar.
     */
    public void agregarHabitacion(Habitacion h) { this.listaHabitaciones.add(h); }

    /**
     * Ingresa un paciente a la clínica.
     * En caso de que el paciente no esté registrado, lanza una excepción PacienteNoRegistradoException.
     * Si el paciente está registrado, se procede a ingresarlo al sistema de ingreso
     *
     * @param paciente El paciente a ingresar.
     * @param fecha   La fecha de ingreso del paciente.
     *<b>Pre</b>: El paciente debe estar registrado en la clínica.
     * @throws PacienteNoRegistradoException Si el paciente no está registrado en la clínica.
     */
    public void ingresarPaciente(Paciente paciente, LocalDate fecha) throws PacienteNoRegistradoException
    {
        if (pacientesRegistrados.containsKey(paciente.getNroHistoriaMedica())){
            paciente.setFechaIngreso(fecha);
            this.sistemaDeIngreso.ingresaPaciente(paciente);
        }
        else
            throw new PacienteNoRegistradoException(paciente);
    }

    /**
     * Egresar un paciente de la clínica.
     * <b>Pre</b>: El paciente debe estar registrado en la clínica.
     * <b>Post</b>: Se genera una factura para el paciente egresado
     *
     * @param paciente El paciente a egresar.
     * @return Factura generada al egresar el paciente
     * @throws PacienteSinConsultasMedicasException Si el paciente no tiene consultas méd
     */
    public Factura egresarPaciente(Paciente paciente) throws PacienteSinConsultasMedicasException
    {
        ArrayList<RegistroPaciente> consultasMedicas = this.sistemaDeReportes.obtenerRegistrosPorPaciente(paciente);
        if (consultasMedicas == null)
            throw new PacienteSinConsultasMedicasException("El paciente no tiene consultas médicas registradas.");
        listaAtencion.remove(paciente);
        return sistemaDeEgreso.egresarPaciente(paciente, consultasMedicas);
    }



    /**
     * Egresar un paciente de la clínica con días internado.
     * <b>Post</b> : Se genera una factura para el paciente egresado, incluyendo los días internado.
     *
     * @param paciente
     * @param diasInternado
     * @return Factura generada al egresar el paciente
     * @throws PacienteNoRegistradoException        Si el paciente no está registrado en la clínica.
     * @throws PacienteSinConsultasMedicasException Si el paciente no tiene consultas médicas registradas.
     */
    public Factura egresarPaciente(Paciente paciente, int diasInternado) throws PacienteNoRegistradoException, PacienteSinConsultasMedicasException
    {
        ArrayList<RegistroPaciente> consultasMedicas = this.sistemaDeReportes.obtenerRegistrosPorPaciente(paciente);
        Paciente p = this.pacientesRegistrados.get(paciente.getNroHistoriaMedica());
        if (p == null)
            throw new PacienteNoRegistradoException(paciente);
        paciente.setDiasInternado(diasInternado);
        if (consultasMedicas == null)
            throw new PacienteSinConsultasMedicasException("El paciente no tiene consultas médicas registradas.");
        Factura factura = sistemaDeEgreso.egresarPaciente(paciente, consultasMedicas);
        sistemaDeReportes.limpiarRegistrosPaciente(paciente);
        listaAtencion.remove(paciente);
        return factura;
    }

    /**
     * Atender a un paciente por un médico.
     * <b>Pre</b>: El médico y el paciente no deben ser nulos.
     * <b>Post</b>: Se registra la atención del paciente por el médico en el sistema de reportes.
     *
     * @param medico   El médico que atiende al paciente.
     * @param paciente El paciente que es atendido.
     * @throws PacienteNoIngresadoException Si el paciente no ha sido ingresado previamente.
     */
    public void atenderPaciente(IMedico medico, Paciente paciente) throws PacienteNoIngresadoException
    {
        if (this.sistemaDeIngreso.sacarPacienteSalaDeEspera(paciente))
        {
            listaAtencion.add(paciente);
        } else
        {
            if (!listaAtencion.contains(paciente))
            {
                throw new PacienteNoIngresadoException(paciente);
            }
        }
        this.sistemaDeReportes.agregarRegistro(medico, paciente, paciente.getFechaIngreso());

    }
    public String generarReporteMedico(IMedico medico, LocalDate fechaInicio, LocalDate fechaFin)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Reporte de Consultas Médicas del Médico: ").append(medico.getNombre()).append(" ").append(medico.getApellido()).append("\n");
        sb.append("Desde: ").append(fechaInicio).append(" Hasta: ").append(fechaFin).append("\n-------------------------------------------------------------------------------------------\n");
        ArrayList<RegistroMedico> consultas= this.sistemaDeReportes.obtenerRegistrosPorMedico(medico);

        consultas.sort(Comparator.comparing(RegistroMedico::getFecha));

        for (RegistroMedico registro :consultas)
        {
            if ((registro.getFecha().isEqual(fechaInicio) || registro.getFecha().isAfter(fechaInicio)) &&
                    (registro.getFecha().isEqual(fechaFin) || registro.getFecha().isBefore(fechaFin)))
            {
                sb.append("Fecha: ").append(registro.getFecha()).append("\tPaciente: ").append(registro.getPaciente().getNroHistoriaMedica()).append(" - ");
                sb.append(registro.getPaciente().getApellido()).append(", ").append(registro.getPaciente().getNombre()).append("\tHonorario consulta: ").append(String.format("%.2f",medico.getSueldo()) ).append("\n");
            }
        }
        return sb.toString();
    }
    /**
     * Genera un reporte de todas las consultas médicas realizadas por un medico específico.
     * <b>Pre</b>: El medico no debe ser nulo.
     * <b>Post</b>: Se retorna un String con el reporte de las consultas médicas del medico.
     *
     * @param medico El medico del cual se desea generar el reporte.
     * @return String con el reporte de las consultas médicas del medico.
     */
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

    /**
     * Interna a un paciente en una habitación específica.
     * <b>Post</b>: El paciente es internado en la habitación especificada.
     *
     * @param paciente El paciente a internar.
     * @param h        La habitación donde se internará al paciente.
     * @throws PacienteNoRegistradoException Si el paciente no está registrado en la clínica.
     * @throws PacienteNoIngresadoException  Si el paciente no ha sido ingresado previamente.
     * @throws PacienteYaInternado           Si el paciente ya está internado.
     * @throws HabitacionOcupadaException    Si la habitación ya está ocupada.
     */
    public void internarPaciente(Paciente paciente, Habitacion h) throws PacienteNoRegistradoException, PacienteNoIngresadoException, PacienteYaInternado, HabitacionOcupadaException
    {
        if (!this.pacientesRegistrados.containsKey(paciente.getNroHistoriaMedica()))
            throw new PacienteNoRegistradoException(paciente);
        if (!this.listaAtencion.contains(paciente))
            throw new PacienteNoIngresadoException(paciente);
        if (paciente.isInternado())
            throw new PacienteYaInternado(paciente);

        h.ocupar();
        paciente.setInternado(true);
        paciente.setHabitacion(h);
        this.listaAtencion.remove(paciente);
    }

    public void agregarAsociado(Asociado asociado){
        this.asociados.add(asociado);
    }

    public void iniciarSimulacion(int cantAsociados, int maxCantSolicitudesPorAsociado){
        try{
            ArrayList<AsociadoDTO> asociadosDTOs= this.dao.cargarConLimite(cantAsociados);
            for (AsociadoDTO aDTO: asociadosDTOs){
                Asociado asociado = new Asociado(aDTO.getNombre(), aDTO.getApellido(), aDTO.getDni(), aDTO.getTelefono(), aDTO.getDomicilio(),  maxCantSolicitudesPorAsociado,this.ambulancia);
                this.asociados.add(asociado);
                Thread hiloAsociado = new Thread(asociado);
                hiloAsociado.start();
            }
        }catch (SQLException e){
            System.out.println("Error al cargar con limite de solicitudes");
        }
    }

    public void guardarNuevoAsociado(Asociado asociado){

        try{
            dao.guardar(asociado);
        }catch (Exception e){

        }
    }

    public void eliminarAsociado(String dni){
        try{
            for (Asociado a: asociados){
                if (a.getDni().equals(dni)){
                    dao.eliminar(a.getId());
                    asociados.remove(a);
                    break;
                }
            }
        }catch (Exception e){

        }
    }


    public Asociado crearAsociado(VistaAsociadoDTO datos){
        return new Asociado(datos.getNombre(), datos.getApellido(), datos.getDni(), datos.getTelefono(), new Domicilio(datos.getCalle(),datos.getNumero(),datos.getCiudad()));
    }
}
