package vista;

import controlador.Controlador;

public interface IVistaSimulacion {
    void iniciarSimulacion();
    void setActionListener(Controlador controlador);
}
