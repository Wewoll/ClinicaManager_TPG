package modelo.modeloAplicacion;

public class NotificacionSimulacion
{
    private String mensaje;
    private String tipo;

    public NotificacionSimulacion(String mensaje, String tipo)
    {
        this.mensaje = mensaje;
        this.tipo = tipo;
    }
    public String getMensaje()
    {
        return mensaje;
    }
    public String getTipo()
    {
        return tipo;
    }
}
