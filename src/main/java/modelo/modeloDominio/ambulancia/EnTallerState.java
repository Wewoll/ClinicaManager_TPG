package modelo.modeloDominio.ambulancia;

import modelo.modeloAplicacion.NotificacionSimulacion;
/**
 * Clase EnTallerState que representa el estado de una ambulancia cuando está en el taller.
 * Implementa la interfaz State y define el comportamiento específico para este estado.
 * Contiene un atributo para la ambulancia asociada.
 */
public class EnTallerState implements State{
    private Ambulancia ambulancia;

    /**
     * Constructor de la clase EnTallerState.
     * <b>post:</b> se crea una instancia de EnTallerState asociada a la ambulancia proporcionada y marca la ambulancia como ocupada.
     * @param ambulancia La ambulancia asociada a este estado.
     */
    public EnTallerState(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
        this.ambulancia.setOcupado(true);
    }
    /**
     * Método que maneja la solicitud de traslado.
     * En este estado, la ambulancia no puede atender solicitudes de traslado.
     */
    @Override
    public void SolicitudDeTraslado() {
        this.ambulancia.notifyObservers(new NotificacionSimulacion("La ambulancia no puede atender la solicitud de traslado porque se encuentra en el taller.","AMBULANCIA"));
    }

    /**
     * Método que maneja la solicitud de atención a domicilio.
     * En este estado, la ambulancia no puede atender solicitudes de atención a domicilio.
     */
    @Override
    public void SolicitudDeAtencionDomicilio() {
        this.ambulancia.notifyObservers(new NotificacionSimulacion("La ambulancia no puede atender la solicitud de traslado porque se encuentra en el taller.","AMBULANCIA"));
    }

    /**
     * Método que maneja la solicitud de mantenimiento.
     * Cambia el estado de la ambulancia a RegresandoDelTallerState.
     */
    @Override
    public void SolicitudMantenimiento() {
        this.ambulancia.setState(new RegresandoDelTallerState(this.ambulancia));
    }

    /**
     * Método que maneja el retorno a la clínica.
     * En este estado, la ambulancia no puede retornar a la clínica.
     */
    @Override
    public void RetornoClinica() {
        this.ambulancia.notifyObservers(new NotificacionSimulacion("La ambulancia no puede atender la solicitud de traslado porque se encuentra en el taller.","AMBULANCIA"));
    }
}
