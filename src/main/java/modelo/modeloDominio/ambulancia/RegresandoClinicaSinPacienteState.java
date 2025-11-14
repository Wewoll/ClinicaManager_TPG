package modelo.modeloDominio.ambulancia;

import modelo.modeloAplicacion.NotificacionSimulacion;

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
        this.ambulancia.notifyObservers(new NotificacionSimulacion("La ambulancia no puede atender la solicitud de mantenimiento porque se encuentra regresando a la clínica sin paciente.","AMBULANCIA"));
    }

    @Override
    public void RetornoClinica() {
        this.ambulancia.notifyObservers(new NotificacionSimulacion("ℹ️ La ambulancia inicia retorno automatico a la clinica ", "INFO"));
        ambulancia.setState(new DisponibleState(this.ambulancia));
    }
}
