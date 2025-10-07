package modelo.personas.medico.posgrado;

import modelo.personas.medico.Medico;

/**
 * Clase concreta que representa un posgrado de doctorado para un médico. Extiende de DecoratorPosgrado.
 * Contiene un incremento fijo al sueldo base del médico.
 */
public class DecoratorPosgradoDoctorado extends DecoratorPosgrado {
    private static final double INCREMENTO = 1.1;

    /**
     * Constructor de la clase DecoratorPosgradoDoctorado.
     * <b>pre:</b> medico no debe ser nulo.
     * <b>post:</b> se crea un DecoratorPosgradoDoctorado.
     * @param medico El médico al que se le aplicará el posgrado de doctorado.
     */
    public DecoratorPosgradoDoctorado(Medico medico) {
        super(medico);
    }

    /**
     * Metodo que retorna el sueldo del médico con el incremento por posgrado de doctorado.
     * @return El sueldo del médico con el incremento aplicado.
     */
    @Override
    public double getSueldo(){
        return this.encapsulado.getSueldo() *  INCREMENTO;
    }
}
