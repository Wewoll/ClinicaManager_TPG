package modelo.modeloAplicacion;


import vista.IVistaSimulacion;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ObservadorSimulacion implements Observer
{
    private ArrayList<Observable> observados;
    private IVistaSimulacion vista;

    public ObservadorSimulacion(Observable observado)
    {
        this.observados = new ArrayList<Observable>();
        this.observados.add(observado);
    }

    public IVistaSimulacion getVista()
    {
        return vista;
    }

    public void setVista(IVistaSimulacion vista)
    {
        this.vista = vista;
    }

    @Override
    public void update(Observable o, Object arg)
    {
        if (!this.observados.contains(o))
            throw new IllegalArgumentException("El observable no es observado por este observador.");
        NotificacionSimulacion estado = (NotificacionSimulacion)arg;

        this.vista.actualizarEstadoSimulacion(estado);
    }
}

