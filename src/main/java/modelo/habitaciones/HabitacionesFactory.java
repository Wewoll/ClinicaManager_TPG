package modelo.habitaciones;

public class HabitacionesFactory {

    public static Habitacion crearHabitacion(String tipo) {
        switch (tipo.toLowerCase()) {
            case "privada":
                return new HabitacionPrivada();
            case "compartida":
                return new HabitacionCompartida();
            case "terapia intensiva":
                return new HabitacionTerapiaIntensiva();
            default:
                throw new IllegalArgumentException("Tipo de habitacion desconocido: " + tipo);
        }
    }
}
