package modelo.modeloDominio.ambulancia;

import modelo.modeloAplicacion.NotificacionSimulacion;

public class EnTallerState implements State{
    private Ambulancia ambulancia;

    public EnTallerState(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
        this.ambulancia.setOcupado(true);
    }

    @Override
    public void SolicitudDeTraslado() {
        //TODO observer observable (no puede atender solicitud)
        this.ambulancia.notifyObservers(new NotificacionSimulacion("La ambulancia no puede atender la solicitud de traslado porque se encuentra en el taller.","AMBULANCIA"));
    }

    @Override
    public void SolicitudDeAtencionDomicilio() {}

    @Override
    public void SolicitudMantenimiento() {
        this.ambulancia.setState(new RegresandoDelTallerState(this.ambulancia));
    }

    @Override
    public void RetornoClinica() {}
}
