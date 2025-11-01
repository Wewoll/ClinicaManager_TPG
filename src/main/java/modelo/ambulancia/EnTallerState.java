package modelo.ambulancia;

public class EnTallerState implements State{
    private Ambulancia ambulancia;

    public EnTallerState(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
        this.ambulancia.setOcupado(true);
    }

    @Override
    public void SolicitudDeTraslado() {
        //TODO observer observable (no puede atender solicitud)
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
