package modelo.modeloDominio.ambulancia;

import modelo.modeloAplicacion.NotificacionSimulacion;

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
        this.ambulancia.notifyObservers(new NotificacionSimulacion("❌ La ambulancia no puede atender la solicitud de atención a domicilio porque se encuentra atendiendo un paciente en domicilio.","AMBULANCIA"));
    }
    @Override
    public void SolicitudMantenimiento(){
        //todo observer observable (no puede atender solicitud)
        this.ambulancia.notifyObservers(new NotificacionSimulacion("❌ La ambulancia no puede atender la solicitud de mantenimiento porque se encuentra atendiendo un paciente en domicilio.","AMBULANCIA"));
    }
    @Override
    public void RetornoClinica(){
        this.ambulancia.notifyObservers(new NotificacionSimulacion("ℹ️ La ambulancia inicia retorno automatico a la clinica sin paciente", "INFO"));
        ambulancia.setState(new RegresandoClinicaSinPacienteState(this.ambulancia));
        //todo observer observable (no puede atender solicitud)
    }
}
