package app;

import Vista.IVista;
import Vista.Ventana;

import modelo.clinica.Clinica;
import Controlador.Controlador;

public class Prueba {
    public static void main(String[] args) {
        IVista vista = new Ventana();
        Clinica clinica = Clinica.getInstancia();
        Controlador controlador = new Controlador(clinica, vista);
        vista.setControlador(controlador);
    }
}
