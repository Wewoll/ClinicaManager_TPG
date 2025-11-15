package modelo.modeloDominio.ambulancia;

import modelo.modeloAplicacion.NotificacionSimulacion;
import modelo.modeloDominio.personas.asociado.Asociado;

import java.util.ArrayList;
/**
 * Clase RetornoAutomatico que implementa Runnable para manejar el retorno automático de una ambulancia.
 * Contiene un atributo para la ambulancia asociada y define el comportamiento del hilo para verificar si los asociados han terminado sus solicitudes.
 */
public class RetornoAutomatico implements Runnable
{
    private Ambulancia ambulancia;

    /**
     * Constructor de la clase RetornoAutomatico.
     * <b>post:</b> se crea una instancia de RetornoAutomatico asociada a la ambulancia proporcionada.
     * @param ambulancia La ambulancia asociada a este retorno automático.
     */
    public RetornoAutomatico(Ambulancia ambulancia)
    {
        this.ambulancia = ambulancia;
    }

    /**
     * Método privado que verifica si todos los asociados han terminado sus solicitudes.
     * Si algún asociado ha alcanzado su máximo de solicitudes atendidas, se desactiva la simulación de la ambulancia.
     */
    private void terminaronAsociados()
    {
        ArrayList<Asociado> asociados = this.ambulancia.getAsociados();
        for (Asociado asociado : asociados)
        {
            if (asociado.getCantSolicitudesAtendidas() >= asociado.getMaxCantSolicitudes())
            {
                this.ambulancia.setSimulacionActiva(false);
                return;
            }
        }
        // this.ambulancia.setSimulacionActiva(true);
    }
    /**
     * Método run que se ejecuta en el hilo.
     * Verifica periódicamente si los asociados han terminado sus solicitudes y, si la simulación está activa, inicia el retorno automático de la ambulancia.
     */
    @Override
    public void run()
    {
        while (ambulancia.isSimulacionActiva())
        {
            terminaronAsociados();
            try
            {
                Thread.sleep(10000); // Espera 10 segundos antes de iniciar el mantenimiento
            } catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                return; // Salir si el hilo es interrumpido
            }
            ambulancia.retornoAutomatico(this);
        }
    }
}

