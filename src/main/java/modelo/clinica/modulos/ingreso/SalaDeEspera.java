package modelo.clinica.modulos.ingreso;

import modelo.personas.paciente.Paciente;
/**
 * Clase SalaDeEspera que representa la sala de espera de la clinica.
 * Contiene en relacion de composicion el patio y la sala privada.
 */
public class SalaDeEspera {
    Patio patio;
    SalaPrivada salaPrivada;

    /**
     * Constructor de SalaDeEspera
     * <b>pre:</b> patio y salaPrivada no deben ser nulos.
     * <b>post:</b> se crea una sala de espera con el patio y la sala privada proporcionados.
     * @param p Patio de la sala de espera.
     * @param s Sala privada de la sala de espera.
     */
    public SalaDeEspera(Patio p, SalaPrivada s){
        patio = p;
        salaPrivada = s;
    }

    /**
     * Metodo publico Patio para preguntar cual es el patio de la sala de espera.
     * @return patio de la sala de espera.
     */
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

    /**
     * Metodo publico boolean para sacar un paciente de la sala de espera.
     * <b>pre:</b> paciente no debe ser nulo.
     * <b>post:</b> se saca el paciente de la sala de espera si existe en el patio o en la sala privada.
     * @param p Paciente a sacar de la sala de espera.
     * @return true si el paciente fue sacado, false si no estaba en la sala de espera.
     */
    public boolean sacarPaciente(Paciente p){
         boolean res = this.patio.sacarPaciente(p);
         if (!res){
             if (this.salaPrivada.isOcupado() && this.salaPrivada.getPaciente().equals(p)){
                this.salaPrivada.setPaciente(null);
                res = true;
             }
         }
         return res;
    }
}
