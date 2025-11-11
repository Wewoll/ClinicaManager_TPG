package controlador;

import modelo.clinica.Clinica;
import modelo.modeloDominio.clinica.Clinica;
import persistencia.AsociadoDTO;
import vista.IVistaPrincipal;
import vista.IVistaSimulacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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
        }
    }

    public void iniciarSimulacion(){
        try {
            this.modelo.iniciarSimulacion(this.vistaPrincipal.getCantAsociados(), this.vistaPrincipal.getCantSolicitudes());
            this.vistaSimulacion.iniciarSimulacion();
        }
        catch (InterruptedException e) {
            this.vistaPrincipal.mostrarMensaje("Fallo", "No se pudo iniciar la simulacion.");
        }
    }

    public void guardarAsociado() {
        AsociadoDTO datos = this.vistaPrincipal.getNuevoAsociado();

        try {
            this.modelo.guardarNuevoAsociado(datos);
            this.vistaPrincipal.mostraMensaje("Exito", "Asociado guardado correctamente.");
            this.vistaPrincipal.limpiarFormularioAlta();
            this.vistaPrincipal.actualizarListaAsociados();
        }
        catch (SQLException e) {
            this.vistaPrincipal.mostrarMensaje("Fallo", "No se pudo guardar al asociado.");
        }
    }

    public void eliminarAsociado() {
        String dni = this.vistaPrincipal.getDNI();

        try {
            this.modelo.eliminarAsociado(dni);
            this.vistaPrincipal.mostraMensaje("Exito", "Asociado eliminado correctamente.");
            this.vistaPrincipal.limpiarFormularioBaja();
            this.vistaPrincipal.actualizarListaAsociados();
        }
        catch (SQLException e) {
            this.vistaPrincipal.mostrarMensaje("Fallo", "No se pudo eliminar al asociado.");
        }
    }
}
