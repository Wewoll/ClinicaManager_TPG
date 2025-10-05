package modelo.clinica.modulos.egreso;

import modelo.clinica.modulos.egreso.facturacion.Factura;
import modelo.clinica.modulos.egreso.facturacion.SistemaDeFacturacion;
import modelo.personas.paciente.Paciente;
import util.registros.RegistroPaciente;

import java.util.ArrayList;

public class SistemaDeEgreso
{
    private SistemaDeFacturacion sistemaDeFacturacion;

    public Factura egresarPaciente(Paciente p, ArrayList<RegistroPaciente> consultasMedicas)
    {
        Factura f = new Factura(p);
        f.setDetalle(consultasMedicas);
        return new Factura(p);
    }

}
