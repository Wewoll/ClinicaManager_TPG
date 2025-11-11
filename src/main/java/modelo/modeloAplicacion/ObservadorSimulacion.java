package modelo.modeloAplicacion;


import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ObservadorSimulacion implements Observer
{
    private ArrayList<ObservableSimulacion> observados;
    private IVistaSimulacion vista;

    public ObservadorSimulacion(ObservableSimulacion observado)
    {
        this.observados = new ArrayList<ObservableSimulacion>();
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
        ObservableSimulacion observado = (ObservableSimulacion)o;
        if (!this.observados.contains(observado))
            throw new IllegalArgumentException("El observable no es observado por este observador.");
        EstadoSimulacion estado = (EstadoSimulacion)arg;

        this.vista.actualizarEstadoSimulacion(estado);
    }
}

