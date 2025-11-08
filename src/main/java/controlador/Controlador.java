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
            case Comandos.ACEPTAR:
                modelo.iniciarSimulacion(this.vista.getAsociados(), this.vista.getSolicudes());
                this.vista
                break;
            case Comandos.DAR_ALTA:
                break;
            case Comandos.DAR_BAJA:
                break;
        }
    }
}
