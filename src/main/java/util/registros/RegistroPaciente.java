package util.registros;

import modelo.personas.medico.IMedico;
import modelo.personas.medico.Medico;

import java.time.LocalDate;

public class RegistroPaciente {
    private IMedico medico;
    private LocalDate fecha;

    public RegistroPaciente(IMedico medico, LocalDate fecha) {
        this.medico = medico;
        this.fecha = fecha;
    }

    public IMedico getMedico() {
        return medico;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}
