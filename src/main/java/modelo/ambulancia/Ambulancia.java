package modelo.ambulancia;

public class Ambulancia {
    private State estadoActual;
    private boolean ocupado;

    public Ambulancia() {
        this.estadoActual = new DisponibleState(this);
        this.ocupado = false;
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

    public synchronized void solicitarMantenimiento()  {
        while (ocupado) {
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
    public synchronized void atenderDomicilio()  {
        while (ocupado) {
            try {
                System.out.println("Ambulancia ocupada, esperando para atender domicilio...");
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
        while (ocupado) {
            try {
                System.out.println("Ambulancia ocupada, esperando para trasladar a la clinica...");
                wait(); // Espera 1 segundo antes de verificar nuevamente
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return; // Salir si el hilo es interrumpido
            }
        }
        System.out.println(">> Trasladando a la clinica");
        estadoActual.SolicitudDeTraslado();
    }
    public synchronized void regresarSinPaciente()  {
        while (ocupado) {
            try {
                System.out.println("Ambulancia ocupada, esperando para regresar sin paciente...");
                wait(); // Espera 1 segundo antes de verificar nuevamente
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return; // Salir si el hilo es interrumpido
            }
        }
        System.out.println(">> Regresando sin paciente");
        estadoActual.RetornoClinica();
        notifyAll();
    }
}
