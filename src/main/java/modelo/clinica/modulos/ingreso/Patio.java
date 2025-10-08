package modelo.clinica.modulos.ingreso;

import modelo.personas.paciente.Paciente;

import java.util.ArrayList;
/**
 * Clase Patio que representa el patio de espera de la clinica.
 * Contiene una lista de pacientes en espera.
 */
public class Patio {
    private ArrayList<Paciente> listaEspera;

    /**
     * Constructor de Patio
     * <b>post:</b> Se crea un patio con una lista vacia de pacientes en espera.
     */
    public Patio(){
        this.listaEspera = new ArrayList<>();
    }

    /**
     * Metodo publico para preguntar cual es la lista de espera de pacientes.
     * @return listaEspera de pacientes.
     */
    public ArrayList<Paciente> getListaEspera() {
        return listaEspera;
    }

    /**
     * Metodo publico void para setear la lista de espera de pacientes.
     * <b>pre:</b> listaEspera no debe ser nulo.
     * <b>post:</b> se setea la lista de espera con la lista proporcionada.
     * @param listaEspera Lista de pacientes en espera.
     */
    public void setListaEspera(ArrayList<Paciente> listaEspera) {
        this.listaEspera = listaEspera;
    }

    /**
     * Metodo publico void para agregar un paciente a la lista de espera.
     * <b>pre:</b> paciente no debe ser nulo.
     * <b>post:</b> se agrega el paciente a la lista de espera.
     * @param paciente Paciente a agregar a la lista de espera.
     */
    public void addPaciente(Paciente paciente){
        this.listaEspera.add(paciente);
    }

    /**
     * Metodo publico boolean para sacar un paciente de la lista de espera.
     * <b>pre:</b> paciente no debe ser nulo.
     * <b>post:</b> se saca el paciente de la lista de espera si existe.
     * @param paciente Paciente a sacar de la lista de espera.
     * @return true si el paciente fue sacado, false si no estaba en la lista.
     */
    public boolean sacarPaciente(Paciente paciente){
        return this.listaEspera.remove(paciente);
    }
}
