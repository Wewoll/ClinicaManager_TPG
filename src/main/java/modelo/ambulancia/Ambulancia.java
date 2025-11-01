package modelo.ambulancia;

public class Ambulancia {
    private State estadoActual;


    public Ambulancia() {
        this.estadoActual = new DisponibleState();
    }

    public void cambiarEstado(State nuevoEstado) {
        this.estadoActual = nuevoEstado;
    }

    public State getEstadoActual() {
        return this.estadoActual;
    }


}
