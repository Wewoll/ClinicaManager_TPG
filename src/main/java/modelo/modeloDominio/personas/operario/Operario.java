package modelo.modeloDominio.personas.operario;

import modelo.modeloDominio.ambulancia.Ambulancia;
import modelo.modeloDominio.personas.Persona;
import modelo.modeloDominio.personas.asociado.Asociado;
import modelo.modeloDominio.util.Domicilio;

import java.util.ArrayList;

public class Operario extends Persona implements Runnable
{
    private Ambulancia ambulancia;
    public Operario(String nombre, String apellido, String dni, String telefono, Domicilio domicilio, Ambulancia ambulancia)
    {
        super(nombre, apellido,  dni, domicilio, telefono);
        this.ambulancia = ambulancia;
    }
    private void terminaronAsociados()
    {
        ArrayList<Asociado> asociados = this.ambulancia.getAsociados();
        for (Asociado asociado : asociados)
        {
            System.out.println("Cantidad de solicitudes atendidas por asociado " + asociado.getDni() + ": " + asociado.getCantSolicitudesAtendidas() + "/" + asociado.getMaxCantSolicitudes());
            if (asociado.getCantSolicitudesAtendidas() >= asociado.getMaxCantSolicitudes())
            {
                this.ambulancia.setSimulacionActiva(false);
                return;
            }
        }
        this.ambulancia.setSimulacionActiva(true);
    }
    @Override
    public void run() {
        // pedir mantenimiento de ambulancias
        while(this.ambulancia.isSimulacionActiva()){
            try {
                long tiempoEspera = (long)(Math.random() * 5000 + 5000); // entre 5 y 10 segundos
                Thread.sleep(tiempoEspera); // espera 10 segundos antes de solicitar mantenimiento
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            ambulancia.solicitarMantenimiento();
            try{
                System.out.println("Mantenimiento realizado por operario " + this.getDni());
                Thread.sleep(5000);
            } catch (InterruptedException e) {}
            ambulancia.volviendoDelTaller();
            terminaronAsociados();
        }

    }


}
