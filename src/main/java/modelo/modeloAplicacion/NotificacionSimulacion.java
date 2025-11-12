package modelo.modeloAplicacion;

public class EstadoSimulacion
{
    private String mensaje;
    private String tipo;

    public EstadoSimulacion(String mensaje, String tipo)
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
