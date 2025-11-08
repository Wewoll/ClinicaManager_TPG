package modelo.ambulancia;

import modelo.personas.asociado.Asociado;

import java.util.ArrayList;

public class Ambulancia {
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

    public synchronized void solicitarMantenimiento()  {
        while (this.ocupado) {
            try {
                System.out.println("Ambulancia ocupada, esperando para solicitar mantenimiento...");
                wait(); // Espera 1 segundo antes de verificar nuevamente
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return; // Salir si el hilo es interrumpido
            }
        }
        System.out.println(">> Solicitando mantenimiento");
        estadoActual.SolicitudMantenimiento();
        notifyAll();
    }
    public synchronized void volviendoDelTaller() {
        System.out.println(">> Volviendo del taller");
        estadoActual.SolicitudMantenimiento();
        notifyAll();
    }
    public synchronized void atenderDomicilio()  {
        while (this.ocupado) {
            try {
                System.out.println("Ambulancia ocupada, esperando para atender domicilio...");
                System.out.println(this.getEstadoActual());
                wait(); // Espera 1 segundo antes de verificar nuevamente
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return; // Salir si el hilo es interrumpido
            }
        }
        System.out.println(">> Atendiendo domicilio");
        estadoActual.SolicitudDeAtencionDomicilio();
    }
    public synchronized void trasladarALaClinica()
    {
        while (this.ocupado) {
            try {
                System.out.println("Ambulancia ocupada, esperando para trasladar a la clinica...");
                System.out.println(this.getEstadoActual());
                wait(); // Espera 1 segundo antes de verificar nuevamente
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return; // Salir si el hilo es interrumpido
            }
        }
        System.out.println(">> Trasladando a la clinica");
        this.estadoActual.SolicitudDeTraslado();
    }

    public synchronized void retornoAutomatico()  {
        System.out.println(">> Retorno automatico");
        estadoActual.RetornoClinica();
        notifyAll();
    }

    public synchronized void regresarSinPaciente()  {
        System.out.println("<< Regresando sin paciente");
        estadoActual.RetornoClinica();
        notifyAll();
    }
}
