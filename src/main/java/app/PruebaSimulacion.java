package app;

import modelo.modeloDominio.ambulancia.Ambulancia;
import modelo.modeloDominio.ambulancia.RetornoAutomatico;
import modelo.modeloDominio.personas.asociado.Asociado;
import modelo.modeloDominio.personas.operario.Operario;

public class PruebaSimulacion
{
    public static void main(String[] args)
    {
        Ambulancia ambulancia = new Ambulancia();
        Asociado asociado1 = new Asociado("Juan", "Perez", "12345678", "555-1234", null, 5, ambulancia);
        Asociado asociado2 = new Asociado("Maria", "Gomez", "87654321", "555-5678", null, 5, ambulancia);
        Asociado asociado3 = new Asociado("Carlos", "Lopez", "11223344", "555-8765", null, 5, ambulancia);
        Asociado asociado4 = new Asociado("Ana", "Martinez", "44332211", "555-4321", null, 5, ambulancia);
        Asociado asociado5 = new Asociado("Luis", "Rodriguez", "55667788", "555-6789", null, 5, ambulancia);
        Asociado asociado6 = new Asociado("Sofia", "Fernandez", "88776655", "555-9876", null, 5, ambulancia);
        RetornoAutomatico retornoAutomatico = new RetornoAutomatico(ambulancia);
        Operario operario = new Operario("Pedro", "Sanchez", "99887766", "555-0000", null, ambulancia);

        new Thread(retornoAutomatico).start();
        new Thread(operario).start();
        new Thread(asociado1).start();
        new Thread(asociado2).start();
        new Thread(asociado3).start();
        new Thread(asociado4).start();
        new Thread(asociado5).start();
        new Thread(asociado6).start();

    }
}
