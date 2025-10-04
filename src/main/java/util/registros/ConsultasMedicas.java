package util.registros;

import modelo.personas.medico.Medico;
import modelo.personas.paciente.Paciente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class ConsultasMedicas {
    private HashMap<String, ArrayList<RegistroMedico>> medicos;
    private HashMap<String, ArrayList<RegistroPaciente>> pacientes;

    public ConsultasMedicas() {
        medicos = new HashMap<>();
        pacientes = new HashMap<>();
    }
    public void agregarRegistro(Medico m, Paciente p, LocalDate fecha){
        RegistroMedico rMedico = new RegistroMedico(p,fecha);
        RegistroPaciente rPaciente = new RegistroPaciente(m,fecha);

        ArrayList<RegistroMedico> listaMedico = this.medicos.get(m.getNroMatricula());
        listaMedico.add(rMedico);
        this.medicos.put(m.getNroMatricula(), listaMedico);

        ArrayList<RegistroPaciente> listaPaciente = this.pacientes.get(p.getNroHistoriaMedica());
        listaPaciente.add(rPaciente);
        this.pacientes.put(p.getNroHistoriaMedica(), listaPaciente);
    }
}
