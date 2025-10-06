package modelo.clinica.modulos.egreso.facturacion;

import java.util.ArrayList;

import modelo.personas.paciente.*;
import util.registros.*;

public class Factura
{
    private static int contadorFacturas = 0;
    private final int numeroFactura;
    private final Paciente paciente;
    private final static double INCREMENTO = 1.20;
    private String detalle;

    public Factura(Paciente paciente)
    {
        this.numeroFactura = ++Factura.contadorFacturas;
        this.paciente = paciente;
    }

    public int getNumeroFactura()
    {
        return numeroFactura;
    }

    public Paciente getPaciente()
    {
        return paciente;
    }


    public void setDetalle(ArrayList<RegistroPaciente> consultasMedicas)
    {
        StringBuilder aux = new StringBuilder();
        aux.append("N° Factura: ").append(this.numeroFactura).append("\n").append("Nombre Paciente: ").append(this.paciente.getNombre()).append("\n").append("Fecha Ingreso: ").append(consultasMedicas.get(0).getFecha()).append("\n").append("Fecha Egreso: ").append(consultasMedicas.get(0).getFecha().plusDays(paciente.getDiasInternado())).append("\n");
        if (paciente.getDiasInternado() > 0)
            aux.append("Cantidad Dias: ").append(this.paciente.getDiasInternado()).append("\n").append("Habitacion tipo: ").append(this.paciente.getHabitacion().toString()).append("\t").append("Costo: $").append(this.paciente.getHabitacion().calcularArancelInternacion(this.paciente)).append("\n");

        aux.append("Consultas Medicas: \n");

        int i;
        double total = 0;
        for (i = 0; (i < consultasMedicas.size()); i++)
        {
            aux.append("\tNombre medico: ").append(consultasMedicas.get(i).getMedico().getNombre()).append("\t").append("Especialidad: ").append(consultasMedicas.get(i).getMedico().toString()).append("\t").append("Subtotal: $").append(consultasMedicas.get(i).getMedico().getSueldo() * INCREMENTO).append("\n");
            total += consultasMedicas.get(i).getMedico().getSueldo() * INCREMENTO;
        }
        aux.append("\t \t Total: $").append(total).append("\n");
        this.detalle = aux.toString();
    }

    public String getDetalle()
    {
        return this.detalle;
    }
}
