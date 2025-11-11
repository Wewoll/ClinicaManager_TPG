package modelo.modeloAplicacion;

import java.util.Observable;

public abstract class ObservableSimulacion extends Observable
{
    private EstadoSimulacion estado;

    public ObservableSimulacion()
    {
        this.estado = null;
    }

    public EstadoSimulacion getEstado()
    {
        return estado;
    }

    public void setEstado(EstadoSimulacion estado)
    {
        this.estado = estado;
    }
}
