package modelo.ambulancia;

public interface State {
    void SolicitudDeTraslado(Ambulancia ambulancia);
    void SolicitudDeArencionDomicilio(Ambulancia ambulancia);
    void SolicitudMantenimiento(Ambulancia ambulancia);
    void RetornoDeTaller(Ambulancia ambulancia);
}
