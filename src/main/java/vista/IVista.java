package vista;


import controlador.Controlador;

public interface IVista {
    public static final String ACEPTAR = "ACEPTAR";
    public static final String DAR_ALTA = "DAR_ALTA";
    public static final String DAR_BAJA = "DAR_BAJA";
    void setActionListener(Controlador controlador);
}
