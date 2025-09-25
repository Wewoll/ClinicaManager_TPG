package modelo.personas.medico.contratacion;

import modelo.personas.medico.Medico;

public class DecoratorContratacionResidente extends DecoratorContratacion {

    public DecoratorContratacionResidente(Medico medico) {
        super(medico);
    }

//    @Override
//    public double calcularSueldo() {
//        return Medico.getSueldoBase() * 0.8; // Sueldo base reducido en un 20% para medicos residentes
//    }
//
//    @Override
//    public String getTipoContratacion() {
//        return "Residente";
//    }
}
