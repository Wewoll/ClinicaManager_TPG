package modelo.modeloDominio.habitaciones;

import modelo.modeloDominio.personas.paciente.Paciente;
import java.lang.Math;

/**
 *  Clase HabitacionTerapiaIntensiva que extiende Habitacion
 *  Contiene atributos estaticos MAXIMO_PACIENTES y COSTO
 */
public class HabitacionTerapiaIntensiva extends Habitacion{
    private static final int MAXIMO_PACIENTES = 1;
    private static final double COSTO = 5000;

    /**
     * Constructor de HabitacionTerapiaIntensiva.
     * <b>post:</b> se crea una habitacion de terapia intensiva.
     */
    public HabitacionTerapiaIntensiva() {
        super(MAXIMO_PACIENTES);
    }


    /**
     * Metodo double que calcula el arancel de internacion.
     * @param  p persona a la que se le calcula el arancel.
     * <b>pre:</b> p no debe ser nulo.
     * <b>post:</b> retorna el valor del arancel de internacion.
     * @return arancel
     */
    @Override
    public double calcularArancelInternacion(Paciente p) {
        int cantdias = p.getDiasInternado();;
        return Math.pow(COSTO,cantdias);
    }

    /**
     * Metodo toString sobreescrito para representar la habitacion de terapia intensiva.
     * @return "Terapia Intensiva" como tipo de habitacion.
     */
    @Override
    public String toString() {
        return "Terapia Intensiva";
    }
}
