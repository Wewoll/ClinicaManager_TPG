package modelo.personas.medico;

public interface IMedico {
    double getSueldo();
    String getNroMatricula();
    String getNombre();
    String getApellido();
    boolean internarPaciente();
    static double getSueldoBase() {
        return Medico.sueldoBase;
    }

    static void setSueldoBase(double sueldoBase) {
        Medico.sueldoBase = sueldoBase;
    }
}
