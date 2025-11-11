package modelo.util;

/**
 * Clase que representa un domicilio con calle, número y ciudad.
 */
public class Domicilio {
    private String calle;
    private int numero;
    private  String ciudad;

    /**
     * Constructor de la clase Domicilio.
     * <b>pre:</b> calle no es nulo, numero es mayor que 0, ciudad no es nulo.
     * <b>post:</b> se crea un objeto Domicilio con los valores proporcionados.
     * @param calle La calle del domicilio.
     * @param numero El número del domicilio.
     * @param ciudad La ciudad del domicilio.
     */
    public Domicilio(String calle, int numero, String ciudad) {
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
    }

    /**
     * Obtiene la calle del domicilio.
     * @return La calle del domicilio.
     */
    public String getCalle() {
        return calle;
    }
    /**
     * Establece la calle del domicilio. Para casos de cambio de domicilio.
     * <b>pre:</b> calle no es nulo.
     * <b>post:</b> se actualiza la calle del domicilio.
     * @param calle La nueva calle del domicilio.
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }
    /**
     * Obtiene el número del domicilio.
     * @return El número del domicilio.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Establece el número del domicilio. Para casos de cambio de domicilio.
     * <b>pre:</b> numero es mayor que 0.
     * <b>post:</b> se actualiza el número del domicilio.
     * @param numero El nuevo número del domicilio.
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Obtiene la ciudad del domicilio.
     * @return La ciudad del domicilio.
     */
    public String getCiudad() {
        return ciudad;
    }
    /**
     * Establece la ciudad del domicilio. Para casos de cambio de domicilio.
     * <b>pre:</b> ciudad no es nulo.
     * <b>post:</b> se actualiza la ciudad del domicilio.
     * @param ciudad La nueva ciudad del domicilio.
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Devuelve una representación en cadena del domicilio.
     * @return Una cadena que representa el domicilio.
     */
    @Override
    public String toString() {
        return calle + numero +',' + ciudad;
    }
}
