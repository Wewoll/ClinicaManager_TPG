package modelo.modeloDominio.ambulancia;

public class RegresandoClinicaSinPacienteState implements State{
    private Ambulancia ambulancia;

    public RegresandoClinicaSinPacienteState(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
        this.ambulancia.setOcupado(false);
    }

    @Override
    public void SolicitudDeTraslado() {
        this.ambulancia.setState(new TrasladandoPacienteClinicaState(this.ambulancia));
    }

    @Override
    public void SolicitudDeAtencionDomicilio() {
        this.ambulancia.setState(new AtendiendoEnDomicilioState(this.ambulancia));
    }

    @Override
    public void SolicitudMantenimiento() {
        //TODO observer observable (no puede atender solicitud)
    }

    @Override
    public void RetornoClinica() {
        ambulancia.setState(new DisponibleState(this.ambulancia));
    }
}
