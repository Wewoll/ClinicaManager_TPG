package modelo.modeloDominio.ambulancia;

import modelo.modeloAplicacion.NotificacionSimulacion;
import modelo.modeloDominio.personas.asociado.Asociado;

import java.util.ArrayList;

public class RetornoAutomatico implements Runnable
{
    private Ambulancia ambulancia;

    public RetornoAutomatico(Ambulancia ambulancia)
    {
        this.ambulancia = ambulancia;
    }
    private void terminaronAsociados()
    {
        ArrayList<Asociado> asociados = this.ambulancia.getAsociados();
        for (Asociado asociado : asociados)
        {
            // System.out.println("Cantidad de solicitudes atendidas por asociado " + asociado.getDni() + ": " + asociado.getCantSolicitudesAtendidas() + "/" + asociado.getMaxCantSolicitudes());
            if (asociado.getCantSolicitudesAtendidas() >= asociado.getMaxCantSolicitudes())
            {
                this.ambulancia.setSimulacionActiva(false);
                return;
            }
        }
        // this.ambulancia.setSimulacionActiva(true);
    }
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
            // System.out.println(">> Iniciando retorno automatico de la ambulancia");
            ambulancia.retornoAutomatico(this);
        }
    }
}

