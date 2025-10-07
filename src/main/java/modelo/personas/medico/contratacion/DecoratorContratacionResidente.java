package modelo.personas.medico.contratacion;

import modelo.personas.medico.posgrado.DecoratorPosgrado;

/**
 * Clase concreta que representa un decorador para la contratación de médicos residentes.
 * Contiene un incremento fijo al sueldo base del médico.
 */
public class DecoratorContratacionResidente extends DecoratorContratacion {
    private static final double INCREMENTO = 1.05;

    /**
     * Constructor de la clase DecoratorContratacionResidente.
     * <b>pre:</b> medico no debe ser nulo.
     * <b>post:</b> se crea un DecoratorContratacionResidente.
     * @param medico El médico al que se le aplicará la contratación de residente.
     */
    public DecoratorContratacionResidente(DecoratorPosgrado medico) {
        super(medico);
    }

    /**
     * Metodo que retorna el sueldo del médico con el incremento por contratación de residente.
     * @return El sueldo del médico con el incremento aplicado.
     */
    @Override
    public double getSueldo() {
        return this.encapsulado.getSueldo() * INCREMENTO;
    }
}
