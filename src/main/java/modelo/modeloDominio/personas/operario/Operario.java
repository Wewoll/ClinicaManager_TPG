package modelo.modeloDominio.personas.operario;

import modelo.modeloAplicacion.NotificacionSimulacion;
import modelo.modeloDominio.ambulancia.Ambulancia;
import modelo.modeloDominio.personas.PersonaObservable;
import modelo.modeloDominio.personas.asociado.Asociado;
import modelo.modeloDominio.util.Domicilio;
import modelo.modeloDominio.util.TiempoMuerto;

import java.util.ArrayList;

/**
 * Clase Operario que representa a un operario encargado del mantenimiento de ambulancias.
 */
public class Operario extends PersonaObservable implements Runnable {
    private Ambulancia ambulancia;

    /**
     * Constructor parametrizado de la clase Operario.
     */
    public Operario(String nombre, String apellido, String dni, String telefono,
                    Domicilio domicilio, Ambulancia ambulancia) {
        super(nombre, apellido, dni, domicilio, telefono);
        this.ambulancia = ambulancia;
    }



    /**
     * Método run que ejecuta la lógica del operario en un hilo separado.
     */
    @Override
    public void run() {
        while (this.ambulancia.isSimulacionActiva()) {
            TiempoMuerto.esperar();

            // Verificar de nuevo antes de solicitar mantenimiento
            if (!this.ambulancia.isSimulacionActiva()) {
                break;
            }

            ambulancia.solicitarMantenimiento(this);

            // Si la simulación terminó durante el mantenimiento, salir
            if (!this.ambulancia.isSimulacionActiva()) {
                break;
            }

            TiempoMuerto.esperar();
            ambulancia.volviendoDelTaller();

            // Verificar si todos los asociados terminaron
            this.ambulancia.verificarYFinalizarSimulacion();
        }

        // Notificar que el operario finalizó
        this.setChanged();
        this.notifyObservers(new NotificacionSimulacion(
                "👷 El operario " + this.getNombre() + " " + this.getApellido() +
                        " ha finalizado su trabajo.", "INFO"));
    }
}