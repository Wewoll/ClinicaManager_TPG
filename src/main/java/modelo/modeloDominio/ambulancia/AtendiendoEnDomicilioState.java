package modelo.modeloDominio.ambulancia;

import modelo.modeloAplicacion.NotificacionSimulacion;
/**
 * Clase AtendiendoEnDomicilioState que representa el estado de una ambulancia cuando está atendiendo en domicilio.
 * Implementa la interfaz State y define el comportamiento específico para este estado.
 * Contiene un atributo para la ambulancia asociada.
 */
public class AtendiendoEnDomicilioState implements State{
    private Ambulancia ambulancia;

    /**
     * Constructor de la clase AtendiendoEnDomicilioState.
     * <b>post:</b> se crea una instancia de AtendiendoEnDomicilioState asociada a la ambulancia proporcionada y marca la ambulancia como ocupada.
     * @param ambulancia La ambulancia asociada a este estado.
     */
    public AtendiendoEnDomicilioState(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
        this.ambulancia.setOcupado(true);
    }
    /**
     * Método que maneja la solicitud de traslado.
     * En este estado, la ambulancia no puede atender solicitudes de traslado.
     */
    @Override
    public void SolicitudDeTraslado(){
        this.ambulancia.notifyObservers(new NotificacionSimulacion("❌ La ambulancia no puede atender la solicitud de traslado porque se encuentra atendiendo un paciente en domicilio.","AMBULANCIA"));
    }

    /**
     * Método que maneja la solicitud de atención a domicilio.
     * En este estado, la ambulancia no puede atender solicitudes de atención a domicilio.
     */
    @Override
    public void SolicitudDeAtencionDomicilio(){
        this.ambulancia.notifyObservers(new NotificacionSimulacion("❌ La ambulancia no puede atender la solicitud de atención a domicilio porque se encuentra atendiendo un paciente en domicilio.","AMBULANCIA"));
    }
    /**
     * Método que maneja la solicitud de mantenimiento.
     * En este estado, la ambulancia no puede atender solicitudes de mantenimiento.
     */
    @Override
    public void SolicitudMantenimiento(){
        this.ambulancia.notifyObservers(new NotificacionSimulacion("❌ La ambulancia no puede atender la solicitud de mantenimiento porque se encuentra atendiendo un paciente en domicilio.","AMBULANCIA"));
    }
    /**
     * Método que maneja el retorno a la clínica.
     * Cambia el estado de la ambulancia a RegresandoClinicaSinPaciente y notifica a los observadores.
     */
    @Override
    public void RetornoClinica(){
        this.ambulancia.notifyObservers(new NotificacionSimulacion("ℹ️ La ambulancia inicia retorno automatico a la clinica sin paciente", "INFO"));
        ambulancia.setState(new RegresandoClinicaSinPacienteState(this.ambulancia));
    }
}
