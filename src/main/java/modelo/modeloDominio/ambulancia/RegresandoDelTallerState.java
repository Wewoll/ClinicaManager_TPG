package modelo.modeloDominio.ambulancia;

import modelo.modeloAplicacion.NotificacionSimulacion;
/**
 * Clase RegresandoDelTallerState que representa el estado de una ambulancia cuando está regresando del taller.
 * Implementa la interfaz State y define el comportamiento específico para este estado.
 * Contiene un atributo para la ambulancia asociada.
 */
public class RegresandoDelTallerState implements State{
    private Ambulancia ambulancia;

    /**
     * Constructor de la clase RegresandoDelTallerState.
     * <b>post:</b> se crea una instancia de RegresandoDelTallerState asociada a la ambulancia proporcionada y marca la ambulancia como no ocupada.
     * @param ambulancia La ambulancia asociada a este estado.
     */
    public RegresandoDelTallerState(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
        this.ambulancia.setOcupado(false);
    }

    /**
     * Método que maneja la solicitud de traslado.
     * En este estado, la ambulancia no puede atender solicitudes de traslado.
     */
    @Override
    public void SolicitudDeTraslado() {
        this.ambulancia.notifyObservers(new NotificacionSimulacion("La ambulancia no puede atender la solicitud de traslado porque se encuentra regresando del taller.","AMBULANCIA"));
    }

    /**
     * Método que maneja la solicitud de atención a domicilio.
     * En este estado, la ambulancia no puede atender solicitudes de atención a domicilio.
     */
    @Override
    public void SolicitudDeAtencionDomicilio() {
        this.ambulancia.notifyObservers(new NotificacionSimulacion("La ambulancia no puede atender la solicitud de traslado porque se encuentra regresando del taller.","AMBULANCIA"));
    }

    /**
     * Método que maneja la solicitud de mantenimiento.
     * En este estado, la ambulancia no puede atender solicitudes de mantenimiento.
     */
    @Override
    public void SolicitudMantenimiento() {
        this.ambulancia.notifyObservers(new NotificacionSimulacion("La ambulancia no puede atender la solicitud de mantenimiento porque se encuentra regresando del taller.","AMBULANCIA"));
    }

    /**
     * Método que maneja el retorno a la clínica.
     * Cambia el estado de la ambulancia a DisponibleState y notifica a los observadores.
     */
    @Override
    public void RetornoClinica() {
        this.ambulancia.notifyObservers(new NotificacionSimulacion("ℹ️ La ambulancia inicia retorno automatico a la clinica ", "INFO"));
        ambulancia.setState(new DisponibleState(this.ambulancia));
    }



}
