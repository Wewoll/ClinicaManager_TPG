package modelo.modeloDominio.ambulancia;

import modelo.modeloAplicacion.NotificacionSimulacion;
/**
 * Clase RegresandoClinicaSinPacienteState que representa el estado de una ambulancia cuando está regresando a la clínica sin paciente.
 * Implementa la interfaz State y define el comportamiento específico para este estado.
 * Contiene un atributo para la ambulancia asociada.
 */
public class RegresandoClinicaSinPacienteState implements State{
    private Ambulancia ambulancia;

    /**
     * Constructor de la clase RegresandoClinicaSinPacienteState.
     * <b>post:</b> se crea una instancia de RegresandoClinicaSinPacienteState asociada a la ambulancia proporcionada y marca la ambulancia como no ocupada.
     * @param ambulancia La ambulancia asociada a este estado.
     */
    public RegresandoClinicaSinPacienteState(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
        this.ambulancia.setOcupado(false);
    }

    /**
     * Método que maneja la solicitud de traslado.
     * Cambia el estado de la ambulancia a TrasladandoPacienteClinica.
     */
    @Override
    public void SolicitudDeTraslado() {
        this.ambulancia.setState(new TrasladandoPacienteClinicaState(this.ambulancia));
    }
    /**
     * Método que maneja la solicitud de atención a domicilio.
     * Cambia el estado de la ambulancia a AtendiendoEnDomicilio.
     */
    @Override
    public void SolicitudDeAtencionDomicilio() {
        this.ambulancia.setState(new AtendiendoEnDomicilioState(this.ambulancia));
    }

    /**
     * Método que maneja la solicitud de mantenimiento.
     * En este estado, la ambulancia no puede atender solicitudes de mantenimiento.
     */
    @Override
    public void SolicitudMantenimiento() {
        this.ambulancia.notifyObservers(new NotificacionSimulacion("La ambulancia no puede atender la solicitud de mantenimiento porque se encuentra regresando a la clínica sin paciente.","AMBULANCIA"));
    }

    /**
     * Método que maneja el retorno a la clínica.
     * Cambia el estado de la ambulancia a DisponibleState y notifica a los observadores.
     */
    @Override
    public void RetornoClinica() {
        this.ambulancia.notifyObservers(new NotificacionSimulacion("ℹ️ La ambulancia inicia retorno automatico a la clinica ", "INFO"));
        ambulancia.setState(new DisponibleState(this.ambulancia));
    }
}
