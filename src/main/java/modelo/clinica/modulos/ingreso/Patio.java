package modelo.clinica.modulos.ingreso;

import modelo.personas.paciente.Paciente;

import java.util.ArrayList;

public class Patio {
    private ArrayList<Paciente> listaEspera;

    public Patio(){
        this.listaEspera = new ArrayList<>();
    }
    public ArrayList<Paciente> getListaEspera() {
        return listaEspera;
    }
    public void setListaEspera(ArrayList<Paciente> listaEspera) {
        this.listaEspera = listaEspera;
    }
    public void addPaciente(Paciente paciente){
        this.listaEspera.add(paciente);
    }
    public boolean sacarPaciente(Paciente paciente){
        return this.listaEspera.remove(paciente);
    }
}
