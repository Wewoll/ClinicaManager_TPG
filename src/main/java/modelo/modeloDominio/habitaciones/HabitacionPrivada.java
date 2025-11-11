package modelo.modeloDominio.habitaciones;

import modelo.modeloDominio.personas.paciente.Paciente;
/**
 * Clase especifica HabitacionCompartida que extiende Habitacion
 * Contiene atributos estaticos MAXIMO_PACIENTES y COSTO
 */
public class HabitacionPrivada extends Habitacion{
    private static final int MAXIMO_PACIENTES = 1;
    private static final double COSTO = 4000;


    /**
     * Constructor de HabitacionPrivada
     * <b>post:</b> se crea una habitacion privada.
     */
    public HabitacionPrivada() {
        super(MAXIMO_PACIENTES);
    }
    /**
     * Metodo double que calcula el arancel de internacion
     * @param  p persona a la que se le calcula el arancel
     * <b>pre:</b> p no debe ser nulo.
     * <b>post:</b> retorna el valor del arancel de internacion.
     * @return arancel
     */
    @Override
    public double calcularArancelInternacion(Paciente p) {
        int cantdias = p.getDiasInternado();
        double arancel;
        if (cantdias == 1)
            arancel = COSTO;
        else if (cantdias >= 2 && cantdias <=5)
                arancel = COSTO * cantdias * 1.3;
            else if (cantdias >= 6)
                arancel = COSTO * cantdias * 2;
                else
                    arancel = 0;
        return arancel;
    }

    /**
     * Metodo toString sobreescrito para representar la habitacion privada
     * @return "Privada" como tipo de habitacion
     */
    @Override
    public String toString() {
        return "Privada";
    }
}
