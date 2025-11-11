package modelo.modeloDominio.habitaciones;

import modelo.modeloDominio.personas.paciente.Paciente;
import modelo.modeloDominio.util.Excepciones.HabitacionOcupadaException;

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
     * @throws HabitacionOcupadaException si la habitacion ya esta ocupada, es decir, si la cantidad actual de pacientes es igual al maximo
     * <b>post:</b> la habitacion queda ocupada si la cantidad actual igual al maximo de pacientes
     */
    @Override
    public void ocupar() throws HabitacionOcupadaException {
        int cantAct = this.getCantidadPacientes();
        if (cantAct < this.getMaximoPacientes()){
            cantAct++;
            this.ocupada = cantAct == this.getMaximoPacientes();
            this.setCantPacientes(cantAct);
        }else
            throw new HabitacionOcupadaException(this);
    }

        /**
         * Metodo void sobreescrito para desocupar la habitacion compartida
         * <b>post:</b> la habitacion queda desocupada si la cantidad actual de pacientes es distinta al maximo de pacientes.
         */
    @Override
    public void desocupar(){
        int  cantact = this.getCantidadPacientes();
        if (cantact == this.getMaximoPacientes())
            this.ocupada = false;
        cantact--;
        this.setCantPacientes(cantact);
    }
    /**
     * Metodo toString sobreescrito para representar la habitacion compartida
     * @return "Compartida" como tipo de habitacion
     */
    @Override
    public String toString() {
        return "Compartida";
    }
}
