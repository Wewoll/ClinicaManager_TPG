package controlador;

import modelo.clinica.Clinica;
import vista.IVista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener {
    private Clinica modelo;
    private IVista vista;

    public Controlador(Clinica modelo, IVista vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.vista.setActionListener(this);
    }

    public setVista(IVista vista)
        {
        this.vista = vista;
        this.vista.setActionListener(this);
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {
            case Comandos.ACEPTAR: {
                this.iniciarSimulacion();
                break;
            }
            case Comandos.DAR_ALTA: {
                this.guardarAsociado();
                break;
            }
            case Comandos.DAR_BAJA: {
                this.eliminarAsociado();
            }
        }
    }

    public void iniciarSimulacion(){
        try {
            this.modelo.iniciarSimulacion(this.vista.getAsociados(), this.vista.getSolicudes());
            this.vista.iniciarSimulacion();
        }
        catch (SimulacionNoIniciadaException e) {
            this.vista.mostrarMensaje("Fallo", "No se pudo iniciar la simulacion.");
        }
    }

    public void guardarAsociado() {
        VistaAsociadoDTO datos = this.vista.getNuevoAsociado();
        Asociado asociado = modelo.crearAsociado(datos);

        try {
            this.modelo.guardarNuevoAsociado(asociado);
            this.vista.mostraMensaje("Exito", "Asociado guardado correctamente.");
            this.vista.limpiarFormularioAlta();
        }
        catch (AsociadoNoAgregadoException e) {
            this.vista.mostrarMensaje("Fallo", "No se pudo guardar al asociado.");
        }
    }

    public void eliminarAsociado() {
        String dni = this.vista.getDNI();

        try {
            this.modelo.eliminarAsociado(dni);
            this.vista.mostraMensaje("Exito", "Asociado eliminado correctamente.");
            this.vista.limpiarFormularioBaja();
        }
        catch (AsociadoNoEliminadoException e) {
            this.vista.mostrarMensaje("Fallo", "No se pudo eliminar al asociado.");
        }
    }
}
