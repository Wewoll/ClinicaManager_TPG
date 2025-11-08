package modelo.personas.asociado;

import modelo.ambulancia.Ambulancia;
import modelo.util.Domicilio;
import patrones.PatronObserver.Observado;

public class Asociado extends Observado implements Runnable
{
    private int maxCantSolicitudes;
    private Ambulancia ambulancia;
    private int cantSolicitudesAtendidas;

    public Asociado(String nombre, String apellido, String dni, String telefono, Domicilio domicilio, int maxCantSolicitudes, Ambulancia ambulancia)
    {
        super(nombre, apellido, dni, domicilio, telefono);
        this.ambulancia = ambulancia;
        this.maxCantSolicitudes = maxCantSolicitudes;
        this.cantSolicitudesAtendidas = 0;
        this.ambulancia.agregarAsociado(this);
    }

    public int getCantSolicitudesAtendidas()
    {
        return cantSolicitudesAtendidas;
    }

    public int getMaxCantSolicitudes()
    {
        return maxCantSolicitudes;
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
        while (ambulancia.isSimulacionActiva() && this.cantSolicitudesAtendidas < this.maxCantSolicitudes)
        {
            System.out.println("Asociado " + this.getDni() + " intentando solicitud " + (this.cantSolicitudesAtendidas + 1) + " de " + maxCantSolicitudes);
            eligirServicio();
            this.cantSolicitudesAtendidas++;
        }
        System.out.println("Asociado " + this.getDni() + " ha finalizado sus solicitudes.");
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
