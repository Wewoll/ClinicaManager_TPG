package modelo.modeloDominio.personas.asociado;

import modelo.modeloAplicacion.NotificacionSimulacion;
import modelo.modeloDominio.ambulancia.Ambulancia;
import modelo.modeloDominio.personas.Persona;
import modelo.modeloDominio.util.Domicilio;
import modelo.modeloDominio.util.TiempoMuerto;
import persistencia.AsociadoDTO;

public class Asociado extends Persona implements Runnable
{
    private int id;
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

    public Asociado(String nombre, String apellido, String dni, String telefono, Domicilio domicilio){
        super(nombre, apellido, dni, domicilio, telefono);
        this.maxCantSolicitudes = Integer.MAX_VALUE;
        this.cantSolicitudesAtendidas = 0;
        this.ambulancia = null;
    }

    public Asociado(AsociadoDTO datos) {
        super(datos.getNombre(), datos.getApellido(), datos.getDni(), new Domicilio(datos.getCalle(), datos.getNumero(), datos.getCiudad()), datos.getTelefono());
        this.maxCantSolicitudes = Integer.MAX_VALUE;
        this.cantSolicitudesAtendidas = 0;
        this.ambulancia = null;
    }
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
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
        ambulancia.atenderDomicilio(this);
        TiempoMuerto.esperar();
        ambulancia.regresarSinPaciente();
    }

    private void trasladoALaClinca()
    {
        new NotificacionSimulacion("El asociado " + this.getDni() + " solicita traslado a clinica.","Asociado");
        ambulancia.trasladarALaClinica(this);
        TiempoMuerto.esperar();
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
            // System.out.println("Asociado " + this.getDni() + " intentando solicitud " + (this.cantSolicitudesAtendidas + 1) + " de " + maxCantSolicitudes);
            eligirServicio();
            TiempoMuerto.esperar();
            // System.out.println("Asociado " + this.getDni() + " ha finalizado solicitud " + (this.cantSolicitudesAtendidas + 1) + " de " + maxCantSolicitudes);
            this.cantSolicitudesAtendidas++;
        }
        // System.out.println("Asociado " + this.getDni() + " ha finalizado sus solicitudes.");
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
