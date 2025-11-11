package modelo.modeloDominio.clinica;

import modelo.modeloDominio.ambulancia.Ambulancia;
import modelo.modeloDominio.clinica.modulos.egreso.SistemaDeEgreso;
import modelo.modeloDominio.clinica.modulos.egreso.facturacion.Factura;
import modelo.modeloDominio.clinica.modulos.reportes.SistemaDeReportes;
import modelo.modeloDominio.clinica.modulos.ingreso.SistemaIngreso;
import modelo.modeloDominio.habitaciones.Habitacion;
import modelo.modeloDominio.personas.asociado.Asociado;
import modelo.modeloDominio.personas.medico.IMedico;
import modelo.modeloDominio.personas.paciente.Paciente;
import modelo.modeloDominio.util.Domicilio;
import modelo.modeloDominio.util.registros.RegistroMedico;
import modelo.modeloDominio.util.registros.RegistroPaciente;
import modelo.modeloDominio.util.Excepciones.*;
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
            instancia.dao = new DataAccessObject();
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
        assert medico != null: "El medico no puede ser nulo";
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
        assert paciente != null: "El paciente no puede ser nulo";
        pacientesRegistrados.put(paciente.getNroHistoriaMedica(), paciente);
    }
    /**
     * Agrega una habitacion a la clinica.
     * <b>Pre</b>: La habitacion no debe ser nula y en caso de que ya exista, se sobreescribe.
     * <b>Post</b>: La habitacion se agrega al registro de habitaciones de la clínica.
     *
     * @param h La habitacion a registrar.
     */
    public void agregarHabitacion(Habitacion h)
    {
        assert  h != null: "El habitacion no puede ser nulo";
        this.listaHabitaciones.add(h);
    }

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
        assert   paciente != null: "El paciente no puede ser nulo";
        assert  fecha != null: "El fecha no puede ser nulo";
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
        assert paciente != null: "El paciente no puede ser nulo";
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
        assert paciente != null: "El paciente no puede ser nulo";
        assert diasInternado >= 0: "Los dias internado no pueden ser negativos";
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
        assert medico != null: "El medico no puede ser nulo";
        assert  paciente != null: "El paciente no puede ser nulo";

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

    /**
     * Genera un reporte de todas las consultas médicas realizadas por un medico específico en un rango de fechas.
     * <b>Pre</b>: El medico no debe ser nulo y las fechas deben ser válidas.
     * <b>Post</b>: Se retorna un String con el reporte de las consultas médicas del medico en el rango de fechas.
     *
     * @param medico      El medico del cual se desea generar el reporte.
     * @param fechaInicio Fecha de inicio del rango.
     * @param fechaFin    Fecha de fin del rango.
     * @return String con el reporte de las consultas médicas del medico en el rango de fechas.
     */
    public String generarReporteMedico(IMedico medico, LocalDate fechaInicio, LocalDate fechaFin)
    {
        assert medico != null : "El medico no puede ser nulo";
        assert  fechaInicio != null : "La fecha de inicio no puede ser nula";
        assert fechaFin != null : "La fecha de fin no puede ser nula";
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
        assert medico != null: "El medico no puede ser nulo";
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
        assert   paciente != null: "El paciente no puede ser nulo";
        assert  h != null: "La habitacion no puede ser nula";
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

    /**
     * Agrega un asociado a la lista de asociados de la clínica.
     * <b>Pre</b>: El asociado no debe ser nulo.
     * <b>Post</b>: El asociado se agrega a la lista de asociados de la clínica.
     * @param asociado El asociado a agregar.
     */
    public void agregarAsociado(Asociado asociado){
        assert asociado != null : "El asociado no puede ser nulo";
        this.asociados.add(asociado);
    }
    /**
     * Inicia la simulación de la clínica con una cantidad específica de asociados y un máximo de solicitudes por asociado.
     * <b>Pre</b>: cantAsociados y maxCantSolicitudesPorAsociado deben ser mayores que 0.
     * <b>Post</b>: Se inicia la simulación con los asociados cargados desde la base de datos.
     *
     * @param cantAsociados                Cantidad de asociados a cargar.
     * @param maxCantSolicitudesPorAsociado Máximo de solicitudes por asociado.
     */
    public void iniciarSimulacion(int cantAsociados, int maxCantSolicitudesPorAsociado){
            assert cantAsociados > 0 : "La cantidad de asociados debe ser mayor que 0";
            assert maxCantSolicitudesPorAsociado > 0 : "La cantidad maxima de solicitudes por asociado debe ser mayor que 0";
        try{
            ArrayList<AsociadoDTO> asociadosDTOs= this.dao.cargarConLimite(cantAsociados);
            for (AsociadoDTO aDTO: asociadosDTOs){
                Asociado asociado = new Asociado(aDTO.getNombre(), aDTO.getApellido(), aDTO.getDni(), aDTO.getTelefono(), new Domicilio(aDTO.getCalle(), aDTO.getNumero(), aDTO.getCiudad()),  maxCantSolicitudesPorAsociado,this.ambulancia);
                this.asociados.add(asociado);
                Thread hiloAsociado = new Thread(asociado);
                hiloAsociado.start();
            }
        }catch (SQLException e){
            System.out.println("Error al cargar con limite de solicitudes");
        }
    }

    /**
     * Guarda un nuevo asociado en la base de datos.
     * <b>Pre</b>: Los datos del asociado no deben ser nulos.
     * <b>Post</b>: Se guarda el nuevo asociado en la base de datos.
     *
     * @param datos Datos del asociado a guardar.
     */
    public void guardarNuevoAsociado(AsociadoDTO datos){
        assert datos != null : "El asociado no puede ser nulo";
        try{
            Asociado asociado = new Asociado(datos);
            dao.guardar(asociado);
        }catch (Exception e){

        }
    }

    /**
     * Elimina un asociado de la base de datos y de la lista de asociados de la clínica.
     * <b>Pre</b>: El DNI del asociado no debe ser nulo.
     * <b>Post</b>: Se elimina el asociado de la base de datos y de la lista de asociados de la clínica.
     *
     * @param dni DNI del asociado a eliminar.
     */
    public void eliminarAsociado(String dni){
        assert dni != null : "El asociado no puede ser nulo";
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

    /**
     * Crea un objeto Asociado a partir de los datos proporcionados.
     * <b>Pre</b>: Los datos del asociado no deben ser nulos.
     * <b>Post</b>: Se devuelve un objeto Asociado con los datos proporcionados.
     *
     * @param datos Datos del asociado a crear.
     * @return Objeto Asociado creado.
     */
    public Asociado crearAsociado(VistaAsociadoDTO datos){
        assert datos != null : "El asociado no puede ser nulo";
        return new Asociado(datos.getNombre(), datos.getApellido(), datos.getDni(), datos.getTelefono(), new Domicilio(datos.getCalle(),datos.getNumero(),datos.getCiudad()));
    }
}
