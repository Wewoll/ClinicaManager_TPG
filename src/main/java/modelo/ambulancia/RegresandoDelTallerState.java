package modelo.ambulancia;

public class RegresandoDelTallerState implements State{
    private Ambulancia ambulancia;

    public RegresandoDelTallerState(Ambulancia ambulancia) {
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
        //TODO observer observable (no puede atender solicitud)
    }

    @Override
    public void RetornoClinica() {
        ambulancia.setState(new DisponibleState(this.ambulancia));
    }



}
