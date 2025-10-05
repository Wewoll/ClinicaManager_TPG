package modelo.clinica.modulos.ingreso;

import modelo.personas.paciente.Paciente;

public class SalaDeEspera {
    Patio patio;
    SalaPrivada salaPrivada;

    public SalaDeEspera(Patio p, SalaPrivada s){
        patio = p;
        salaPrivada = s;
    }
    public void ingresaPaciente(Paciente p){
        Paciente pacienteSalaPrivada = salaPrivada.getPaciente();
        if (!salaPrivada.isOcupado()){
            salaPrivada.setPaciente(p);
        }else{
            if (p.prioridad(pacienteSalaPrivada)){
                patio.addPaciente(pacienteSalaPrivada);
                salaPrivada.setPaciente(p);
            }else{
                patio.addPaciente(p);
            }
        }
    }
    public void sacarPaciente(Paciente p){
         this.patio.sacarPaciente(p);
         this.salaPrivada.setPaciente(null);
    }
}
