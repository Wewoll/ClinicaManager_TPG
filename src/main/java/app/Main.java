package app;

import modelo.clinica.Clinica;
import modelo.clinica.modulos.egreso.facturacion.Factura;
import modelo.habitaciones.Habitacion;
import modelo.habitaciones.HabitacionesFactory;
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
        Habitacion habitacion1 = null;
        Habitacion habitacion2;
        Habitacion habitacion3;
        Habitacion habitacion4;
        Habitacion habitacion5;
        Habitacion habitacion6;

        try
        {
            habitacion1 = HabitacionesFactory.crearHabitacion("Privada");
            habitacion2 = HabitacionesFactory.crearHabitacion("Compartida");
            habitacion3 = HabitacionesFactory.crearHabitacion("Terapia Intensiva");
            habitacion4 = HabitacionesFactory.crearHabitacion("Privada");
            habitacion5 = HabitacionesFactory.crearHabitacion("Compartida");
            habitacion6 = HabitacionesFactory.crearHabitacion("Terapia Intensiva");
            clinica.agregarHabitacion(habitacion1);
            clinica.agregarHabitacion(habitacion2);
            clinica.agregarHabitacion(habitacion3);
            clinica.agregarHabitacion(habitacion4);
            clinica.agregarHabitacion(habitacion5);
            clinica.agregarHabitacion(habitacion6);

        } catch (TipoDesconocidoException e)
        { // aca nunca va a entrar
            System.out.println("Tipo de desconocido Exception: " + e.getMessage());
        }
        IMedico medico1 = null;
        IMedico medico2 = null;
        try
        {
            medico1 = MedicoFactory.crearMedico("46892358", "2012", "Juan", "Perez", new Domicilio("Calle falsa", 1234, "Mar del Plata"), "223456789", "Cirugia", "Permanente", "Magister");
            medico2 = MedicoFactory.crearMedico("46564934", "2013", "Ana", "Gomez", new Domicilio("Calle verdadera", 5678, "Mar del Plata"), "223456780", "Pediatria", "residente", "grado");
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

        paciente1 = PacienteFactory.crearPaciente("46564934", "Esteban", "Lopez", new Domicilio("Calle 123", 456, "Mar del Plata"), "2233344556", "001", 10, LocalDate.of(2024, 5, 20));
        paciente2 = PacienteFactory.crearPaciente("87654321", "Lucia", "Martinez", new Domicilio("Calle 456", 789, "Mar del Plata"), "2236655443", "002", 30, LocalDate.of(2024, 5, 20));
        paciente3 = PacienteFactory.crearPaciente("12345678", "Carlos", "Gonzalez", new Domicilio("Calle 789", 101, "Mar del Plata"), "2237788990", "003", 65, LocalDate.of(2024, 5, 20));
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

        // internacion de pacientes

        Paciente paciente4 = PacienteFactory.crearPaciente("11223344", "Marta", "Ramirez", new Domicilio("Calle 321", 654, "Mar del Plata"), "2232211445", "004", 70, LocalDate.of(2024, 5, 20));
        clinica.registrarPaciente(paciente4);
        try
        {
            clinica.ingresarPaciente(paciente4);
        } catch (PacienteNoRegistradoException e) // aca nunca va a entrar
        {
            System.out.println(e.getMessage());
        }

        try
        {
            clinica.atenderPaciente(medico1, paciente4);
            clinica.atenderPaciente(medico2, paciente4);
        } catch (Exception e) // aca no va a entrar
        {

            System.out.println(e.getMessage());
        }
        try{
            clinica.internarPaciente(paciente4,habitacion1);

        } catch (Exception e) // aca no va a entrar
        {
            System.out.println(e.getMessage());
        }
        Factura factura5 = null;
        try
        {
            factura5 = clinica.egresarPaciente(paciente4,4);
        } catch (Exception e) // aca nunca va a entrar
        {
            System.out.println(e.getMessage());
        }
        System.out.println(factura5.getDetalle());

        // prueba de excepciones

        IMedico medicoError1 = null;
        IMedico medicoError2 = null;
        IMedico medicoError3 = null;

        try
        {
            medicoError1 = MedicoFactory.crearMedico("21760114", "2013", "Medico", "Error 1", new Domicilio("Calle verdadera", 5678, "Mar del Plata"), "223456780", "Pediatria", "Contratacion no existente", "grado");
        } catch (ContratacionNoExistenteException e)
        {
            System.out.println("Este es el mensaje de contratacion no existente:\t" + e.getMessage());
        } catch (Exception e)
        { // No va entrar, estoy forzando la anterior excepcion
            System.out.println(e.getMessage());
        }
        try
        {
            medicoError2 = MedicoFactory.crearMedico("21760114", "2013", "Medico", "Error 2", new Domicilio("Calle verdadera", 5678, "Mar del Plata"), "223456780", "Esta especialidad no existe", "permanente", "grado");
        } catch (EspecialidadNoExistenteException e)
        {
            System.out.println("Este es el mensaje de especialidad no existente:\t" + e.getMessage());
        } catch (Exception e)
        { // No va entrar, estoy forzando la anterior excepcion
            System.out.println(e.getMessage());
        }
        try
        {
            medicoError3 = MedicoFactory.crearMedico("21760114", "2013", "Medico", "Error 3", new Domicilio("Calle verdadera", 5678, "Mar del Plata"), "223456780", "Pediatria", "permanente", "Este grado no existe");
        } catch (TituloNoExistenteException e)
        {
            System.out.println("Este es el mensaje de titulo no existe:\t" + e.getMessage());
        } catch (Exception e)
        { // No va entrar, estoy forzando la anterior excepcion
            System.out.println(e.getMessage());
        }
        Paciente pacienteNoRegistrado = PacienteFactory.crearPaciente("99999999", "Paciente", "No Registrado", new Domicilio("Calle 123", 456, "Mar del Plata"), "2233344556", "999", 10, LocalDate.of(2024, 5, 20));
        try
        {
            clinica.ingresarPaciente(pacienteNoRegistrado);
        } catch (PacienteNoRegistradoException e)
        { // aca va a entrar
            System.out.println("Este es el mensaje de paciente no registrado:\t" + e.getMessage());
        }
        Paciente pacienteNoIngresado = PacienteFactory.crearPaciente("88888888", "Paciente", "No Ingresado", new Domicilio("Calle 123", 456, "Mar del Plata"), "2233344556", "888", 10, LocalDate.of(2024, 5, 20));
        clinica.registrarPaciente(pacienteNoIngresado);

        try
        {
            clinica.atenderPaciente(medico1, pacienteNoIngresado);
        } catch (PacienteNoIngresadoException e)
        { // acá va a entrar
            System.out.println("Este es el mensaje de paciente no ingresado:\t" + e.getMessage());
        }
    }
}
