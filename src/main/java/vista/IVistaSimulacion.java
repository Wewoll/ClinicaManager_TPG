package vista;

import controlador.Controlador;
import modelo.modeloAplicacion.NotificacionSimulacion;
/**
 * Interfaz que define los métodos para la vista de simulación.
 * Contiene métodos para iniciar la simulación, actualizar el estado de la simulación y finalizar la simulación.
 */
public interface IVistaSimulacion {
    public static final String FINALIZAR_SIMULACION = "FINALIZAR";
    void iniciarSimulacion();
    void setActionListener(Controlador controlador);
    void actualizarEstadoSimulacion(NotificacionSimulacion estado);
    void FinalizarSimulacion();
}
