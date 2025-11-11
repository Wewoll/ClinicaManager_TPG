package controlador;

import modelo.clinica.Clinica;
import modelo.personas.asociado.Asociado;
import persistencia.AsociadoDTO;
import vista.IVista;
import vista.VistaAsociadoDTO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Controlador implements ActionListener {
    private Clinica modelo;
    private IVista vista;

    public Controlador(Clinica modelo, IVista vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.vista.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {
            case IVista.ACEPTAR: {
                this.iniciarSimulacion();
                break;
            }
            case IVista.DAR_ALTA: {
                this.guardarAsociado();
                break;
            }
            case IVista.DAR_BAJA: {
                this.eliminarAsociado();
            }
        }
    }

    public void iniciarSimulacion(){
        try {
            this.modelo.iniciarSimulacion (this.vista.getAsociados(), this.vista.getSolicudes());
            this.vista.iniciarSimulacion();
        }
        catch (InterruptedException e) {
            this.vista.mostrarMensaje("Fallo", "No se pudo iniciar la simulacion.");
        }
    }

    public void guardarAsociado() {
        AsociadoDTO datos = this.vista.getNuevoAsociado();

        try {
            this.modelo.guardarNuevoAsociado(datos);
            this.vista.mostraMensaje("Exito", "Asociado guardado correctamente.");
            this.vista.limpiarFormularioAlta();
            this.vista.actualizarListaAsociados();
        }
        catch (SQLException e) {
            this.vista.mostrarMensaje("Fallo", "No se pudo guardar al asociado.");
        }
    }

    public void eliminarAsociado() {
        String dni = this.vista.getDNI();

        try {
            this.modelo.eliminarAsociado(dni);
            this.vista.mostraMensaje("Exito", "Asociado eliminado correctamente.");
            this.vista.limpiarFormularioBaja();
            this.vista.actualizarListaAsociados();
        }
        catch (SQLException e) {
            this.vista.mostrarMensaje("Fallo", "No se pudo eliminar al asociado.");
        }
    }
}
