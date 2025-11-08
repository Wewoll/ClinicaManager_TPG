package vista;


import controlador.Controlador;

import java.util.ArrayList;

public interface IVista {
    public static final String ACEPTAR = "ACEPTAR";
    public static final String DAR_ALTA = "DAR_ALTA";
    public static final String DAR_BAJA = "DAR_BAJA";
    void setActionListener(Controlador controlador);
    public VistaAsociadoDTO getNuevoAsociado();
    public void iniciarSimulacion();
    public void mostrarMensaje(String titulo, String mensaje);
    public void limpiarFormularioAlta();
    public void limpiarFormularioBaja();
    public void actualizarListaAsociados(ArrayList<String> asociados);
}