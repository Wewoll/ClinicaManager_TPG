package modelo.clinica.modulos.ingreso;

import modelo.personas.paciente.Paciente;
/**
 * Clase SistemaIngreso que representa el sistema de ingreso de pacientes a la clinica.
 * Contiene en relacion de composicion la sala de espera.
 */
public class SistemaIngreso
{
    private SalaDeEspera salaDeEspera;
    /**
     * Constructor de SistemaIngreso
     * <b>post:</b> se crea un sistema de ingreso con una sala de espera.
     */
    public SistemaIngreso() {
        this.salaDeEspera = new SalaDeEspera(new Patio(), new SalaPrivada());
    }
    /**
     * Metodo publico void para ingresar un paciente a la sala de espera.
     * <b>pre:</b> paciente no debe ser nulo.
     * <b>post:</b> se ingresa el paciente a la sala de espera.
     * @param p Paciente a ingresar a la sala de espera.
     */
    public void ingresaPaciente(Paciente p){
        salaDeEspera.ingresaPaciente(p);
    }

    /**
     * Metodo publico boolean para sacar un paciente de la sala de espera.
     * <b>pre:</b> paciente no debe ser nulo.
     * <b>post:</b> se saca el paciente de la sala de espera si existe en el patio o en la sala privada.
     * @param p Paciente a sacar de la sala de espera.
     * @return true si el paciente fue sacado, false si no estaba en la sala de espera.
     */
    public boolean sacarPacienteSalaDeEspera(Paciente p){
        return salaDeEspera.sacarPaciente(p);
    }
}
