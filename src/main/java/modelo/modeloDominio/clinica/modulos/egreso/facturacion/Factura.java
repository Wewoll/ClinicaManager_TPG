package modelo.modeloDominio.clinica.modulos.egreso.facturacion;

import java.util.ArrayList;

import modelo.modeloDominio.personas.paciente.*;
import modelo.modeloDominio.util.registros.*;

/**
 * Clase Factura que representa una factura generada para un paciente.
 * Contiene como atributos el numero de factura, paciente asociado, el detalle de la factura, y un contador estatico para llevar el control de las facturas generadas.
 */
public class Factura
{
    private static int contadorFacturas = 0;
    private final int numeroFactura;
    private final Paciente paciente;
    private final static double INCREMENTO = 1.20;
    private String detalle;

    /**
     * Constructor de Factura
     * <b>pre:</b> paciente no debe ser nulo.
     * <b>post:</b> se crea una factura con un numero unico y el
     *
     * @param paciente Paciente al que se le genera la factura.
     */
    public Factura(Paciente paciente)
    {
        assert  paciente != null : "El paciente no puede ser nulo";
        this.numeroFactura = ++Factura.contadorFacturas;
        this.paciente = paciente;
    }

    /**
     * Metodo publico int para preguntar cual es el numero de factura.
     *
     * @return numeroFactura de la factura.
     */
    public int getNumeroFactura()
    {
        return numeroFactura;
    }

    /**
     * Metodo publico Paciente para preguntar cual es el paciente asociado a la factura.
     *
     * @return paciente asociado a la factura.
     */
    public Paciente getPaciente()
    {
        return paciente;
    }

    /**
     * Metodo publico void para setear el detalle de la factura.
     * <b>pre:</b> consultasMedicas no debe ser nulo.
     * <b>post:</b> se setea el detalle de la factura con la informacion de las consultas medicas.
     *
     * @param consultasMedicas Lista de registros de consultas medicas del paciente.
     */
    public void setDetalle(ArrayList<RegistroPaciente> consultasMedicas)
    {
        assert consultasMedicas != null : "La lista de consultas medicas no puede ser nulo";
        StringBuilder aux = new StringBuilder();
        aux.append("N° Factura: ").append(this.numeroFactura).append("\n").append("Nombre Paciente: ").append(this.paciente.getNombre()).append("\n").append("Fecha Ingreso: ").append(consultasMedicas.get(0).getFecha()).append("\n").append("Fecha Egreso: ").append(consultasMedicas.get(0).getFecha().plusDays(paciente.getDiasInternado())).append("\n");
        if (this.paciente.getDiasInternado() > 0)
            aux.append("Cantidad Dias: ").append(this.paciente.getDiasInternado()).append("\n").append("Habitacion tipo: ").append(this.paciente.getHabitacion().toString()).append("\t").append("Costo: $").append(this.paciente.getHabitacion().calcularArancelInternacion(this.paciente)).append("\n");

        aux.append("Consultas Medicas: \n");

        int i;
        double total = 0;
        for (i = 0; (i < consultasMedicas.size()); i++)
        {
            aux.append("\tNombre medico: ").append(consultasMedicas.get(i).getMedico().getNombre()).append("\t").append("Especialidad: ").append(consultasMedicas.get(i).getMedico().toString()).append("\t").append("Subtotal: $").append(consultasMedicas.get(i).getMedico().getSueldo() * INCREMENTO).append("\n");
            total += consultasMedicas.get(i).getMedico().getSueldo() * INCREMENTO;
        }
        if (this.paciente.getDiasInternado() > 0)
            total += this.paciente.getHabitacion().calcularArancelInternacion(this.paciente);
        aux.append("\t \t Total: $").append(total).append("\n");
        this.detalle = aux.toString();
    }

    /**
     * Metodo publico String para preguntar cual es el detalle de la factura.
     *
     * @return detalle de la factura.
     */
    public String getDetalle()
    {
        return this.detalle;
    }
}
