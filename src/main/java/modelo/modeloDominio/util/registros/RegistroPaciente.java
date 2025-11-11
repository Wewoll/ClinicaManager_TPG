package modelo.modeloDominio.util.registros;

import modelo.modeloDominio.personas.medico.IMedico;

import java.time.LocalDate;

/**
 * Clase que representa el registro de un paciente con un médico en una fecha específica.
 * Contiene información sobre el médico y la fecha del registro.
 */
public class RegistroPaciente {
    private IMedico medico;
    private LocalDate fecha;

    /**
     * Constructor de la clase RegistroPaciente.
     * <b>pre:</b> medico no es nulo, fecha no es nula.
     * <b>post:</b> Se crea un registro de paciente con el médico y la fecha proporcionados.
     * @param medico Médico asociado al registro.
     * @param fecha Fecha del registro.
     */
    public RegistroPaciente(IMedico medico, LocalDate fecha) {
        assert medico != null : "El médico no puede ser nulo";
        assert fecha != null : "La fecha no puede ser nula";
        this.medico = medico;
        this.fecha = fecha;
    }

    /**
     * Obtiene el médico asociado al registro.
     * @return Médico del registro.
     */
    public IMedico getMedico() {
        return medico;
    }

    /**
     * Obtiene la fecha del registro.
     * @return Fecha del registro.
     */
    public LocalDate getFecha() {
        return fecha;
    }

    @Override
    public String toString()
    {
        return "RegistroPaciente{" +
                "medico=" + medico +
                ", fecha=" + fecha +
                '}';
    }
}
