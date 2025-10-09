package app;

import modelo.clinica.Clinica;
import modelo.clinica.modulos.egreso.facturacion.Factura;
import modelo.personas.medico.IMedico;
import modelo.personas.medico.MedicoFactory;
import modelo.personas.paciente.Paciente;
import modelo.personas.paciente.PacienteFactory;
import util.Domicilio;
import util.Excepciones.*;

import java.time.LocalDate;

public class Main
{
    public static void main(String[] args)
    {
        Clinica clinica = Clinica.getInstancia();

        MedicoFactory medicoFactory = new MedicoFactory();
        PacienteFactory pacienteFactory = new PacienteFactory();
        IMedico medico1 = null;
        IMedico medico2 = null;
        try
        {
            medico1 = medicoFactory.crearMedico("46892358", "2012", "Juan", "Perez", new Domicilio("Calle falsa", 1234, "Mar del Plata"), "223456789", "Cirugia", "Permanente", "Magister");
            medico2 = medicoFactory.crearMedico("46564934", "2013", "Ana", "Gomez", new Domicilio("Calle verdadera", 5678, "Mar del Plata"), "223456780", "Pediatria", "residente", "grado");
            clinica.registrarMedico(medico1);
            clinica.registrarMedico(medico2);

        } catch (EspecialidadNoExistenteException | ContratacionNoExistenteException |
                 TituloNoExistenteException e) // aca no va a entrar
        {
            System.out.println(e.getMessage());
        }
        Paciente paciente1 = null;
        Paciente paciente2 = null;
        Paciente paciente3 = null;

        paciente1 = pacienteFactory.crearPaciente("46564934", "Esteban", "Lopez", new Domicilio("Calle 123", 456, "Mar del Plata"), "2233344556", "001", 10, LocalDate.of(2024, 5, 20));
        paciente2 = pacienteFactory.crearPaciente("87654321", "Lucia", "Martinez", new Domicilio("Calle 456", 789, "Mar del Plata"), "2236655443", "002", 30, LocalDate.of(2024, 5, 20));
        paciente3 = pacienteFactory.crearPaciente("12345678", "Carlos", "Gonzalez", new Domicilio("Calle 789", 101, "Mar del Plata"), "2237788990", "003", 65, LocalDate.of(2024, 5, 20));
        clinica.registrarPaciente(paciente1);
        clinica.registrarPaciente(paciente2);
        clinica.registrarPaciente(paciente3);

        try
        {
            clinica.ingresarPaciente(paciente1);
            clinica.ingresarPaciente(paciente2);
            clinica.ingresarPaciente(paciente3);
        } catch (PacienteNoRegistradoException e) // aca nunca va a entrar
        {
            System.out.println(e.getMessage());
        }

        try
        {
            clinica.atenderPaciente(medico1, paciente1);
            clinica.atenderPaciente(medico2, paciente1); // derivar paciente

            clinica.atenderPaciente(medico2, paciente2);
            clinica.atenderPaciente(medico1, paciente3);
        } catch (PacienteNoIngresadoException e) // aca no va a entrar
        {
            System.out.println("Estoy en el atender paciente try catch");
            System.out.println(e.getMessage());
        }

        Factura factura1 = null;
        Factura factura2 = null;
        Factura factura3 = null;
        try
        {
            factura1 = clinica.egresarPaciente(paciente1);
            factura2 = clinica.egresarPaciente(paciente2);
            factura3 = clinica.egresarPaciente(paciente3);
        } catch (PacienteSinConsultasMedicasException e) // aca nunca va a entrar
        {
            System.out.println(e.getMessage());
        }
        System.out.println(factura1.getDetalle());
        System.out.println(factura2.getDetalle());
        System.out.println(factura3.getDetalle());

        // prueba de excepciones

        IMedico medicoError1 = null;
        IMedico medicoError2 = null;
        IMedico medicoError3 = null;

        try
        {
            medicoError1 = medicoFactory.crearMedico("21760114", "2013", "Medico", "Error 1", new Domicilio("Calle verdadera", 5678, "Mar del Plata"), "223456780", "Pediatria", "Contratacion no existente", "grado");
        } catch (ContratacionNoExistenteException e)
        {
            System.out.println("Este es el mensaje de contratacion no existente:\t" + e.getMessage());
        }catch (Exception e){ // No va entrar, estoy forzando la anterior excepcion
            System.out.println(e.getMessage());
        }
        try{
            medicoError2 =  medicoFactory.crearMedico("21760114", "2013", "Medico", "Error 2", new Domicilio("Calle verdadera", 5678, "Mar del Plata"), "223456780", "Esta especialidad no existe", "permanente", "grado");
        } catch (EspecialidadNoExistenteException e)
        {
            System.out.println("Este es el mensaje de especialidad no existente:\t" + e.getMessage());
        }catch (Exception e){ // No va entrar, estoy forzando la anterior excepcion
            System.out.println(e.getMessage());
        }
        try{
            medicoError3 =  medicoFactory.crearMedico("21760114", "2013", "Medico", "Error 3", new Domicilio("Calle verdadera", 5678, "Mar del Plata"), "223456780", "Pediatria", "permanente", "Este grado no existe");
        } catch (TituloNoExistenteException e)
        {
            System.out.println("Este es el mensaje de titulo no existe:\t" + e.getMessage());
        }catch (Exception e){ // No va entrar, estoy forzando la anterior excepcion
            System.out.println(e.getMessage());
        }

    }
    }
