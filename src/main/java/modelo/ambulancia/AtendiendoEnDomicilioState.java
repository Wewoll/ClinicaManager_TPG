package modelo.ambulancia;

public class AtendiendoEnDomicilioState implements State{
    private Ambulancia ambulancia;

    public AtendiendoEnDomicilioState(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
        this.ambulancia.setOcupado(true);
    }

    @Override
    public void SolicitudDeTraslado(){
    }
    @Override
    public void SolicitudDeAtencionDomicilio(){
        //todo observer observable (no puede atender solicitud)
    }
    @Override
    public void SolicitudMantenimiento(){
        //todo observer observable (no puede atender solicitud)

    }
    @Override
    public void RetornoClinica(){
        ambulancia.setState(new RegresandoClinicaSinPacienteState(this.ambulancia));
        //todo observer observable (no puede atender solicitud)
    }
}
