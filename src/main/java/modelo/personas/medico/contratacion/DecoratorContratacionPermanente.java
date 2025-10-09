package modelo.personas.medico.contratacion;

import modelo.personas.medico.posgrado.DecoratorPosgrado;

/**
 * Clase concreta que extiende de DecoratorContratacion y representa la contratacion permanente de un medico.
 * Contiene el INCREMENTO fijo que representa el incremento salarial para medicos con contratacion permanente.
 */
public class DecoratorContratacionPermanente extends DecoratorContratacion
{
    private final static double INCREMENTO = 1.1;

    /**
     * Constructor de la clase DecoratorContratacionPermanente.
     * <b>pre:</b> medico no debe ser nulo.
     * <b>post:</b> se crea un DecoratorContratacionPerman
     *
     * @param medico El medico al que se le aplicara la contratacion permanente.
     */
    public DecoratorContratacionPermanente(DecoratorPosgrado medico)
    {
        super(medico);
    }


    /**
     * Metodo que retorna el sueldo del medico con el incremento por contratacion permanente.
     *
     * @return El sueldo del medico con el incremento aplicado.
     */
    @Override
    public double getSueldo()
    {
        return this.encapsulado.getSueldo() * INCREMENTO;
    }


}
