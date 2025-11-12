package modelo.modeloDominio.ambulancia;

import modelo.modeloAplicacion.ObservableSimulacion;
import modelo.modeloDominio.personas.asociado.Asociado;
import modelo.modeloDominio.personas.operario.Operario;

import java.util.ArrayList;

public class Ambulancia extends ObservableSimulacion
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
                wait(); // Espera 1 segundo antes de verificar nuevamente
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return; // Salir si el hilo es interrumpido
            }
        }
        o.notifyObservers(o.getEstado());
        // System.out.println(">> Solicitando mantenimiento");
        estadoActual.SolicitudMantenimiento();
        notifyAll();
    }
    public synchronized void volviendoDelTaller() {
        // System.out.println(">> Volviendo del taller");
        estadoActual.SolicitudMantenimiento();
        notifyAll();
    }
    public synchronized void atenderDomicilio()  {
        while (this.ocupado) {
            try {
                // System.out.println("Ambulancia ocupada, esperando para atender domicilio...");
                // System.out.println(this.getEstadoActual());
                wait(); // Espera 1 segundo antes de verificar nuevamente
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return; // Salir si el hilo es interrumpido
            }
        }
        System.out.println(">> Atendiendo domicilio");
        estadoActual.SolicitudDeAtencionDomicilio();
    }
    public synchronized void trasladarALaClinica(Asociado a)
    {
        while (this.ocupado) {
            try {
                // System.out.println("Ambulancia ocupada, esperando para trasladar a la clinica...");
                // System.out.println(this.getEstadoActual());
                wait(); // Espera 1 segundo antes de verificar nuevamente
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return; // Salir si el hilo es interrumpido
            }
        }
        a.notifyObservers(a.getEstado());
        // System.out.println(">> Trasladando a la clinica");
        this.estadoActual.SolicitudDeTraslado();
    }

    public synchronized void retornoAutomatico(RetornoAutomatico retornoAutomatico)  {
        retornoAutomatico.notifyObservers(retornoAutomatico.getEstado());
        estadoActual.RetornoClinica();
        notifyAll();
    }

    public synchronized void regresarSinPaciente()  {
        // System.out.println("<< Regresando sin paciente");
        estadoActual.RetornoClinica();
        notifyAll();
    }
}
