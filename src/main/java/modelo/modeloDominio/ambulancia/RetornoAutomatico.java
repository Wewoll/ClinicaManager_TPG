package modelo.modeloDominio.ambulancia;

/**
 * Clase RetornoAutomatico que implementa Runnable para manejar el retorno automático de una ambulancia.
 * Este hilo periódicamente intenta retornar la ambulancia a la clínica si está en otro lugar.
 */
public class RetornoAutomatico implements Runnable {
    private Ambulancia ambulancia;
    private static final long INTERVALO_RETORNO = 10000; // 10 segundos

    /**
     * Constructor de la clase RetornoAutomatico.
     * @param ambulancia La ambulancia asociada a este retorno automático.
     */
    public RetornoAutomatico(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }

    /**
     * Método run que se ejecuta en el hilo.
     * Periódicamente intenta retornar la ambulancia a la clínica mientras la simulación esté activa.
     */
    @Override
    public void run() {
        while (ambulancia.isSimulacionActiva()) {
            try {
                Thread.sleep(INTERVALO_RETORNO);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return; // Salir limpiamente si el hilo es interrumpido
            }

            // Verificar nuevamente después del sleep
            if (!ambulancia.isSimulacionActiva()) {
                break;
            }

            // Intentar retorno automático
            // El método retornoAutomatico en Ambulancia debe verificar
            // si es apropiado hacer el retorno según el estado actual
            ambulancia.retornoAutomatico(this);
        }
    }
}