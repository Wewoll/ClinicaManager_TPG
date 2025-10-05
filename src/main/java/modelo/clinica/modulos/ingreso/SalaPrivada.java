package modelo.clinica.modulos.ingreso;

import modelo.personas.paciente.Paciente;

public class SalaPrivada {
    private boolean ocupado;
    private Paciente paciente;

    public SalaPrivada(){
        this.ocupado = false;
        this.paciente = null;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
