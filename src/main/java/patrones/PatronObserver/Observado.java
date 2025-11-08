package patrones.PatronObserver;


public abstract class Observado
{
    private IObservador observador;

    public Observado()
    {
        this.observador = new ObservadorAsociado(this);
    }

    public void agregarObservador(IObservador o){
        this.observador = o;
    }

    void notificarObservadores(String mensaje){
       this.observador.actualizar(mensaje);
    }
}
