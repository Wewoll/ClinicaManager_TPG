package modelo.clinica.modulos.reportes;

import modelo.personas.medico.IMedico;
import modelo.personas.medico.Medico;
import modelo.personas.paciente.Paciente;
import util.registros.ConsultasMedicas;
import util.registros.RegistroMedico;
import util.registros.RegistroPaciente;

import java.time.LocalDate;
import java.util.ArrayList;

public class SistemaDeReportes {
    private ConsultasMedicas consultasMedicas;

    public SistemaDeReportes() {
        this.consultasMedicas = new ConsultasMedicas();
    }

    public void agregarRegistro(IMedico m, Paciente p, LocalDate fecha){
        this.consultasMedicas.agregarRegistro(m, p, fecha);
    }
    public ArrayList<RegistroPaciente> obtenerRegistrosPorPaciente(Paciente p){
        return this.consultasMedicas.obtenerRegistrosPorPaciente(p);
    }
    public ArrayList<RegistroMedico> obtenerRegistrosPorMedico(IMedico m)
    {
        return this.consultasMedicas.obtenerRegistrosPorMedico(m);
    }
}
