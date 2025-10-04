package modelo.clinica;
import java.util.ArrayList;

import modelo.habitaciones.Habitacion;
import modelo.personas.paciente.*;
import util.registros.*;

public class Factura {
    private static int contadorFacturas = 0;
    private int numeroFactura;
    private Paciente paciente;
    private Habitacion habitacion;
    private final static double INCREMENTO = 1.20;

    public Factura(Paciente paciente, Habitacion habitacion) {
        this.numeroFactura = ++Factura.contadorFacturas;
        this.paciente = paciente;
        this.habitacion = habitacion;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public String detalle(ArrayList<RegistroPaciente> consultasMedicas) {
        String aux = "";
        aux += "N° Factura: " + this.numeroFactura + "\n" + 
                "Nombre Paciente: " + this.paciente.getNombre() + "\n" +
                "Fecha Ingreso: " + consultasMedicas.get(0).getFecha() + "\n" +
                "Fecha Egreso: " + consultasMedicas.get(0).getFecha().plusDays(paciente.getDiasInternado())  + "\n" +
                "Cantidad Dias: " + this.paciente.getDiasInternado() + "\n" +
                "Habitacion tipo: " + this.habitacion.toString() + "\t" + "Costo: $" + this.habitacion.calcularArancelInternacion(this.paciente) + "\n";
        aux += "Consultas Medicas: \n";

        int i;
        double total = 0;
        for ( i = 0; (i < consultasMedicas.size()); i++){
            aux += "\tNombre medico: " + consultasMedicas.get(i).getMedico().getNombre() + "\t" + "Especialidad: " + consultasMedicas.get(i).getMedico().toString() + "\t" + "Subtotal: $" + consultasMedicas.get(i).getMedico().getSueldo()*INCREMENTO + "\n";
            total += consultasMedicas.get(i).getMedico().getSueldo()*INCREMENTO;
        }
        aux += "\t \t Total: $" + total + "\n";
        return aux;
    }
}
