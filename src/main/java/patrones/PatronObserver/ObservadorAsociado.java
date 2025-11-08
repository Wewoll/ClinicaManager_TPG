package patrones.PatronObserver;


import vista.IVista;

import java.util.ArrayList;

public class ObservadorAsociado implements IObservador
{
    private ArrayList<Observado> observados;
    private IVista vista;

    public ObservadorAsociado(Observado observado)
    {
        this.observados = new ArrayList<Observado>();
        this.observados.add(observado);
    }
    @Override
    public void actualizar(String mensaje)
    {
        
    }


}

