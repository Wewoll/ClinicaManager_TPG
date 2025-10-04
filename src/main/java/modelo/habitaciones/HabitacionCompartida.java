package modelo.habitaciones;

import modelo.personas.paciente.Paciente;
    /**
    * Clase especifica HabitacionCompartida que extiende Habitacion
    */
public class HabitacionCompartida extends Habitacion{
    private static final int MAXIMO_PACIENTES = 2;
    private static final double COSTO = 2000;

    public HabitacionCompartida() {
        super(MAXIMO_PACIENTES);
    }

    /**
     * Metodo double sobreescrito para calcular el arancel de internacion del paciente
     * @param  p persona a la que se le calcula el arancel, no debe ser nulo
     * @return costo final del arancel
     */
    @Override
    public double calcularArancelInternacion(Paciente p) {
        int cantdias = p.getDiasInternado();
        return COSTO * cantdias;
    }

    /**
     * Metodo void sobreescrito para ocupar la habitacion compartida
     */
    @Override
    public void ocupar(){
        int cantact = this.getCantidadPacientes();
        if (cantact < this.getMaximoPacientes()){
            cantact++;
            this.ocupada = cantact == this.getMaximoPacientes();
            this.setCantPacientes(cantact);
        }
    }

        /**
         * Metodo void sobreescrito para desocupar la habitacion compartida
         * pre: cantidad actual de pacientes en la habitacion mayor o igual a 0
         */
    @Override
    public void desocupar(){
        int  cantact = this.getCantidadPacientes();
        if (cantact == this.getMaximoPacientes())
            this.ocupada = false;
        cantact--;
        this.setCantPacientes(cantact);
    }

    @Override
    public String toString() {
        return "Compartida";
    }
}
