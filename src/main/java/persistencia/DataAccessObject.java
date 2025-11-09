package persistencia;

import modelo.personas.asociado.Asociado;
import modelo.util.Domicilio;

import java.sql.*;
import java.util.ArrayList;

public class DataAccessObject {
    private BaseDeDatos bd;

    public DataAccessObject() {
        this.bd = new BaseDeDatos();
    }

    public void guardar(Asociado asociado) throws SQLException {
        AsociadoDTO aDTO = CrearDTO(asociado);

        String query = "INSERT INTO Asociados VALUES (?,?,?,?,?,?)";

        bd.ejecutarInsert(query,aDTO);
    }
    public void cerrarConexion() throws SQLException{
        BaseDeDatos.cerrarConexion();
    }

    public  ArrayList<AsociadoDTO> cargarConLimite(int limite) throws SQLException {

        String query = "SELECT * FROM Asociados LIMIT ? ;";

        ResultSet resultado = bd.ejecutarConsulta(query, -1);
        ArrayList<AsociadoDTO> asociadosDTO = new ArrayList<>();
        while (resultado.next()) {
            AsociadoDTO aDTO = this.CrearDTO(resultado.getInt("id"), resultado.getString("nombre"), resultado.getString("apellido"), resultado.getString("dni"),
                    resultado.getString("telefono"), resultado.getObject("domicilio"));
            asociadosDTO.add(aDTO);
        }
        return asociadosDTO;
    }

    public AsociadoDTO cargar(int id) throws SQLException {

        String query = "SELECT * FROM Asociados WHERE id = ? ;";

        ResultSet resultado = bd.ejecutarConsulta(query, id);

        AsociadoDTO aDTO = null;
        aDTO = this.CrearDTO(id, resultado.getString("nombre"), resultado.getString("apellido"), resultado.getString("dni"),resultado.getString("telefono"), resultado.getObject("domicilio"));
        return aDTO;
    }
    public ArrayList<AsociadoDTO> cargarBD() throws SQLException {
        String query = "SELECT * FROM Asociados ;";

        ResultSet resultado = bd.ejecutarConsulta(query, -1);
        ArrayList<AsociadoDTO> asociadosDTO = new ArrayList<>();
        while (resultado.next()) {
            AsociadoDTO aDTO = this.CrearDTO(resultado.getInt("id"), resultado.getString("nombre"), resultado.getString("apellido"), resultado.getString("dni"),
                    resultado.getString("telefono"), resultado.getObject("domicilio"));
            asociadosDTO.add(aDTO);
        }
        return asociadosDTO;
    }
    public void eliminar(int id) throws SQLException{
        String query = "DELETE FROM Asociados WHERE id = ? ;";

        bd.ejecutarDelete(query, id);

    }

    public void actualizar(Asociado asociado) throws SQLException {
        String quety = "UPDATE Asociados SET nombre = ?, apellido = ?, dni = ?, domicilio = ?, telefono = ? WHERE id = ? ;";
        AsociadoDTO aDTO = CrearDTO(asociado);
        bd.ejecutarActualizacion(quety, aDTO);
    }
    public AsociadoDTO CrearDTO(Asociado asociado) {
        AsociadoDTO aDTO = new AsociadoDTO();
        aDTO.setId(asociado.getId());
        aDTO.setNombre(asociado.getNombre());
        aDTO.setApellido(asociado.getApellido());
        aDTO.setDni(asociado.getDni());
        aDTO.setDomicilio(asociado.getDomicilio());
        aDTO.setTelefono(asociado.getTelefono());
        return aDTO;
    }
    public AsociadoDTO CrearDTO(int id, String nombre, String apellido, String dni, String telefono, Object domicilio) {
        AsociadoDTO aDTO = new AsociadoDTO();
        aDTO.setId(id);
        aDTO.setNombre(nombre);
        aDTO.setApellido(apellido);
        aDTO.setDni(dni);
        aDTO.setTelefono(telefono);
        aDTO.setDomicilio((Domicilio) domicilio);
        return aDTO;
    }
}
