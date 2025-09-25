package modelo.personas.medico.contratacion;

import modelo.personas.medico.Medico;

public class DecoratorContratacionPermanente extends DecoratorContratacion {

    public DecoratorContratacionPermanente(Medico medico) {
        super(medico);
    }

//    @Override
//    public double calcularSueldo() {
//        return Medico.getSueldoBase() * 1.5; // Sueldo base aumentado en un 50% para medicos permanentes
//    }
//
//    @Override
//    public String getTipoContratacion() {
//        return "Permanente";
//    }
}
