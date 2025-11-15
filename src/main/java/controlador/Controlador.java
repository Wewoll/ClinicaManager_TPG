package controlador;

import modelo.modeloDominio.clinica.Clinica;
import persistencia.AsociadoDTO;
import vista.IVistaPrincipal;
import vista.IVistaSimulacion;
import vista.datosAsociadoDTOIncorrectoException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase Controlador que maneja las interacciones entre la vista y el modelo.
 * Implementa ActionListener para manejar eventos de la interfaz de usuario.
 * Contiene referencias al modelo Clinica y a las vistas IVistaPrincipal e IVistaSimulacion.
 */
public class Controlador implements ActionListener {
    private Clinica modelo;
    private IVistaPrincipal vistaPrincipal;
    private IVistaSimulacion vistaSimulacion;


    /**
     * Constructor de la clase Controlador.
     * <b>post:</b> se crea una instancia de Controlador con el modelo y las vistas proporcionadas.
     * @param modelo La instancia del modelo Clinica.
     * @param vistaPrincipal La instancia de la vista IVistaPrincipal.
     * @param vistaSimulacion La instancia de la vista IVistaSimulacion.
     */
    public Controlador(Clinica modelo, IVistaPrincipal vistaPrincipal, IVistaSimulacion vistaSimulacion) {
        this.modelo = modelo;
        this.vistaPrincipal = vistaPrincipal;
        this.vistaPrincipal.setActionListener(this);
        this.vistaSimulacion = vistaSimulacion;
        this.vistaSimulacion.setActionListener(this);
        try {
            this.actualizarListaAsociados();
        }
        catch (SQLException e) {
            this.vistaPrincipal.mostrarMensaje("Fallo", "No se pudo cargar la lista de asociados.");
        }
    }

    /**
     * Maneja los eventos de acción de la interfaz de usuario.
     * <b>post:</b> se ejecuta la acción correspondiente según el comando recibido.
     * @param e El evento de acción generado por la interfaz de usuario.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case IVistaPrincipal.ACEPTAR: {
                this.iniciarSimulacion();
                break;
            }
            case IVistaPrincipal.DAR_ALTA: {
                this.guardarAsociado();
                break;
            }
            case IVistaPrincipal.DAR_BAJA: {
                this.eliminarAsociado();
            }
            case IVistaSimulacion.FINALIZAR_SIMULACION: {
                this.finalizarSimulacion();
                break;
            }
        }
    }

    /**
     * Finaliza la simulación en el modelo.
     * <b>post:</b> se finaliza la simulación en el modelo Clinica.
     */
    public void finalizarSimulacion()
    {
            this.modelo.finalizarSimulacion();
    }

    /**
     * Inicia la simulación en el modelo con los parámetros obtenidos de la vista principal.
     * <b>post:</b> se inicia la simulación en el modelo Clinica y se notifica a la vista de simulación.
     */
    public void iniciarSimulacion(){
        try {
            this.modelo.iniciarSimulacion(this.vistaPrincipal.getCantAsociados(), this.vistaPrincipal.getCantSolicitudes(),this.vistaSimulacion);
            this.vistaSimulacion.iniciarSimulacion();
        }
        catch (Exception e) {
            this.vistaPrincipal.mostrarMensaje("Fallo", "No se pudo iniciar la simulacion.");
        }
    }

    /**
     * Guarda un nuevo asociado en el modelo con los datos obtenidos de la vista principal.
     * <b>post:</b> se guarda un nuevo asociado en el modelo Clinica y se actualiza la lista de asociados en la vista principal.
     */
    public void guardarAsociado() {
        try{
            AsociadoDTO datos = this.vistaPrincipal.getNuevoAsociado();
            try {
                this.modelo.guardarNuevoAsociado(datos);
                this.vistaPrincipal.mostrarMensaje("Exito", "Asociado guardado correctamente.");
                this.vistaPrincipal.limpiarFormularioAlta();
                this.actualizarListaAsociados();
            }
            catch (SQLException e) {
                this.vistaPrincipal.mostrarMensaje("Fallo", "No se pudo guardar al asociado.");
            }
        }
        catch(datosAsociadoDTOIncorrectoException e){
            this.vistaPrincipal.mostrarMensaje("Fallo", "Datos del asociado incorrectos.");
            return;
        }
    }

    /**
     * Elimina un asociado del modelo utilizando el DNI obtenido de la vista principal.
     * <b>post:</b> se elimina el asociado del modelo Clinica y se actualiza la lista de asociados en la vista principal.
     */
    public void eliminarAsociado() {
        try{
            String dni = this.vistaPrincipal.getDNI();
            try {
                this.modelo.eliminarAsociado(dni);
                this.vistaPrincipal.mostrarMensaje("Exito", "Asociado eliminado correctamente.");
                this.vistaPrincipal.limpiarFormularioBaja();
                this.actualizarListaAsociados();
            }
            catch (SQLException e) {
                this.vistaPrincipal.mostrarMensaje("Fallo", "No se pudo eliminar al asociado.");
            }
        }
        catch(datosAsociadoDTOIncorrectoException e){
            this.vistaPrincipal.mostrarMensaje("Fallo", "DNI del asociado incorrecto.");
            return;
        }
    }

    /**
     * Actualiza la lista de asociados en la vista principal obteniendo los datos del modelo.
     * <b>post:</b> se actualiza la lista de asociados en la vista principal.
     * @throws SQLException Si ocurre un error al obtener los datos de los asociados.
     */
    public void actualizarListaAsociados() throws SQLException {
        // TODO: Hacer que se traigan todos los asociados desde la base de datos
        ArrayList<AsociadoDTO> asociadosDTO = this.modelo.getAsociadosDTO();
        this.vistaPrincipal.actualizarListaAsociados(asociadosDTO);
    }
}
