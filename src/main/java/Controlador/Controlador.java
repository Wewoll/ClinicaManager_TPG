package Controlador;

import modelo.clinica.Clinica;
import Vista.IVista;

public class Controlador {
    private Clinica clinica;
    private IVista vista;

    public Controlador(Clinica clinica, IVista vista) {
        this.clinica = clinica;
        this.vista = vista;
        this.vista.setControlador(this);
    }
}
