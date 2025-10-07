package modelo.clinica.modulos.reportes;

import util.registros.*;

import modelo.personas.medico.IMedico;
import modelo.personas.paciente.Paciente;
import util.registros.RegistroMedico;
import util.registros.RegistroPaciente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class SistemaDeReportes
{
    private HashMap<String, ArrayList<RegistroMedico>> medicos;
    private HashMap<String, ArrayList<RegistroPaciente>> pacientes;

    public SistemaDeReportes()
    {
        this.medicos = new HashMap<>();
        this.pacientes = new HashMap<>();
    }

    public void agregarRegistro(IMedico m, Paciente p, LocalDate fecha)
    {
        RegistroMedico rMedico = new RegistroMedico(p, fecha);
        RegistroPaciente rPaciente = new RegistroPaciente(m, fecha);

        ArrayList<RegistroMedico> listaMedico = this.medicos.get(m.getNroMatricula());
        listaMedico.add(rMedico);
        this.medicos.put(m.getNroMatricula(), listaMedico);

        ArrayList<RegistroPaciente> listaPaciente = this.pacientes.get(p.getNroHistoriaMedica());
        listaPaciente.add(rPaciente);
        this.pacientes.put(p.getNroHistoriaMedica(), listaPaciente);
    }

    public ArrayList<RegistroPaciente> obtenerRegistrosPorPaciente(Paciente p)
    {
        return this.pacientes.get(p.getNroHistoriaMedica());
    }

    public ArrayList<RegistroMedico> obtenerRegistrosPorMedico(IMedico m)
    {
        return this.medicos.get(m.getNroMatricula());
    }

    public void limpiarRegistrosPaciente(Paciente p)
    {
        ArrayList<RegistroPaciente> listaPaciente = this.pacientes.get(p.getNroHistoriaMedica());
        listaPaciente.clear();
        this.pacientes.put(p.getNroHistoriaMedica(), listaPaciente);
    }
}
