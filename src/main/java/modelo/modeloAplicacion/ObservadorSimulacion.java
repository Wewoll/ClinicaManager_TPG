package modelo.modeloAplicacion;


import vista.IVistaSimulacion;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ObservadorSimulacion implements Observer
{
    private ArrayList<Observable> observados;
    private IVistaSimulacion vista;

    public ObservadorSimulacion(Observable observado, IVistaSimulacion vista)
    {
        this.observados = new ArrayList<Observable>();
        observado.addObserver(this);
        this.observados.add(observado);
        this.vista = vista;
    }

    public IVistaSimulacion getVista()
    {
        return vista;
    }

    public void agregarObservado(Observable observado)
    {
        this.observados.add(observado);
        observado.addObserver(this);
    }

    public void setVista(IVistaSimulacion vista)
    {
        this.vista = vista;
    }

    @Override
    public void update(Observable o, Object arg)
    {
        // System.out.println("estado");
        if (!this.observados.contains(o))
            throw new IllegalArgumentException("El observable no es observado por este observador.");
        NotificacionSimulacion estado = (NotificacionSimulacion)arg;
        // System.out.println(estado);
        this.vista.actualizarEstadoSimulacion(estado);
    }
}

