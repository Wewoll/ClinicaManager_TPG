package modelo.habitaciones;

import modelo.personas.paciente.Paciente;
import java.lang.Math;

/**
 *  Clase HabitacionTerapiaIntensiva que extiende Habitacion
 */
public class HabitacionTerapiaIntensiva extends Habitacion{
    private static final int MAXIMO_PACIENTES = 1;
    private static final double COSTO = 5000;

    public HabitacionTerapiaIntensiva() {
        super(MAXIMO_PACIENTES);
    }


    /**
     * Metodo double que calcula el arancel de internacion
     * @param  p persona a la que se le calcula el arancel
     * pre: p no debe ser nulo
     * @return arancel
     * pos: retorna el valor del arancel de internacion
     */
    @Override
    public double calcularArancelInternacion(Paciente p) {
        int cantdias = p.getDiasInternado();
        double arancel = Math.pow(COSTO,cantdias);
        return arancel;
    }

    @Override
    public String toString() {
        return "Terapia Intensiva";
    }
}
