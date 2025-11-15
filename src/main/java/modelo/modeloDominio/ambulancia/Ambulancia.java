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
 * Contiene atributos para el estado actual, si está ocupada, si la simulación está activa y una lista de asociados.
 */
public class Ambulancia extends Observable
{
    private State estadoActual;
    private boolean ocupado;
    private boolean isSimulacionActiva;
    private ArrayList<Asociado> asociados;

    /**
     * Constructor de la clase Ambulancia.
     * <b>post:</b> se crea una instancia de Ambulancia con el estado inicial Disponible, no ocupada, simulación activa y una lista vacía de asociados.
     */
    public Ambulancia() {
        this.estadoActual = new DisponibleState(this);
        this.ocupado = false;
        this.isSimulacionActiva = true;
        this.asociados = new ArrayList<>();
    }
    /**
     * Agrega un asociado a la lista de asociados de la ambulancia.
     * <b>pre:</b> el asociado proporcionado no debe ser nulo.
     * <b>post:</b> el asociado proporcionado se agrega a la lista de asociados.
     * @param asociado El objeto Asociado a agregar.
     */
   public void agregarAsociado(Asociado asociado) {
       assert asociado != null;
        this.asociados.add(asociado);
    }

    /**
     * Obtiene la lista de asociados de la ambulancia.
     * @return La lista de objetos Asociado.
     */
    public ArrayList<Asociado> getAsociados() {
        return this.asociados;
    }

    /**
     * Establece el estado actual de la ambulancia.
     * <b>pre:</b> el nuevo estado proporcionado no debe ser nulo.
     * <b>post:</b> el estado actual se actualiza al nuevo estado proporcionado.
     * @param nuevoEstado El nuevo estado a establecer.
     */
    public void setState(State nuevoEstado) {
        assert nuevoEstado != null;
        this.estadoActual = nuevoEstado;
    }

    /**
     * Obtiene el estado actual de la ambulancia.
     * @return El estado actual de la ambulancia.
     */
    public State getEstadoActual() {
        return this.estadoActual;
    }

    /**
     * Verifica si la ambulancia está ocupada.
     * @return true si la ambulancia está ocupada, false en caso contrario.
     */
    public boolean isOcupado() {
        return ocupado;
    }

    /**
     * Establece si la ambulancia está ocupada.
     * <b>post:</b> el estado de ocupado se actualiza al valor proporcionado.
     * @param ocupado true si la ambulancia está ocupada, false en caso contrario.
     */
    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    /**
     * Verifica si la simulación está activa.
     * @return true si la simulación está activa, false en caso contrario.
     */
    public boolean isSimulacionActiva()
    {
        return isSimulacionActiva;
    }
    /**
     * Establece si la simulación está activa.
     * <b>post:</b> el estado de la simulación se actualiza al valor proporcionado.
     * @param simulacionActiva true si la simulación está activa, false en caso contrario.
     */
    public void setSimulacionActiva(boolean simulacionActiva)
    {
        isSimulacionActiva = simulacionActiva;
    }

    /**
     * Solicita mantenimiento para la ambulancia, función synchronized.
     * Si la ambulancia está ocupada, el operario esperará hasta que esté disponible.
     * <b>pre:</b> el operario proporcionado no debe ser nulo.
     * <b>post:</b> se solicita mantenimiento para la ambulancia.
     * @param o El objeto Operario que solicita el mantenimiento.
     */
    public synchronized void solicitarMantenimiento(Operario o)  {
        assert  o != null;
        while (this.ocupado) {
            try {
                setChanged();
                this.notifyObservers(new NotificacionSimulacion("❌ 🔧 Ambulancia ocupada, el operario "+ o.getNombre() + " espera para solicitar mantenimiento...","Operario"));
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return; // Salir si el hilo es interrumpido
            }
        }
        setChanged();
        this.notifyObservers(new NotificacionSimulacion("🔧 El operario " + o.getNombre() + " solicitó el mantenimiento de la ambulancia.","Operario"));
        estadoActual.SolicitudMantenimiento();
        notifyAll();
    }
    /**
     * Indica que la ambulancia está volviendo del taller, función synchronized.
     * <b>post:</b> se notifica a los observadores que la ambulancia está volviendo del taller.
     */
    public synchronized void volviendoDelTaller() {
        this.notifyObservers(new NotificacionSimulacion("ℹ️ La ambulancia vuelve del taller.","AMBULANCIA"));
        estadoActual.SolicitudMantenimiento();
        notifyAll();
    }

    /**
     * Atiende un domicilio para un asociado, función synchronized.
     * Si la ambulancia está ocupada, el asociado esperará hasta que esté disponible.
     * <b>pre:</b> el asociado proporcionado no debe ser nulo.
     * <b>post:</b> se atiende el domicilio del asociado.
     * @param a El objeto Asociado que solicita atención a domicilio.
     */
    public synchronized void atenderDomicilio(Asociado a)  {
        assert   a != null;
        while (this.ocupado) {
            try {
                setChanged();
                this.notifyObservers(new NotificacionSimulacion("❌ 🏠 Ambulancia ocupada, el asociado "+ a.getNombre() + " espera para ser atendido a domicilio...","Asociado"));
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return; // Salir si el hilo es interrumpido
            }
        }
        setChanged();
        this.notifyObservers(new NotificacionSimulacion("🏠 El asociado " + a.getNombre() + " es atendido domicilio.","Asociado"));
        estadoActual.SolicitudDeAtencionDomicilio();
    }

    /**
     * Traslada a un asociado a la clínica, función synchronized.
     * Si la ambulancia está ocupada, el asociado esperará hasta que esté disponible.
     * <b>pre:</b> el asociado proporcionado no debe ser nulo.
     * <b>post:</b> se traslada al asociado a la clínica.
     * @param a El objeto Asociado que solicita traslado a la clínica.
     */
    public synchronized void trasladarALaClinica(Asociado a)
    {
        assert   a != null;
        while (this.ocupado) {
            try {
                setChanged();
                this.notifyObservers(new NotificacionSimulacion("❌ 🚑 Ambulancia ocupada, el asociado "+ a.getNombre() + " espera para ser trasladado a la clinica...","Asociado"));
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return; // Salir si el hilo es interrumpido
            }
        }
        setChanged();
        this.notifyObservers(new NotificacionSimulacion("🚑  El asociado " + a.getNombre() + " es trasladado a la clinica.","Asociado"));
        this.estadoActual.SolicitudDeTraslado();
    }

    /**
     * Realiza un retorno automático de la ambulancia, función synchronized.
     * <b>post:</b> se notifica a los observadores que la ambulancia está realizando un retorno automático.
     * @param retornoAutomatico El objeto RetornoAutomatico que indica el retorno.
     */
    public synchronized void retornoAutomatico(RetornoAutomatico retornoAutomatico)  {
        setChanged();
        // el notify observers esta dentro del estado, ya que puede ser que no se pueda hacer el retorno automatico
        estadoActual.RetornoClinica();
        notifyAll();
    }

    /**
     * Realiza un retorno de la ambulancia sin paciente, función synchronized.
     * <b>post:</b> se notifica a los observadores que la ambulancia está regresando sin paciente.
     */
    public synchronized void regresarSinPaciente()  {
        setChanged();
        estadoActual.RetornoClinica();
        notifyAll();
    }
}
