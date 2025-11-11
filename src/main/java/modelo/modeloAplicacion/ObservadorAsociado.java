package modelo.modeloAplicacion;


import vista.IVistaPrincipal;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ObservadorAsociado implements Observer
{
    private ArrayList<Observado> observados;
    private IVistaPrincipal vista;

    public ObservadorAsociado(Observado observado)
    {
        this.observados = new ArrayList<Observado>();
        this.observados.add(observado);
    }

    public IVistaPrincipal getVista()
    {
        return vista;
    }

    public void setVista(IVistaPrincipal vista)
    {
        this.vista = vista;
    }

    @Override
    public void update(Observable o, Object arg)
    {

    }
}

