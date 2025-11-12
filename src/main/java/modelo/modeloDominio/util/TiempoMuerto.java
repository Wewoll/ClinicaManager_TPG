package modelo.modeloDominio.util;

public class TiempoMuerto
{
    public static void esperar()
    {
        try
        {
            Thread.sleep(Math.round(Math.random()*5000));
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
}
