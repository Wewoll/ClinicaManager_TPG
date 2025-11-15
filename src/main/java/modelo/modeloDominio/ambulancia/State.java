package modelo.modeloDominio.ambulancia;
/**
 * Interfaz State que define los métodos que deben implementar los diferentes estados de una ambulancia.
 * Contiene métodos para manejar solicitudes de traslado, atención a domicilio, mantenimiento y retorno a la clínica.
 */
public interface State {
    void SolicitudDeTraslado();
    void SolicitudDeAtencionDomicilio();
    void SolicitudMantenimiento();
    void RetornoClinica();
}
