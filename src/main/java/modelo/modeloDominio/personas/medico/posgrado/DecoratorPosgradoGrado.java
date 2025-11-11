package modelo.modeloDominio.personas.medico.posgrado;

import modelo.modeloDominio.personas.medico.Medico;

/**
 * Clase concreta que representa un posgrado de grado para un médico. Extiende de DecoratorPosgrado.
 * Contiene un incremento fijo al sueldo base del médico.
 */
public class DecoratorPosgradoGrado extends DecoratorPosgrado {
    private static final double INCREMENTO = 1;

    /**
     * Constructor de la clase DecoratorPosgradoGrado.
     * <b>pre:</b> medico no debe ser nulo.
     * <b>post:</b> se crea un DecoratorPosgradoGrado.
     * @param medico El médico al que se le aplicará el posgrado de grado.
     */
    public DecoratorPosgradoGrado(Medico medico){
        super(medico);
    }

    /**
     * Metodo que retorna el sueldo del médico con el incremento por posgrado de grado.
     * @return El sueldo del médico con el incremento aplicado.
     */
    @Override
    public double getSueldo(){
        return this.encapsulado.getSueldo() *  INCREMENTO;
    }
}
