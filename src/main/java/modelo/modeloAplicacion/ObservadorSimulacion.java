package modelo.modeloAplicacion;


import vista.IVistaSimulacion;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
/**
 * Clase ObservadorSimulacion que implementa el patrón Observer para observar cambios en objetos Observable.
 * Notifica a la vista de simulación sobre los cambios en el estado de la simulación.
 * Contiene una lista de objetos observados y una referencia a la vista de simulación.
 */
public class ObservadorSimulacion implements Observer
{
    private ArrayList<Observable> observados;
    private IVistaSimulacion vista;

    /**
     * Constructor de la clase ObservadorSimulacion.
     * <b>post:</b> se crea una instancia de ObservadorSimulacion que observa el objeto Observable proporcionado y tiene la vista de simulación asociada.
     * @param observado El objeto Observable a observar.
     * @param vista La vista de simulación asociada.
     */
    public ObservadorSimulacion(Observable observado, IVistaSimulacion vista)
    {
        this.observados = new ArrayList<Observable>();
        observado.addObserver(this);
        this.observados.add(observado);
        this.vista = vista;
    }

    /**
     * Método get para obtener la vista de simulación.
     * @return La vista de simulación asociada.
     */
    public IVistaSimulacion getVista()
    {
        return vista;
    }

    /**
     * Agrega un nuevo objeto Observable a la lista de observados y se registra como observador.
     * <b>post:</b> el objeto Observable proporcionado se agrega a la lista de observados y este observador se registra para recibir notificaciones.
     * @param observado El objeto Observable a agregar.
     */
    public void agregarObservado(Observable observado)
    {
        assert observado != null;
        this.observados.add(observado);
        observado.addObserver(this);
    }

    /**
     * Método set para establecer la vista de simulación.
     * @param vista La vista de simulación a establecer.
     */
    public void setVista(IVistaSimulacion vista)
    {
        this.vista = vista;
    }

    /**
     * Método update que se llama cuando un objeto Observable notifica un cambio.
     * Actualiza la vista de simulación con el nuevo estado de la simulación.
     * <b>post:</b> la vista de simulación se actualiza con el estado proporcionado en la notificación.
     * @param o El objeto Observable que notificó el cambio.
     * @param arg El argumento que contiene la notificación de la simulación.
     * @throws IllegalArgumentException Si el objeto Observable no es observado por este observador.
     */
    @Override
    public void update(Observable o, Object arg)
    {
        assert o != null;
        assert arg != null;
        if (!this.observados.contains(o))
            throw new IllegalArgumentException("El observable no es observado por este observador.");
        NotificacionSimulacion estado = (NotificacionSimulacion)arg;
        this.vista.actualizarEstadoSimulacion(estado);
    }
}

