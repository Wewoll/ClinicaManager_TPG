package modelo.personas.operario;

import modelo.ambulancia.Ambulancia;
import modelo.personas.Persona;
import modelo.util.Domicilio;

public class Operario extends Persona implements Runnable
{
    private Ambulancia ambulancia;
    public Operario(String nombre, String apellido, String dni, String telefono, Domicilio domicilio, Ambulancia ambulancia)
    {
        super(nombre, apellido,  dni, domicilio, telefono);
        this.ambulancia = ambulancia;
    }

    @Override
    public void run() {
        // pedir mantenimiento de ambulancias
        while(true){
            try {
                long tiempoEspera = (long)(Math.random() * 5000 + 5000); // entre 5 y 10 segundos
                Thread.sleep(tiempoEspera); // espera 10 segundos antes de solicitar mantenimiento
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            ambulancia.solicitarMantenimiento();
        }

    }


}
