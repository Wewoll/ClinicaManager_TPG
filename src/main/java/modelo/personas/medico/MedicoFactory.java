package modelo.personas.medico;
import util.Domicilio;
import modelo.personas.medico.especialidades.*;
import modelo.personas.medico.contratacion.*;
import modelo.personas.medico.posgrado.*;

public class MedicoFactory{

    public DecoratorContratacion crearMedico(String dni, String nroMatricula, String nombre, String apellido, Domicilio domicilio, String telefono, String especialidad, String contratacion, String titulo) throws EspecialidadNoExistenteException, ContratacionNoExistenteException, TituloNoExistenteException {
        Medico medico = null;
        if(especialidad.equalsIgnoreCase("clinica")){
            medico = new MedicoClinico(nombre, apellido, dni, domicilio, telefono, nroMatricula);
        } else if(especialidad.equalsIgnoreCase("cirugia")) {
            medico = new MedicoCirugia(nombre, apellido, dni, domicilio, telefono, nroMatricula);
        } else
            if(especialidad.equalsIgnoreCase("pediatria")){
                medico = new MedicoPediatra(nombre, apellido, dni, domicilio, telefono, nroMatricula);
            }else
                throw new EspecialidadNoExistenteException("La especialidad ingresada no existe");



        DecoratorPosgrado decoratorPosgrado = null;
        if(titulo.equalsIgnoreCase("magister")){
            decoratorPosgrado = new DecoratorPosgradoMagister(medico);
        }
        else
            if(titulo.equalsIgnoreCase("doctor")){
                decoratorPosgrado = new DecoratorPosgradoDoctorado(medico);
            }
            else
                throw new TituloNoExistenteException("El tipo de postgrado ingresado no existe");

        DecoratorContratacion decorator = null;
        if(contratacion.equalsIgnoreCase("permanente")){
            decorator = new DecoratorContratacionPermanente(decoratorPosgrado);
        }else
            if(contratacion.equalsIgnoreCase("residente")){
                decorator = new DecoratorContratacionResidente(decoratorPosgrado);
            }
            else
                throw new ContratacionNoExistenteException("El tipo de contratacion ingresada no existe");

        return decorator;
    }
}
