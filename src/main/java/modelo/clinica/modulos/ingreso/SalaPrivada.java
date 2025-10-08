package modelo.clinica.modulos.ingreso;

import modelo.personas.paciente.Paciente;
/**
 * Clase SalaPrivada que representa una sala privada en la clinica.
 * Contiene un estado de ocupacion y un paciente asignado.
 */
public class SalaPrivada {
    private boolean ocupado;
    private Paciente paciente;

    /**
     * Constructor de SalaPrivada
     * <b>post:</b> se crea una sala privada desocupada sin paciente asignado.
     */
    public SalaPrivada(){
        this.ocupado = false;
        this.paciente = null;
    }
    /**
     * Metodo publico boolean para preguntar si la sala privada esta ocupada.
     * @return true si la sala esta ocupada, false si esta desocupada.
     */
    public boolean isOcupado() {
        return ocupado;
    }

    /**
     * Metodo publico void para setear el estado de ocupacion de la sala privada.
     * <b>pre:</b> ocupado no debe ser nulo.
     * <b>post:</b> se setea el estado de ocupacion con el valor proporcionado.
     * @param ocupado Estado de ocupacion de la sala privada.
     */
    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
    /**
     * Metodo publico Paciente para preguntar cual es el paciente asignado a la sala privada.
     * @return paciente asignado a la sala privada.
     */
    public Paciente getPaciente() {
        return paciente;
    }
    /**
     * Metodo publico void para setear el paciente asignado a la sala privada.
     * <b>pre:</b> paciente no debe ser nulo.
     * <b>post:</b> se setea el paciente asignado con el valor proporcionado.
     * @param paciente Paciente a asignar a la sala privada.
     */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
