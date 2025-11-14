package vista;

import controlador.Controlador;
import modelo.modeloAplicacion.NotificacionSimulacion;

public interface IVistaSimulacion {
    public static final String FINALIZAR_SIMULACION = "Finalizar";
    void iniciarSimulacion();
    void setActionListener(Controlador controlador);
    void actualizarEstadoSimulacion(NotificacionSimulacion estado);
}
