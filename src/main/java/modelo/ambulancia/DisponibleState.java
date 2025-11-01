package modelo.ambulancia;

public class DisponibleState implements State{
    private Ambulancia ambulancia;

    public DisponibleState(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
        this.ambulancia.setOcupado(false);
    }
    @Override
    public void SolicitudDeTraslado(){
        ambulancia.setState(new TrasladandoPacienteClinicaState(this.ambulancia));
    }
    @Override
    public void SolicitudDeAtencionDomicilio(){
        ambulancia.setState(new AtendiendoEnDomicilioState(this.ambulancia));
    }
    @Override
    public void SolicitudMantenimiento(){
        ambulancia.setState(new EnTallerState(this.ambulancia));
    }
    @Override
    public void RetornoClinica(){
    }
}
