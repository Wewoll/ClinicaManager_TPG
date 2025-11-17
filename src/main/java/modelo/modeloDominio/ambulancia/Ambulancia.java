package modelo.modeloDominio.ambulancia;

import modelo.modeloAplicacion.NotificacionSimulacion;
import modelo.modeloDominio.personas.asociado.Asociado;
import modelo.modeloDominio.personas.operario.Operario;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Clase Ambulancia que representa una ambulancia en el sistema.
 * Utiliza el patrón State para manejar sus diferentes estados.
 * Extiende de Observable para notificar a los observadores sobre cambios en su estado.
 */
public class Ambulancia extends Observable {
    private State estadoActual;
    private boolean ocupado;
    private boolean isSimulacionActiva;
    private ArrayList<Asociado> asociados;

    /**
     * Constructor de la clase Ambulancia.
     */
    public Ambulancia() {
        this.estadoActual = new DisponibleState(this);
        this.ocupado = false;
        this.isSimulacionActiva = true;
        this.asociados = new ArrayList<>();
    }

    /**
     * Agrega un asociado a la lista de asociados de la ambulancia.
     */
    public void agregarAsociado(Asociado asociado) {
        assert asociado != null;
        this.asociados.add(asociado);
    }

    /**
     * Obtiene la lista de asociados de la ambulancia.
     */
    public ArrayList<Asociado> getAsociados() {
        return this.asociados;
    }

    /**
     * Establece el estado actual de la ambulancia.
     */
    public synchronized void setState(State nuevoEstado) {
        assert nuevoEstado != null;
        this.estadoActual = nuevoEstado;
    }

    /**
     * Obtiene el estado actual de la ambulancia.
     */
    public State getEstadoActual() {
        return this.estadoActual;
    }

    /**
     * Verifica si la ambulancia está ocupada.
     */
    public synchronized boolean isOcupado() {
        return ocupado;
    }

    /**
     * Establece si la ambulancia está ocupada.
     */
    public synchronized void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    /**
     * Verifica si la simulación está activa.
     */
    public synchronized boolean isSimulacionActiva() {
        return isSimulacionActiva;
    }

    /**
     * Establece si la simulación está activa y despierta a todos los hilos esperando.
     */
    public synchronized void setSimulacionActiva(boolean simulacionActiva) {
        this.isSimulacionActiva = simulacionActiva;
        if (!simulacionActiva) {
            // Despertar a todos los hilos que están esperando
            notifyAll();
        }
    }

    public synchronized void verificarYFinalizarSimulacion() {
        if (!isSimulacionActiva) {
            return; // Ya está finalizada
        }

        // Verificar si todos los asociados terminaron
        for (Asociado asociado : asociados) {
            if (asociado.getCantSolicitudesAtendidas() < asociado.getMaxCantSolicitudes()) {
                return; // Todavía hay asociados activos
            }
        }

        // Todos los asociados terminaron sus solicitudes
        this.isSimulacionActiva = false;

        setChanged();
        notifyObservers(new NotificacionSimulacion(
                "✅ Todos los asociados completaron sus solicitudes. La simulación finalizará.", "INFO"));

        notifyAll(); // Despertar a todos los hilos esperando
    }

    /**
     * Solicita mantenimiento para la ambulancia.
     */
    public synchronized void solicitarMantenimiento(Operario o) {
        assert o != null;

        while (this.isOcupado() && this.isSimulacionActiva()) {
            try {
                setChanged();
                notifyObservers(new NotificacionSimulacion(
                        "❌ 🔧 Ambulancia ocupada, el operario " + o.getNombre() +
                                " espera para solicitar mantenimiento...", "NO_AMBULANCIA"));
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                notifyAll();
                return;
            }
        }

        // Si la simulación terminó mientras esperaba, salir
        if (!this.isSimulacionActiva()) {
            return;
        }
        this.setOcupado(true);
        // Cambiar estado primero
        this.setChanged();
        estadoActual.SolicitudMantenimiento();

        // Notificar dentro del synchronized
        setChanged();
        notifyObservers(new NotificacionSimulacion(
                "🔧 El operario " + o.getNombre() +
                        " solicitó el mantenimiento de la ambulancia.", "OK"));
    }

    /**
     * Indica que la ambulancia está volviendo del taller.
     */
    public synchronized void volviendoDelTaller() {
        // Cambiar estado
        estadoActual.SolicitudMantenimiento();
        this.setOcupado(false);

        // Notificar dentro del synchronized
        setChanged();
        notifyObservers(new NotificacionSimulacion(
                "🔧 La ambulancia vuelve del taller.", "INFO"));

        // Despertar hilos esperando
        notifyAll();
    }

    /**
     * Atiende un domicilio para un asociado.
     */
    public synchronized void atenderDomicilio(Asociado a) {
        assert a != null;

        while (this.isOcupado() && this.isSimulacionActiva()) {
            try {
                setChanged();
                notifyObservers(new NotificacionSimulacion(
                        "❌ 🏠 Ambulancia ocupada, el asociado " + a.getNombre() +
                                " espera para ser atendido a domicilio...", "NO_AMBULANCIA"));
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                notifyAll();
                return;
            }
        }

        // Si la simulación terminó mientras esperaba, salir
        if (!this.isSimulacionActiva()) {
            return;
        }
        this.setOcupado(true);
        // Cambiar estado primero
        this.setChanged();
        estadoActual.SolicitudDeAtencionDomicilio();

        // Notificar dentro del synchronized
        setChanged();
        notifyObservers(new NotificacionSimulacion(
                "🏠 El asociado " + a.getNombre() + " es atendido a domicilio.", "OK"));
    }

    /**
     * Traslada a un asociado a la clínica.
     */
    public synchronized void trasladarALaClinica(Asociado a) {
        assert a != null;

        while (this.isOcupado() && this.isSimulacionActiva()) {
            try {
                setChanged();
                notifyObservers(new NotificacionSimulacion(
                        "❌ 🚑 Ambulancia ocupada, el asociado " + a.getNombre() +
                                " espera para ser trasladado a la clínica...", "NO_AMBULANCIA"));
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                notifyAll();
                return;
            }
        }

        // Si la simulación terminó mientras esperaba, salir
        if (!this.isSimulacionActiva()) {
            return;
        }
        this.setOcupado(true);
        this.setChanged();
        // Cambiar estado primero
        this.estadoActual.SolicitudDeTraslado();

        // Notificar dentro del synchronized
        setChanged();
        notifyObservers(new NotificacionSimulacion(
                "🚑 El asociado " + a.getNombre() + " es trasladado a la clínica.", "OK"));
    }

    /**
     * Realiza un retorno automático de la ambulancia.
     */
    public synchronized void retornoAutomatico(RetornoAutomatico retornoAutomatico) {
        estadoActual.RetornoClinica();
        notifyAll();
    }

    /**
     * Realiza un retorno de la ambulancia sin paciente.
     */
    public synchronized void regresarSinPaciente() {
        // Cambiar estado y liberar ambulancia
        estadoActual.RetornoClinica();
        this.setOcupado(false);

        // Notificar dentro del synchronized
        setChanged();
        notifyObservers(new NotificacionSimulacion(
                "🏥 La ambulancia regresó a la clínica.", "INFO"));

        // Despertar hilos esperando
        notifyAll();
    }
}