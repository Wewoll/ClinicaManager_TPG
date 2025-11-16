package modelo.modeloDominio.personas.asociado;

import modelo.modeloAplicacion.NotificacionSimulacion;
import modelo.modeloDominio.ambulancia.Ambulancia;
import modelo.modeloDominio.personas.PersonaObservable;
import modelo.modeloDominio.util.Domicilio;
import modelo.modeloDominio.util.TiempoMuerto;
import persistencia.AsociadoDTO;

/**
 * Clase Asociado que representa a un asociado en el sistema.
 */
public class Asociado extends PersonaObservable implements Runnable {
    private int maxCantSolicitudes;
    private Ambulancia ambulancia;
    private int cantSolicitudesAtendidas;

    public Asociado(String nombre, String apellido, String dni, String telefono,
                    Domicilio domicilio, int maxCantSolicitudes, Ambulancia ambulancia) {
        super(nombre, apellido, dni, domicilio, telefono);
        this.ambulancia = ambulancia;
        this.maxCantSolicitudes = maxCantSolicitudes;
        this.cantSolicitudesAtendidas = 0;
        this.ambulancia.agregarAsociado(this);
    }

    public Asociado(String nombre, String apellido, String dni, String telefono, Domicilio domicilio) {
        super(nombre, apellido, dni, domicilio, telefono);
        this.maxCantSolicitudes = Integer.MAX_VALUE;
        this.cantSolicitudesAtendidas = 0;
        this.ambulancia = null;
    }

    public Asociado(AsociadoDTO datos) {
        super(datos.getNombre(), datos.getApellido(), datos.getDni(),
                new Domicilio(datos.getCalle(), datos.getNumero(), datos.getCiudad()),
                datos.getTelefono());
        this.maxCantSolicitudes = Integer.MAX_VALUE;
        this.cantSolicitudesAtendidas = 0;
        this.ambulancia = null;
    }

    public int getCantSolicitudesAtendidas() {
        return cantSolicitudesAtendidas;
    }

    public int getMaxCantSolicitudes() {
        return maxCantSolicitudes;
    }

    /**
     * Simula la atención a domicilio.
     */
    private void atencionADomicilio() {
        ambulancia.atenderDomicilio(this);

        // Si la simulación terminó, no esperar ni regresar
        if (!ambulancia.isSimulacionActiva()) {
            return;
        }

        TiempoMuerto.esperar();
        ambulancia.regresarSinPaciente();
    }

    /**
     * Simula el traslado a la clínica.
     */
    private void trasladoALaClinica() {
        ambulancia.trasladarALaClinica(this);

        // Si la simulación terminó, no esperar ni regresar
        if (!ambulancia.isSimulacionActiva()) {
            return;
        }

        TiempoMuerto.esperar();
        ambulancia.regresarSinPaciente();
    }

    /**
     * Elige aleatoriamente entre atención a domicilio o traslado a la clínica.
     */
    private void eligirServicio() {
        float opcion = (float) Math.random();
        if (opcion < 0.5)
            atencionADomicilio();
        else
            trasladoALaClinica();
    }

    /**
     * Ejecuta el ciclo de atención del asociado.
     */
    @Override
    public void run() {
        while (ambulancia.isSimulacionActiva() &&
                this.cantSolicitudesAtendidas < this.maxCantSolicitudes) {

            eligirServicio();

            // Verificar de nuevo después del servicio
            if (!ambulancia.isSimulacionActiva()) {
                break;
            }

            TiempoMuerto.esperar();
            this.cantSolicitudesAtendidas++;
        }

        // Notificar finalización
        this.setChanged();
        this.notifyObservers(new NotificacionSimulacion(
                "✅ El asociado " + this.getNombre() + " ha finalizado sus solicitudes. (" +
                        this.cantSolicitudesAtendidas + "/" + this.maxCantSolicitudes + ")", "ASOCIADO"));
    }

    @Override
    public int hashCode() {
        return this.getDni().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Asociado))
            return false;
        Asociado asociado = (Asociado) obj;
        return this.getDni().equals(asociado.getDni());
    }
}