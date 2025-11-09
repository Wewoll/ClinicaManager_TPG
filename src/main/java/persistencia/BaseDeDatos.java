package persistencia;

import java.sql.*;

public class BaseDeDatos {
    private static Connection conexion;
    private static final String URL = "jdbc:mysql://localhost:3306/mi_base_de_datos";
    private static final String USUARIO = "mi_usuario";
    private static final String CONTRASENA = "mi_contrasena";

    public BaseDeDatos() {
        conexion = null;
        cargarDriver();
        abrirConexion();
    }

    public static void cargarDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void abrirConexion() {
        try {
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void cerrarConexion() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }
    public static Connection getConexion() {
        return conexion;
    }

    public ResultSet ejecutarConsulta(String consulta, int id) throws SQLException{
        ResultSet rs = null;
        PreparedStatement stmt = conexion.prepareStatement(consulta);
        if (id != -1)
            stmt.setInt(1, id);
        rs = stmt.executeQuery(consulta);
        return rs;
    }
    public void ejecutarActualizacion(String actualizacion, AsociadoDTO aDTO) throws SQLException{
        PreparedStatement stmt = conexion.prepareStatement(actualizacion);
        stmt.setInt(1, aDTO.getId());
        stmt.setString(2, aDTO.getNombre());
        stmt.setString(3, aDTO.getApellido());
        stmt.setString(4, aDTO.getDni());
        stmt.setObject(5, aDTO.getDomicilio());
        stmt.setString(6, aDTO.getTelefono());
        stmt.executeUpdate(actualizacion);
    }

    public void ejecutarInsert(String insert, AsociadoDTO aDTO)throws SQLException {
        PreparedStatement stmt = conexion.prepareStatement(insert);
        stmt.setInt(1, aDTO.getId());
        stmt.setString(2, aDTO.getNombre());
        stmt.setString(3, aDTO.getApellido());
        stmt.setString(4, aDTO.getDni());
        stmt.setObject(5, aDTO.getDomicilio());
        stmt.setString(6, aDTO.getTelefono());
        stmt.executeUpdate(insert);

    }

    public void ejecutarDelete(String delete, int id) throws SQLException{

        PreparedStatement stmt = conexion.prepareStatement(delete);
        stmt.setInt(1, id);
        stmt.executeUpdate(delete);

    }

}
