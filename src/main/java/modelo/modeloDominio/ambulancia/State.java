package modelo.modeloDominio.ambulancia;

public interface State {
    void SolicitudDeTraslado();
    void SolicitudDeAtencionDomicilio();
    void SolicitudMantenimiento();
    void RetornoClinica();
}
