package modelo.clinica.modulos.ingreso;

import modelo.personas.paciente.Paciente;

public class SistemaIngreso
{
    private SalaDeEspera salaDeEspera;

    public SistemaIngreso() {
        this.salaDeEspera = new SalaDeEspera(new Patio(), new SalaPrivada());
    }

    public void ingresaPaciente(Paciente p){
        salaDeEspera.ingresaPaciente(p);
    }
    public void sacarPacienteSalaDeEspera(Paciente p){
        salaDeEspera.sacarPaciente(p);
    }
}
