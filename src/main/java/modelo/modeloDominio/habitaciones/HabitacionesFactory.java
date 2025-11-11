package modelo.modeloDominio.habitaciones;

import modelo.modeloDominio.util.Excepciones.TipoDesconocidoException;

/**
 * Clase HabitacionesFactory que implementa el patron de diseño Factory Method
 * Permite crear instancias de diferentes tipos de habitaciones basadas en un string de tipo
 */
public class HabitacionesFactory {

    /**
     * Metodo estatico que crea una instancia de Habitacion segun el tipo proporcionado
     * <b>pre:</b> tipo no debe ser nulo ni vacio
     * @param tipo el tipo de habitacion a crear ("privada", "compartida", "terapia intensiva")
     * <b>post:</b> se retorna una instancia de Habitacion correspondiente al tipo
     * @return una instancia de Habitacion correspondiente al tipo
     * @throws TipoDesconocidoException si el tipo no coincide con ninguna habitacion conocida
     */
    public static Habitacion crearHabitacion(String tipo) throws TipoDesconocidoException
    {
        switch (tipo.toLowerCase()) {
            case "privada":
                return new HabitacionPrivada();
            case "compartida":
                return new HabitacionCompartida();
            case "terapia intensiva":
                return new HabitacionTerapiaIntensiva();
            default:
                throw new TipoDesconocidoException("Tipo de habitacion desconocido: " + tipo);
        }
    }
}
