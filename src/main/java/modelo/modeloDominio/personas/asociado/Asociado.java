package modelo.modeloDominio.personas.asociado;

import modelo.modeloAplicacion.NotificacionSimulacion;
import modelo.modeloDominio.ambulancia.Ambulancia;
import modelo.modeloDominio.personas.Persona;
import modelo.modeloDominio.util.Domicilio;
import modelo.modeloDominio.util.TiempoMuerto;
import persistencia.AsociadoDTO;
/**
 * Clase Asociado que representa a un asociado en el sistema.
 * Extiende de la clase Persona e implementa Runnable para permitir la ejecución en un hilo separado.
 * Contiene atributos para la cantidad máxima de solicitudes, la ambulancia asociada y la cantidad de solicitudes atendidas.
 */
public class Asociado extends Persona implements Runnable
{
    private int maxCantSolicitudes;
    private Ambulancia ambulancia;
    private int cantSolicitudesAtendidas;

    /**
     * Constructor de la clase Asociado.
     * <b>post:</b> se crea una instancia de Asociado con los datos proporcionados y se asocia a la ambulancia dada.
     * @param nombre El nombre del asociado.
     * @param apellido El apellido del asociado.
     * @param dni El DNI del asociado.
     * @param telefono El teléfono del asociado.
     * @param domicilio El domicilio del asociado.
     * @param maxCantSolicitudes La cantidad máxima de solicitudes que el asociado puede atender.
     * @param ambulancia La ambulancia asociada al asociado.
     */
    public Asociado(String nombre, String apellido, String dni, String telefono, Domicilio domicilio, int maxCantSolicitudes, Ambulancia ambulancia)
    {
        super(nombre, apellido, dni, domicilio, telefono);
        this.ambulancia = ambulancia;
        this.maxCantSolicitudes = maxCantSolicitudes;
        this.cantSolicitudesAtendidas = 0;
        this.ambulancia.agregarAsociado(this);
    }

    /**
     * Constructor de la clase Asociado con solicitudes ilimitadas.
     * <b>post:</b> se crea una instancia de Asociado con los datos proporcionados y solicitudes ilimitadas.
     * @param nombre El nombre del asociado.
     * @param apellido El apellido del asociado.
     * @param dni El DNI del asociado.
     * @param telefono El teléfono del asociado.
     * @param domicilio El domicilio del asociado.
     */
    public Asociado(String nombre, String apellido, String dni, String telefono, Domicilio domicilio){
        super(nombre, apellido, dni, domicilio, telefono);
        this.maxCantSolicitudes = Integer.MAX_VALUE;
        this.cantSolicitudesAtendidas = 0;
        this.ambulancia = null;
    }
    /**
     * Constructor de la clase Asociado a partir de un DTO.
     * <b>post:</b> se crea una instancia de Asociado con los datos proporcionados en el DTO y solicitudes ilimitadas.
     * @param datos El DTO que contiene los datos del asociado.
     */
    public Asociado(AsociadoDTO datos) {
        super(datos.getNombre(), datos.getApellido(), datos.getDni(), new Domicilio(datos.getCalle(), datos.getNumero(), datos.getCiudad()), datos.getTelefono());
        this.maxCantSolicitudes = Integer.MAX_VALUE;
        this.cantSolicitudesAtendidas = 0;
        this.ambulancia = null;
    }
    /**
     * Método get
     * @return cantSolicitudesAtendidas
     */
    public int getCantSolicitudesAtendidas()
    {
        return cantSolicitudesAtendidas;
    }
    /**
     * Método get
     * @return maxCantSolicitudes
     */
    public int getMaxCantSolicitudes()
    {
        return maxCantSolicitudes;
    }

    /**
     * Método privado que simula la atención a domicilio.
     * La ambulancia atiende al asociado en su domicilio, espera un tiempo muerto y luego regresa sin paciente.
     */
    // el asociado puede hacer distintos pedidos en paralelo
    private void atencionADomicilio()
    {
        ambulancia.atenderDomicilio(this);
        TiempoMuerto.esperar();
        ambulancia.regresarSinPaciente();
    }
    /**
     * Método privado que simula el traslado a la clínica.
     * La ambulancia traslada al asociado a la clínica, espera un tiempo muerto y luego regresa sin paciente.
     */
    private void trasladoALaClinca()
    {
        new NotificacionSimulacion("El asociado " + this.getDni() + " solicita traslado a clinica.","Asociado");
        ambulancia.trasladarALaClinica(this);
        TiempoMuerto.esperar();
        ambulancia.regresarSinPaciente();
    }
    /**
     * Método privado que elige aleatoriamente entre atención a domicilio o traslado a la clínica.
     */
    private void eligirServicio()
    {
        float opcion = (float) Math.random();
        if (opcion < 0.5)
            atencionADomicilio();
        else
            trasladoALaClinca();
    }
    /**
     * Método run que ejecuta el ciclo de atención del asociado.
     * Mientras la simulación esté activa y no se haya alcanzado la cantidad máxima de solicitudes, el asociado elige un servicio y espera un tiempo muerto.
     */
    @Override
    public void run()
    {
        while (ambulancia.isSimulacionActiva() && this.cantSolicitudesAtendidas < this.maxCantSolicitudes)
        {
            eligirServicio();
            TiempoMuerto.esperar();
            this.cantSolicitudesAtendidas++;
        }
    }

    /**
     * Método hashCode que genera un código hash basado en el DNI del asociado.
     * @return El código hash del asociado.
     */
    @Override
    public int hashCode()
    {
        return this.getDni().hashCode();
    }

    /**
     * Método equals que compara dos objetos Asociado basándose en su DNI.
     * @param obj El objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
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
