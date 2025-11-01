package modelo.ambulancia;

public class TrasladandoPacienteClinicaState implements State{
    private Ambulancia ambulancia;

    public TrasladandoPacienteClinicaState(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
        this.ambulancia.setOcupado(true);
    }

    @Override
    public void SolicitudDeTraslado(){
        //todo observer observable (no puede atender solicitud)
    }
    @Override
    public void SolicitudDeAtencionDomicilio(){
    }
    @Override
    public void SolicitudMantenimiento(){
        //todo observer observable (no puede atender solicitud)
    }
    @Override
    public void RetornoClinica(){
    }
}
