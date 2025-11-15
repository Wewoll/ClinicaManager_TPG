package modelo.modeloDominio.ambulancia;

import modelo.modeloAplicacion.NotificacionSimulacion;

/**
 * Clase DisponibleState que representa el estado de una ambulancia cuando está disponible.
 * Implementa la interfaz State y define el comportamiento específico para este estado.
 * Contiene un atributo para la ambulancia asociada.
 */
public class DisponibleState implements State{
    private Ambulancia ambulancia;

    /**
     * Constructor de la clase DisponibleState.
     * <b>post:</b> se crea una instancia de DisponibleState asociada a la ambulancia proporcionada y marca la ambulancia como no ocupada.
     * @param ambulancia La ambulancia asociada a este estado.
     */
    public DisponibleState(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
        this.ambulancia.setOcupado(false);
    }
    /**
     * Método que maneja la solicitud de traslado.
     * Cambia el estado de la ambulancia a TrasladandoPacienteClinica.
     */
    @Override
    public void SolicitudDeTraslado(){
        ambulancia.setState(new TrasladandoPacienteClinicaState(this.ambulancia));
    }
    /**
     * Método que maneja la solicitud de atención a domicilio.
     * Cambia el estado de la ambulancia a AtendiendoEnDomicilio.
     */
    @Override
    public void SolicitudDeAtencionDomicilio(){
        ambulancia.setState(new AtendiendoEnDomicilioState(this.ambulancia));
    }
    /**
     * Método que maneja la solicitud de mantenimiento.
     * Cambia el estado de la ambulancia a EnTaller.
     */
    @Override
    public void SolicitudMantenimiento(){
        ambulancia.setState(new EnTallerState(this.ambulancia));
    }
    /**
     * Método que maneja el retorno a la clínica.
     * En este estado, la ambulancia ya se encuentra en la clínica, por lo que no realiza ninguna acción.
     */
    @Override
    public void RetornoClinica(){
        this.ambulancia.notifyObservers(new NotificacionSimulacion("ℹ️ La ambulancia ya esta en la clinica", "INFO"));

    }
}
