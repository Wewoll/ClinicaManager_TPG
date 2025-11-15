package modelo.modeloDominio.ambulancia;

import modelo.modeloAplicacion.NotificacionSimulacion;
/**
 * Clase TrasladandoPacienteClinicaState que representa el estado de una ambulancia cuando está trasladando un paciente a la clínica.
 * Implementa la interfaz State y define el comportamiento específico para este estado.
 * Contiene un atributo para la ambulancia asociada.
 */
public class TrasladandoPacienteClinicaState implements State{
    private Ambulancia ambulancia;

    /**
     * Constructor de la clase TrasladandoPacienteClinicaState.
     * <b>post:</b> se crea una instancia de TrasladandoPacienteClinicaState asociada a la ambulancia proporcionada y marca la ambulancia como ocupada.
     * @param ambulancia La ambulancia asociada a este estado.
     */
    public TrasladandoPacienteClinicaState(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
        this.ambulancia.setOcupado(true);
    }

    /**
     * Método que maneja la solicitud de traslado.
     * En este estado, la ambulancia no puede atender solicitudes de traslado.
     */
    @Override
    public void SolicitudDeTraslado(){
        //todo observer observable (no puede atender solicitud)
        this.ambulancia.notifyObservers(new NotificacionSimulacion("La ambulancia no puede atender la solicitud de traslado porque se encuentra trasladando un paciente a la clínica.","AMBULANCIA"));
    }
    /**
     * Método que maneja la solicitud de atención a domicilio.
     * En este estado, la ambulancia no puede atender solicitudes de atención a domicilio.
     */
    @Override
    public void SolicitudDeAtencionDomicilio(){
        this.ambulancia.notifyObservers(new NotificacionSimulacion("La ambulancia no puede atender la solicitud de atención a domicilio porque se encuentra trasladando un paciente a la clínica.","AMBULANCIA"));
    }
    /**
     * Método que maneja la solicitud de mantenimiento.
     * En este estado, la ambulancia no puede atender solicitudes de mantenimiento.
     */
    @Override
    public void SolicitudMantenimiento(){
        this.ambulancia.notifyObservers(new NotificacionSimulacion("La ambulancia no puede atender la solicitud de mantenimiento porque se encuentra trasladando un paciente a la clínica.","AMBULANCIA"));
    }
    /**
     * Método que maneja el retorno a la clínica.
     * Cambia el estado de la ambulancia a DisponibleState y notifica a los observadores.
     */
    @Override
    public void RetornoClinica(){
        try{
            Thread.sleep(1000);
            this.ambulancia.notifyObservers(new NotificacionSimulacion("La ambulancia incia retorno automatico", "INFO"));
            this.ambulancia.setState(new DisponibleState(this.ambulancia));
            this.ambulancia.setOcupado(false);
        }catch(Exception e){

        }
    }
}
