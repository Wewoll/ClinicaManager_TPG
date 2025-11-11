package modelo.modeloAplicacion;


import modelo.modeloDominio.personas.PersonaObservable;
import vista.IVista;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ObservadorAsociado implements Observer
{
    private ArrayList<PersonaObservable> observados;
    private IVista vista;

    public ObservadorAsociado(Observado observado)
    {
        this.observados = new ArrayList<Observado>();
        this.observados.add(observado);
    }

    public IVista getVista()
    {
        return vista;
    }

    public void setVista(IVista vista)
    {
        this.vista = vista;
    }

    @Override
    public void update(Observable o, Object arg)
    {

    }
}

