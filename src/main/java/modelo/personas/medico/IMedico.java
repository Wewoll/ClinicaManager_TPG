package modelo.personas.medico;
/**
 * Interfaz IMedico que define los metodos que deben implementar los medicos.
 * Contiene metodos para obtener el sueldo, numero de matricula, nombre y apellido del medico.
 * Tambien contiene metodos estaticos para obtener y setear el sueldo base de los medicos.
 */
public interface IMedico {
    double getSueldo();
    String getNroMatricula();
    String getNombre();
    String getApellido();
    static double getSueldoBase() {
        return Medico.sueldoBase;
    }

    static void setSueldoBase(double sueldoBase) {
        Medico.sueldoBase = sueldoBase;
    }
}
