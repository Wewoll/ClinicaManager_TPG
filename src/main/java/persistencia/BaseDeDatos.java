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
            //TODO : levantar base de datos (persistencia)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                //TODO : grabado de la base de datos (persistencia)
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ResultSet ejecutarConsulta(String consulta) {

        ResultSet rs = null;
        try {
            Statement stmt = conexion.createStatement();
            stmt.executeQuery(consulta);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
