package app;

import vista.IVista;
import vista.Ventana;

import modelo.clinica.Clinica;
import controlador.Controlador;

public class Prueba {
    public static void main(String[] args) {
        IVista vista = new Ventana();
        Clinica clinica = Clinica.getInstancia();
        Controlador controlador = new Controlador(clinica, vista);
        vista.setActionListener(controlador);
    }
}
