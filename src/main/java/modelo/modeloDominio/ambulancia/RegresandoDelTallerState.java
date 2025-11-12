package modelo.modeloDominio.ambulancia;

import modelo.modeloAplicacion.NotificacionSimulacion;

public class RegresandoDelTallerState implements State{
    private Ambulancia ambulancia;

    public RegresandoDelTallerState(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
        this.ambulancia.setOcupado(false);
    }

    @Override
    public void SolicitudDeTraslado() {
        //TODO observer observable (no puede atender solicitud)
        this.ambulancia.notifyObservers(new NotificacionSimulacion("La ambulancia no puede atender la solicitud de traslado porque se encuentra regresando del taller.","AMBULANCIA"));
    }

    @Override
    public void SolicitudDeAtencionDomicilio() {}

    @Override
    public void SolicitudMantenimiento() {
        //TODO observer observable (no puede atender solicitud)
        this.ambulancia.notifyObservers(new NotificacionSimulacion("La ambulancia no puede atender la solicitud de mantenimiento porque se encuentra regresando del taller.","AMBULANCIA"));
    }

    @Override
    public void RetornoClinica() {
        ambulancia.setState(new DisponibleState(this.ambulancia));
    }



}
