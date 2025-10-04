package util.registros;

import modelo.personas.medico.Medico;

import java.time.LocalDate;

public class RegistroPaciente {
    private Medico medico;
    private LocalDate fecha;

    public RegistroPaciente(Medico medico, LocalDate fecha) {
        this.medico = medico;
        this.fecha = fecha;
    }

    public Medico getMedico() {
        return medico;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}
