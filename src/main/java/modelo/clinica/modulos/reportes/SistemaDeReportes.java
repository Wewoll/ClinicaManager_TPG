package modelo.clinica.modulos.reportes;

import modelo.personas.medico.IMedico;
import modelo.personas.paciente.Paciente;
import modelo.util.registros.RegistroMedico;
import modelo.util.registros.RegistroPaciente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Clase SistemaDeReportes que representa el sistema de reportes de la clinica.
 * Contiene como atributos dos HashMap para almacenar los registros medicos y los registros de pacientes.
 */
public class SistemaDeReportes
{
    private HashMap<String, ArrayList<RegistroMedico>> medicos;
    private HashMap<String, ArrayList<RegistroPaciente>> pacientes;

    /**
     * Constructor de SistemaDeReportes
     * <b>post:</b> Se crea un sistema de reportes con dos HashMap vacios para medicos y pacientes.
     */
    public SistemaDeReportes()
    {
        this.medicos = new HashMap<>();
        this.pacientes = new HashMap<>();
    }

    /**
     * Metodo publico void para agregar un registro medico y un registro de paciente.
     * <b>pre:</b> El medico, paciente y fecha no deben ser nulos.
     * <b>post:</b> Se agrega un registro medico al HashMap de medicos y un registro de paciente al HashMap de pacientes.
     *
     * @param m     Medico que realiza el registro.
     * @param p     Paciente que recibe el registro.
     * @param fecha Fecha del registro.
     */
    public void agregarRegistro(IMedico m, Paciente p, LocalDate fecha)
    {
        RegistroMedico rMedico = new RegistroMedico(p, fecha);
        RegistroPaciente rPaciente = new RegistroPaciente(m, fecha);

        ArrayList<RegistroMedico> listaMedico = this.medicos.get(m.getNroMatricula());
        if (listaMedico == null)
            listaMedico = new ArrayList<RegistroMedico>();
        listaMedico.add(rMedico);
        this.medicos.put(m.getNroMatricula(), listaMedico);

        ArrayList<RegistroPaciente> listaPaciente = this.pacientes.get(p.getNroHistoriaMedica());
        if (listaPaciente == null)
            listaPaciente = new ArrayList<RegistroPaciente>();
        listaPaciente.add(rPaciente);
        this.pacientes.put(p.getNroHistoriaMedica(), listaPaciente);
    }

    /**
     * Metodo publico para obtener los registros de un paciente.
     * <b>pre:</b> El paciente no debe ser nulo.
     *
     * @param p Paciente del cual se quieren obtener los registros.
     * @return Lista de registros del paciente.
     */
    public ArrayList<RegistroPaciente> obtenerRegistrosPorPaciente(Paciente p)
    {

        return this.pacientes.get(p.getNroHistoriaMedica());
    }

    /**
     * Metodo publico para obtener los registros de un medico.
     * <b>pre:</b> El medico no debe ser nulo.
     *
     * @param m Medico del cual se quieren obtener los registros.
     * @return Lista de registros del medico.
     */
    public ArrayList<RegistroMedico> obtenerRegistrosPorMedico(IMedico m)
    {
        return this.medicos.get(m.getNroMatricula());
    }

    /**
     * Metodo publico void para limpiar los registros de un paciente.
     * <b>pre:</b> El paciente no debe ser nulo.
     * <b>post:</b> Se limpian los registros del paciente en el HashMap de pacientes.
     *
     * @param p Paciente del cual se quieren limpiar los registros.
     */
    public void limpiarRegistrosPaciente(Paciente p)
    {
        ArrayList<RegistroPaciente> listaPaciente = this.pacientes.get(p.getNroHistoriaMedica());
        listaPaciente.clear();
        this.pacientes.put(p.getNroHistoriaMedica(), listaPaciente);
    }
}
