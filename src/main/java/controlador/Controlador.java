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

public class Controlador implements ActionListener {
    private Clinica modelo;
    private IVistaPrincipal vistaPrincipal;
    private IVistaSimulacion vistaSimulacion;

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

    public void finalizarSimulacion()
    {
            this.modelo.finalizarSimulacion();
    }

    public void iniciarSimulacion(){
        try {
            this.modelo.iniciarSimulacion(this.vistaPrincipal.getCantAsociados(), this.vistaPrincipal.getCantSolicitudes(),this.vistaSimulacion);
            this.vistaSimulacion.iniciarSimulacion();
        }
        catch (Exception e) {
            this.vistaPrincipal.mostrarMensaje("Fallo", "No se pudo iniciar la simulacion.");
        }
    }

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

    public void actualizarListaAsociados() throws SQLException {
        // TODO: Hacer que se traigan todos los asociados desde la base de datos
        //ArrayList<AsociadoDTO> asociadosDTO = this.modelo.getAsociadosDTO();
        //this.vistaPrincipal.actualizarListaAsociados(asociadosDTO);
    }
}
