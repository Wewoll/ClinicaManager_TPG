package modelo.modeloDominio.ambulancia;

import modelo.modeloAplicacion.NotificacionSimulacion;

public class TrasladandoPacienteClinicaState implements State{
    private Ambulancia ambulancia;

    public TrasladandoPacienteClinicaState(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
        this.ambulancia.setOcupado(true);
    }

    @Override
    public void SolicitudDeTraslado(){
        //todo observer observable (no puede atender solicitud)
        this.ambulancia.notifyObservers(new NotificacionSimulacion("La ambulancia no puede atender la solicitud de traslado porque se encuentra trasladando un paciente a la clínica.","AMBULANCIA"));
    }
    @Override
    public void SolicitudDeAtencionDomicilio(){
    }
    @Override
    public void SolicitudMantenimiento(){
        //todo observer observable (no puede atender solicitud)
        this.ambulancia.notifyObservers(new NotificacionSimulacion("La ambulancia no puede atender la solicitud de mantenimiento porque se encuentra trasladando un paciente a la clínica.","AMBULANCIA"));
    }
    @Override
    public void RetornoClinica(){
        try{
            Thread.sleep(1000);
            this.ambulancia.setState(new DisponibleState(this.ambulancia));
            this.ambulancia.setOcupado(false);
        }catch(Exception e){

        }
    }
}
