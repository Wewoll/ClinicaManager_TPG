package vista;


import controlador.Controlador;
import modelo.modeloDominio.personas.asociado.Asociado;
import persistencia.AsociadoDTO;

import java.util.ArrayList;
/**
 * Interfaz que define los métodos para la vista principal de la aplicación.
 * Contiene métodos para gestionar asociados y actualizar la interfaz de usuario.
 */
public interface IVistaPrincipal {
    public static final String ACEPTAR = "ACEPTAR";
    public static final String DAR_ALTA = "DAR_ALTA";
    public static final String DAR_BAJA = "DAR_BAJA";
    void setActionListener(Controlador controlador);
    public AsociadoDTO getNuevoAsociado() throws datosAsociadoDTOIncorrectoException;
    public void limpiarFormularioAlta();
    public void limpiarFormularioBaja();
    public void actualizarListaAsociados(ArrayList<AsociadoDTO> asociados);
    public int getCantAsociados() throws datosSimulacionIncorrectosException;
    public int getCantSolicitudes() throws datosSimulacionIncorrectosException;
    void mostrarMensaje(String exito, String s);
    public String getDNI() throws datosAsociadoDTOIncorrectoException;
}