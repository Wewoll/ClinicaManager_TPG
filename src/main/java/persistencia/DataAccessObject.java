package persistencia;

import modelo.personas.asociado.Asociado;
import modelo.util.Domicilio;

import java.sql.*;
import java.util.ArrayList;
/**
 * Clase DataAccessObject que maneja la persistencia de objetos Asociado en la base de datos.
 * Contiene métodos para guardar, cargar, eliminar y actualizar asociados.
 * Utiliza la clase BaseDeDatos para interactuar con la base de datos.
 */
public class DataAccessObject {
    private BaseDeDatos bd;

    /**
     * Constructor de la clase DataAccessObject.
     * <b>post:</b> se crea un objeto DataAccessObject con una instancia de BaseDeDatos.
     */
    public DataAccessObject() {
        this.bd = new BaseDeDatos();
    }

    /**
     * Guarda un objeto Asociado en la base de datos.
     * <b>pre:</b> asociado no debe ser nulo.
     * <b>post:</b> se guarda el asociado en la base de datos.
     * @param asociado El objeto Asociado a guardar.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    public void guardar(Asociado asociado) throws SQLException {
        assert asociado != null : "El asociado no puede ser nulo";

        AsociadoDTO aDTO = CrearDTO(asociado);

        String query = "INSERT INTO Asociados VALUES (?,?,?,?,?,?,?,?);";

        bd.ejecutarInsert(query,aDTO);
    }

    /**
     * Cierra la conexión con la base de datos.
     * @throws SQLException Si ocurre un error al cerrar la conexión.
     */
    public void cerrarConexion() throws SQLException{
        BaseDeDatos.cerrarConexion();
    }
    /**
     * Carga una lista de objetos Asociado desde la base de datos con un límite especificado.
     * <b>pre:</b> limite debe ser mayor que 0.
     * <b>post:</b> se devuelve una lista de asociados cargados desde la base de datos.
     * @param limite El número máximo de asociados a cargar.
     * @return Una lista de objetos AsociadoDTO.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    public  ArrayList<AsociadoDTO> cargarConLimite(int limite) throws SQLException {
        assert limite > 0 : "El limite debe ser mayor que 0";
        String query = "SELECT * FROM Asociados LIMIT ? ;";

        ResultSet resultado = bd.ejecutarConsulta(query, limite);
        ArrayList<AsociadoDTO> asociadosDTO = new ArrayList<>();
        while (resultado.next()) {
            AsociadoDTO aDTO = this.CrearDTO(resultado.getInt("id"), resultado.getString("nombre"), resultado.getString("apellido"), resultado.getString("dni")
                    , resultado.getString("calle"), resultado.getInt("numero"), resultado.getString("ciudad"), resultado.getString("telefono"));
            asociadosDTO.add(aDTO);
        }
        return asociadosDTO;
    }

    /**
     * Carga un objeto Asociado desde la base de datos por su ID.
     * <b>pre:</b> id debe ser mayor que 0.
     * <b>post:</b> se devuelve el asociado cargado desde la base de datos.
     * @param id El ID del asociado a cargar.
     * @return Un objeto AsociadoDTO.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    public AsociadoDTO cargar(int id) throws SQLException {
        assert  id > 0 : "El id debe ser mayor que 0";
        String query = "SELECT * FROM Asociados WHERE id = ? ;";

        ResultSet resultado = bd.ejecutarConsulta(query, id);

        AsociadoDTO aDTO = null;
        aDTO = this.CrearDTO(resultado.getInt("id"), resultado.getString("nombre"), resultado.getString("apellido"), resultado.getString("dni")
                , resultado.getString("calle"), resultado.getInt("numero"), resultado.getString("ciudad"), resultado.getString("telefono"));
        return aDTO;
    }

    /**
     * Carga todos los objetos Asociado desde la base de datos.
     * <b>post:</b> se devuelve una lista de todos los asociados cargados desde la base de datos.
     * @return Una lista de objetos AsociadoDTO.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    public ArrayList<AsociadoDTO> cargarBD() throws SQLException {
        String query = "SELECT * FROM Asociados ;";

        ResultSet resultado = bd.ejecutarConsulta(query, -1);
        ArrayList<AsociadoDTO> asociadosDTO = new ArrayList<>();
        while (resultado.next()) {
            AsociadoDTO aDTO = this.CrearDTO(resultado.getInt("id"), resultado.getString("nombre"), resultado.getString("apellido"), resultado.getString("dni")
                    , resultado.getString("calle"), resultado.getInt("numero"), resultado.getString("ciudad"), resultado.getString("telefono"));
            asociadosDTO.add(aDTO);
        }
        return asociadosDTO;
    }
    /**
     * Elimina un objeto Asociado de la base de datos por su ID.
     * <b>pre:</b> id debe ser mayor que 0.
     * <b>post:</b> se elimina el asociado de la base de datos.
     * @param id El ID del asociado a eliminar.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    public void eliminar(int id) throws SQLException{
        assert id > 0 : "El id debe ser mayor que 0";
        String query = "DELETE FROM Asociados WHERE id = ? ;";

        bd.ejecutarDelete(query, id);

    }
    /**
     * Actualiza un objeto Asociado en la base de datos.
     * <b>pre:</b> asociado no debe ser nulo.
     * <b>post:</b> se actualiza el asociado en la base de datos.
     * @param asociado El objeto Asociado a actualizar.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    public void actualizar(Asociado asociado) throws SQLException {
        assert asociado != null : "El asociado no puede ser nulo";
        String quety = "UPDATE Asociados SET nombre = ?, apellido = ?, dni = ?, calle = ?, numero = ?, ciudad = ?, telefono = ? WHERE id = ? ;";
        AsociadoDTO aDTO = CrearDTO(asociado);
        bd.ejecutarActualizacion(quety, aDTO);
    }

    /**
     * Crea un objeto AsociadoDTO a partir de un objeto Asociado.
     * <b>pre:</b> asociado no debe ser nulo.
     * <b>post:</b> se devuelve un objeto AsociadoDTO con los datos del asociado.
     * @param asociado El objeto Asociado del cual se crea el DTO.
     * @return Un objeto AsociadoDTO.
     */
    public AsociadoDTO CrearDTO(Asociado asociado) {
        assert asociado != null : "El asociado no puede ser nulo";
        AsociadoDTO aDTO = new AsociadoDTO();
        aDTO.setId(asociado.getId());
        aDTO.setNombre(asociado.getNombre());
        aDTO.setApellido(asociado.getApellido());
        aDTO.setDni(asociado.getDni());
        aDTO.setCalle(asociado.getDomicilio().getCalle());
        aDTO.setNumero(asociado.getDomicilio().getNumero());
        aDTO.setCiudad(asociado.getDomicilio().getCiudad());
        aDTO.setTelefono(asociado.getTelefono());
        return aDTO;
    }
    /**
     * Crea un objeto AsociadoDTO a partir de los datos proporcionados por la base de datos.
     * <b>pre:</b> los datos proporcionados deben ser válidos.
     * <b>post:</b> se devuelve un objeto AsociadoDTO con los datos proporcionados.
     * @param id El ID del asociado.
     * @param nombre El nombre del asociado.
     * @param apellido El apellido del asociado.
     * @param dni El DNI del asociado.
     * @param calle La calle del domicilio del asociado.
     * @param numero El número del domicilio del asociado.
     * @param ciudad La ciudad del domicilio del asociado.
     * @param telefono El teléfono del asociado.
     * @return Un objeto AsociadoDTO.
     */
    public AsociadoDTO CrearDTO(int id, String nombre, String apellido, String dni, String calle, int numero, String ciudad, String telefono) {

        AsociadoDTO aDTO = new AsociadoDTO();
        aDTO.setId(id);
        aDTO.setNombre(nombre);
        aDTO.setApellido(apellido);
        aDTO.setDni(dni);
        aDTO.setTelefono(telefono);
        aDTO.setCalle(calle);
        aDTO.setNumero(numero);
        aDTO.setCiudad(ciudad);
        return aDTO;
    }
}
