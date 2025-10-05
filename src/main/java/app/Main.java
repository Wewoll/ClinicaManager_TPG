package app;

import modelo.clinica.Clinica;
import modelo.clinica.modulos.egreso.facturacion.Factura;
import modelo.personas.medico.IMedico;
import modelo.personas.medico.Medico;
import modelo.personas.medico.MedicoFactory;
import modelo.personas.paciente.Paciente;
import modelo.personas.paciente.PacienteFactory;
import util.Domicilio;

public class Main
{
    public static void main(String[] args) {
        Clinica clinica = Clinica.getInstancia();

        MedicoFactory medicoFactory = new MedicoFactory();
        PacienteFactory pacienteFactory = new PacienteFactory();
        IMedico medico1 = null;
        IMedico medico2 = null;
        try{
            medico1 = medicoFactory.crearMedico("Juan", "2012", "Juan", "Perez", new Domicilio("Calle falsa",1234,"Mar del Plata") , "223456789", "Cirugia","Permanente","Magister");
            medico2 = medicoFactory.crearMedico("Ana", "2013", "Ana", "Gomez", new Domicilio("Calle verdadera",5678,"Mar del Plata") , "223456780", "Pediatria","Temporario","Especialista");
            clinica.registrarMedico(medico1);
            clinica.registrarMedico(medico2);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        Paciente paciente1 = null;
        Paciente paciente2 = null;
        Paciente paciente3 = null;
        try{
             paciente1 = pacienteFactory.crearPaciente("46564934", "Esteban", "Lopez", new Domicilio("Calle 123", 456, "Mar del Plata"), "2233344556", "001", 10);
             paciente2 = pacienteFactory.crearPaciente("87654321", "Lucia", "Martinez", new Domicilio("Calle 456", 789, "Mar del Plata"), "2236655443", "002", 30);
             paciente3 = pacienteFactory.crearPaciente("12345678", "Carlos", "Gonzalez", new Domicilio("Calle 789", 101, "Mar del Plata"), "2237788990", "003", 65);
            clinica.registrarPaciente(paciente1);
            clinica.registrarPaciente(paciente2);
            clinica.registrarPaciente(paciente3);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try{
            clinica.ingresarPaciente(paciente1);
            clinica.ingresarPaciente(paciente2);
            clinica.ingresarPaciente(paciente3);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        clinica.atenderPaciente(medico1,paciente1);
        clinica.atenderPaciente(medico2,paciente2);
        clinica.atenderPaciente(medico1,paciente3);

        Factura factura1 = clinica.egresarPaciente(paciente1);
        Factura factura2 = clinica.egresarPaciente(paciente2);
        Factura factura3 = clinica.egresarPaciente(paciente3);
        System.out.println(factura1.getDetalle());
        System.out.println(factura2.getDetalle());
        System.out.println(factura3.getDetalle());
    }
}
