package vista;

import controlador.Controlador;
import modelo.modeloAplicacion.EstadoSimulacion;

public interface IVistaSimulacion {
    void iniciarSimulacion();
    void setActionListener(Controlador controlador);
    void actualizarEstadoSimulacion(EstadoSimulacion estado);
}
