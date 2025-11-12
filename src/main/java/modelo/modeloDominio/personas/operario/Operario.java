package modelo.modeloDominio.personas.operario;

import modelo.modeloAplicacion.NotificacionSimulacion;
import modelo.modeloDominio.ambulancia.Ambulancia;
import modelo.modeloDominio.personas.PersonaObservable;
import modelo.modeloDominio.personas.asociado.Asociado;
import modelo.modeloDominio.util.Domicilio;

import java.util.ArrayList;

/**
 * Clase Operario que representa a un operario encargado del mantenimiento de ambulancias.
 * Hereda de PersonaObservable e implementa Runnable para permitir la ejecución en un hilo separado.
 */
public class Operario extends PersonaObservable implements Runnable
{
    private Ambulancia ambulancia;

    /**
     * Constructor parametrizado de la clase Operario.
     * <b>pre:</b> los parámetros no deben ser nulos.
     * <b>post:</b> se crea una instancia de Operario con los valores proporcionados.
     * @param nombre
     * @param apellido
     * @param dni
     * @param telefono
     * @param domicilio
     * @param ambulancia
     */
    public Operario(String nombre, String apellido, String dni, String telefono, Domicilio domicilio, Ambulancia ambulancia)
    {
        super(nombre, apellido,  dni, domicilio, telefono);
        this.ambulancia = ambulancia;
    }

    /**
     * Método privado que verifica si los asociados han terminado de atender sus solicitudes.
     * Si algún asociado ha alcanzado su máximo de solicitudes atendidas, se desactiva la simulación.
     * <b>post:</b> se actualiza el estado de la simulación en la ambulancia.
     */
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
        this.ambulancia.setSimulacionActiva(true);
    }

    /**
     * Método run que ejecuta la lógica del operario en un hilo separado.
     * El operario solicita mantenimiento de la ambulancia cada cierto tiempo mientras la simulación esté activa.
     * <b>post:</b> el operario solicita mantenimiento de la ambulancia periódicamente.
     */
    @Override
    public void run() {
        // pedir mantenimiento de ambulancias
        while(this.ambulancia.isSimulacionActiva()){
            try {
                long tiempoEspera = (long)(Math.random() * 5000 + 5000); // entre 5 y 10 segundos
                Thread.sleep(tiempoEspera); // espera 10 segundos antes de solicitar mantenimiento
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            ambulancia.solicitarMantenimiento(this);
            try{
                System.out.println("Mantenimiento realizado por operario " + this.getDni());
                Thread.sleep(5000);
            } catch (InterruptedException e) {}
            ambulancia.volviendoDelTaller();
            terminaronAsociados();
        }

    }


}
