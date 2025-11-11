package vista;


import controlador.Controlador;
import persistencia.AsociadoDTO;

import java.util.ArrayList;

public interface IVistaPrincipal {
    public static final String ACEPTAR = "ACEPTAR";
    public static final String DAR_ALTA = "DAR_ALTA";
    public static final String DAR_BAJA = "DAR_BAJA";
    void setActionListener(Controlador controlador);
    public AsociadoDTO getNuevoAsociado();
    public void limpiarFormularioAlta();
    public void limpiarFormularioBaja();
    public void actualizarListaAsociados(ArrayList<String> asociados);
    public int getCantAsociados();
    public int getCantSolicitudes();
    void mostrarMensaje(String exito, String s);
    public String getDNI();
}