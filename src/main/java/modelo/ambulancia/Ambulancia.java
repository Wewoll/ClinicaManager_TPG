package modelo.ambulancia;

public class Ambulancia {
    private State estadoActual;
    private boolean ocupado;

    public Ambulancia() {
        this.estadoActual = new DisponibleState();
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
}
