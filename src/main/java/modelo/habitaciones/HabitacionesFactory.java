package modelo.habitaciones;

import util.Excepciones.TipoDesconocidoException;

public class HabitacionesFactory {

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
