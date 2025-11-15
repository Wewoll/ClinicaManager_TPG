package modelo.modeloAplicacion;

/**
 * Clase NotificacionSimulacion que representa una notificación en la simulación.
 * Contiene un mensaje y un tipo de notificación.
 */
public class NotificacionSimulacion
{
    private String mensaje;
    private String tipo;

    /**
     * Constructor de la clase NotificacionSimulacion.
     * <b>post:</b> se crea una instancia de NotificacionSimulacion con el mensaje y tipo proporcionados.
     * @param mensaje El mensaje de la notificación.
     * @param tipo El tipo de la notificación.
     */
    public NotificacionSimulacion(String mensaje, String tipo)
    {
        this.mensaje = mensaje;
        this.tipo = tipo;
    }

    /**
     * Método get
     * @return mensaje
     */
    public String getMensaje()
    {
        return mensaje;
    }
    /**
     * Método get
     * @return tipo
     */
    public String getTipo()
    {
        return tipo;
    }

    /**
     * Método toString
     * @return representación en String de la notificación
     */
    @Override
    public String toString()
    {
        return "NotificacionSimulacion{" +
                "mensaje='" + mensaje + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
