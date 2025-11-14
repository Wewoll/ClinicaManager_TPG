package modelo.modeloDominio.ambulancia;

import modelo.modeloAplicacion.NotificacionSimulacion;
import modelo.modeloDominio.personas.asociado.Asociado;
import modelo.modeloDominio.personas.operario.Operario;

import java.util.ArrayList;
import java.util.Observable;

public class Ambulancia extends Observable
{
    private State estadoActual;
    private boolean ocupado;
    private boolean isSimulacionActiva;
    private ArrayList<Asociado> asociados;

    public Ambulancia() {
        this.estadoActual = new DisponibleState(this);
        this.ocupado = false;
        this.isSimulacionActiva = true;
        this.asociados = new ArrayList<>();
    }

   public void agregarAsociado(Asociado asociado) {
        this.asociados.add(asociado);
    }

    public ArrayList<Asociado> getAsociados() {
        return this.asociados;
    }

    public void setState(State nuevoEstado) {
        this.estadoActual = nuevoEstado;
    }

    public State getEstadoActual() {
        return this.estadoActual;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public boolean isSimulacionActiva()
    {
        return isSimulacionActiva;
    }

    public void setSimulacionActiva(boolean simulacionActiva)
    {
        isSimulacionActiva = simulacionActiva;
    }

    public synchronized void solicitarMantenimiento(Operario o)  {
        while (this.ocupado) {
            try {
                // System.out.println("Ambulancia ocupada, esperando para solicitar mantenimiento...");
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
        // System.out.println(">> Solicitando mantenimiento");
        estadoActual.SolicitudMantenimiento();
        notifyAll();
    }
    public synchronized void volviendoDelTaller() {
        // System.out.println(">> Volviendo del taller");
        this.notifyObservers(new NotificacionSimulacion("ℹ️ La ambulancia vuelve del taller.","AMBULANCIA"));
        estadoActual.SolicitudMantenimiento();
        notifyAll();
    }
    public synchronized void atenderDomicilio(Asociado a)  {
        while (this.ocupado) {
            try {
                setChanged();
                this.notifyObservers(new NotificacionSimulacion("❌ 🏠 Ambulancia ocupada, el asociado "+ a.getNombre() + " espera para ser atendido a domicilio...","Asociado"));
                // System.out.println("Ambulancia ocupada, esperando para atender domicilio...");
                // System.out.println(this.getEstadoActual());
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
    public synchronized void trasladarALaClinica(Asociado a)
    {
        while (this.ocupado) {
            try {
                setChanged();
                this.notifyObservers(new NotificacionSimulacion("❌ 🚑 Ambulancia ocupada, el asociado "+ a.getNombre() + " espera para ser trasladado a la clinica...","Asociado"));
                // System.out.println(this.getEstadoActual());
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return; // Salir si el hilo es interrumpido
            }
        }
        setChanged();
        this.notifyObservers(new NotificacionSimulacion("🚑  El asociado " + a.getNombre() + " es trasladado a la clinica.","Asociado"));
        // System.out.println(">> Trasladando a la clinica");
        this.estadoActual.SolicitudDeTraslado();
    }

    public synchronized void retornoAutomatico(RetornoAutomatico retornoAutomatico)  {
        setChanged();
        // el notify observers esta dentro del estado, ya que puede ser que no se pueda hacer el retorno automatico
        estadoActual.RetornoClinica();
        notifyAll();
    }

    public synchronized void regresarSinPaciente()  {
        // System.out.println("<< Regresando sin paciente");
        setChanged();
        estadoActual.RetornoClinica();
        notifyAll();
    }
}
