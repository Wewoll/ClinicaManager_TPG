package util.registros;

import modelo.personas.paciente.Paciente;

import java.time.LocalDate;

/**
 * Clase que representa un registro médico de un paciente en una fecha específica.
 * Contiene información sobre el paciente y la fecha del registro.
 */
public class RegistroMedico {
    private Paciente paciente;
    private LocalDate fecha;

    /**
     * Constructor de la clase RegistroMedico.
     * <b>pre:</b> p != null && fecha != null
     * <b>post:</b> Crea un nuevo registro médico con el paciente y la fecha proporcionados.
     * @param p Paciente asociado al registro.
     * @param fecha Fecha del registro.
     */
    public RegistroMedico(Paciente p, LocalDate fecha){
        this.paciente = p;
        this.fecha = fecha;
    }

    /**
     * Obtiene el paciente asociado al registro médico.
     * @return Paciente del registro.
     */
    public Paciente getPaciente() {
        return paciente;
    }
    /**
     * Obtiene la fecha del registro médico.
     * @return Fecha del registro.
     */
    public LocalDate getFecha() {
        return fecha;
    }
    /**
     * Representación en cadena del registro médico.
     * @return Cadena que representa el registro médico con paciente y fecha.
     */
    @Override
    public String toString() {
        return "RegistroMedico{" +
                "paciente=" + paciente +
                ", fecha=" + fecha +
                '}';
    }
}
