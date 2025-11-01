package controlador;

import modelo.clinica.Clinica;
import vista.IVista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener {
    private Clinica clinica;
    private IVista vista;

    public Controlador(Clinica clinica, IVista vista) {
        this.clinica = clinica;
        this.vista = vista;
        this.vista.setControlador(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
