package util.registros;

import modelo.personas.paciente.Paciente;

import java.time.LocalDate;

public class RegistroMedico {
    private Paciente paciente;
    private LocalDate fecha;

    public RegistroMedico(Paciente p, LocalDate fecha){
        this.paciente = p;
        this.fecha = fecha;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "RegistroMedico{" +
                "paciente=" + paciente +
                ", fecha=" + fecha +
                '}';
    }
}
