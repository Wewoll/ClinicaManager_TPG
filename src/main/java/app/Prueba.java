package app;

import vista.IVistaPrincipal;
import vista.IVistaSimulacion;
import vista.VentanaPrincipal;

import modelo.modeloDominio.clinica.Clinica;
import controlador.Controlador;
import vista.VentanaSimulacion;

public class Prueba {
    public static void main(String[] args) {
        IVistaPrincipal vistaPrincipal = new VentanaPrincipal();
        IVistaSimulacion vistaSimulacion = new VentanaSimulacion();
        Clinica clinica = Clinica.getInstancia();
        Controlador controlador = new Controlador(clinica, vistaPrincipal, vistaSimulacion);
    }
}
