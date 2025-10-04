package modelo.habitaciones;

import modelo.personas.paciente.Paciente;
/**
 * Clase especifica HabitacionCompartida que extiende Habitacion
 */
public class HabitacionPrivada extends Habitacion{
    private static final int MAXIMO_PACIENTES = 1;
    private static final double COSTO = 4000;

    public HabitacionPrivada() {
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
        double arancel=0;
        if (cantdias == 1)
            arancel = COSTO;
        else if (cantdias >= 2 && cantdias <=5)
                arancel = COSTO * cantdias * 1.3;
            else if (cantdias >= 6)
                arancel = COSTO * cantdias * 2;
        return arancel;
    }

    @Override
    public String toString() {
        return "Privada";
    }
}
