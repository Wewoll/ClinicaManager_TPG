package modelo.personas.asociado;

import modelo.ambulancia.Ambulancia;
import modelo.personas.Persona;
import util.Domicilio;

public class Asociado extends Persona implements Runnable
{
    private final int maxCantSolicitudes;
    private final Ambulancia ambulancia;

    public Asociado(String nombre, String apellido, String dni, String telefono, Domicilio domicilio, int maxCantSolicitudes, Ambulancia ambulancia)
    {
        super(nombre, apellido, dni, domicilio, telefono);
        this.ambulancia = ambulancia;
        this.maxCantSolicitudes = maxCantSolicitudes;
    }

    // el asociado puede hacer distintos pedidos en paralelo
    private void atencionADomicilio()
    {
        ambulancia.atenderDomicilio();
        ambulancia.regresarSinPaciente();
    }

    private void trasladoALaClinca()
    {
        ambulancia.trasladarALaClinica();
        ambulancia.regresarSinPaciente();
    }

    private void eligirServicio()
    {
        float opcion = (float) Math.random();
        if (opcion < 0.5)
            atencionADomicilio();
        else
            trasladoALaClinca();
    }

    @Override
    public void run()
    {
        for (int i = 0; i < maxCantSolicitudes; i++)
        {
            System.out.println("Asociado " + this.getDni() + " intentando solicitud " + (i + 1) + " de " + maxCantSolicitudes);
            eligirServicio();
        }
    }

    @Override
    public int hashCode()
    {
        return this.getDni().hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == this)
            return true;
        if (!(obj instanceof Asociado))
            return false;
        Asociado asociado = (Asociado) obj;
        return this.getDni().equals(asociado.getDni());
    }
}
