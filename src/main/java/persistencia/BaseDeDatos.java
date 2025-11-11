package persistencia;

import java.sql.*;
/**
 * Clase BaseDeDatos que maneja la conexión y operaciones básicas con la base de datos MySQL.
 * Contiene métodos para abrir y cerrar la conexión, así como para ejecutar consultas y actualizaciones.
 * Utiliza el driver JDBC de MySQL para interactuar con la base de datos.
 * Tiene como atributos la URL de la base de datos, el usuario y la contraseña.
 */
public class BaseDeDatos {
    private static Connection conexion;
    private static final String URL = "jdbc:mysql://localhost:3306/Grupo_7";
    private static final String USUARIO = "progra_c";
    private static final String CONTRASENA = "progra_c";

    /**
     * Constructor de la clase BaseDeDatos.
     * <b>post:</b> se crea una instancia de BaseDeDatos y se abre la conexión a la base de datos.
     */
    public BaseDeDatos() {
        conexion = null;
        cargarDriver();
        abrirConexion();
    }
    /**
     * Carga el driver JDBC de MySQL.
     * <b>post:</b> se carga el driver JDBC de MySQL.
     */
    public static void cargarDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Abre la conexión a la base de datos.
     * <b>post:</b> se abre la conexión a la base de datos.
     */
    public static void abrirConexion() {
        try {
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Cierra la conexión a la base de datos.
     * <b>post:</b> se cierra la conexión a la base de datos.
     * @throws SQLException Si ocurre un error al cerrar la conexión.
     */
    public static void cerrarConexion() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }
    /**
     * Obtiene la conexión a la base de datos.
     * @return La conexión a la base de datos.
     */
    public static Connection getConexion() {
        return conexion;
    }
    /**
     * Ejecuta una consulta SQL y devuelve el resultado.
     * <b>pre:</b> la consulta debe ser una sentencia SQL válida.
     * <b>post:</b> se devuelve el resultado de la consulta.
     * @param consulta La consulta SQL a ejecutar.
     * @return El resultado de la consulta.
     * @throws SQLException Si ocurre un error al ejecutar la consulta.
     */
    public ResultSet ejecutarConsulta(String consulta, int id) throws SQLException{
        ResultSet rs = null;
        PreparedStatement stmt = conexion.prepareStatement(consulta);
        stmt.setInt(1, id);
        rs = stmt.executeQuery(consulta);
        return rs;
    }

    /**
     * Ejecuta una actualización SQL (UPDATE).
     * <b>pre:</b> la actualización debe ser una sentencia SQL válida.
     * <b>post:</b> se ejecuta la actualización en la base de datos.
     * @param actualizacion La actualización SQL a ejecutar.
     * @throws SQLException Si ocurre un error al ejecutar la actualización.
     */
    public void ejecutarActualizacion(String actualizacion, AsociadoDTO aDTO) throws SQLException{
        PreparedStatement stmt = conexion.prepareStatement(actualizacion);
        stmt.setInt(1, aDTO.getId());
        stmt.setString(2, aDTO.getNombre());
        stmt.setString(3, aDTO.getApellido());
        stmt.setString(4, aDTO.getDni());
        stmt.setString(5, aDTO.getCalle());
        stmt.setInt(6, aDTO.getNumero());
        stmt.setString(7, aDTO.getCiudad());
        stmt.setString(8, aDTO.getTelefono());
        stmt.executeUpdate(actualizacion);
    }

    /**
     * Ejecuta una sentencia INSERT SQL.
     * <b>pre:</b> la sentencia debe ser una sentencia SQL válida.
     * <b>post:</b> se ejecuta la inserción en la base de datos.
     * @param insert La sentencia INSERT SQL a ejecutar.
     * @throws SQLException Si ocurre un error al ejecutar la inserción.
     */
    public void ejecutarInsert(String insert, AsociadoDTO aDTO)throws SQLException {
        PreparedStatement stmt = conexion.prepareStatement(insert);
        stmt.setInt(1, aDTO.getId());
        stmt.setString(2, aDTO.getNombre());
        stmt.setString(3, aDTO.getApellido());
        stmt.setString(4, aDTO.getDni());
        stmt.setString(5, aDTO.getCalle());
        stmt.setInt(6, aDTO.getNumero());
        stmt.setString(7, aDTO.getCiudad());
        stmt.setString(8, aDTO.getTelefono());
        stmt.executeUpdate(insert);

    }
    /**
     * Ejecuta una sentencia DELETE SQL.
     * <b>pre:</b> la sentencia debe ser una sentencia SQL válida.
     * <b>post:</b> se ejecuta la eliminación en la base de datos.
     * @param delete La sentencia DELETE SQL a ejecutar.
     * @throws SQLException Si ocurre un error al ejecutar la eliminación.
     */
    public void ejecutarDelete(String delete, int id) throws SQLException{

        PreparedStatement stmt = conexion.prepareStatement(delete);
        stmt.setInt(1, id);
        stmt.executeUpdate(delete);

    }

    /**
     * Inicializa la base de datos creando la tabla Asociados y llenándola con datos de ejemplo.
     * <b>post:</b> se crea la tabla Asociados y se insertan datos de ejemplo.
     */
    public void IniciarBD(){
        String query = "DROP TABLE IF EXISTS Asociados;";
        try{
            Statement stmt = conexion.createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        query = "CREATE TABLE IF NOT EXISTS Asociados (int id PRIMARY KEY, VARCHAR(30) nombre,VARCHAR(30) apellido, VARCHAR(10) DNI UNIQUE, VARCHAR(30) calle,int numero, VARCHAR(35) ciudad, VARCHAR(15) telefono);";
        try {
            Statement stmt = conexion.createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        query = "INSERT INTO Asociados (id, nombre, apellido, dni, domicilio, telefono) VALUES (1, 'Juan', 'State', '12345678', 'Buenos Aires', 123, 'Mar del Plata', '2235551234'), (2, 'Maria', 'Template', '87654321', 'Libertad',742, 'Mar del Plata', '2235555678'), (3, 'Carlos', 'Observer', '11223344', 'Colon', 456, 'Mar del Plata', '2235558765'), (4, 'Jose', 'Concurrencia', '21842190', 'Juan B Justo,'200,' Mar del Plata', '1551232349'), (5, 'Xin', 'Dao', '33445566', 'Corrientes', 789,'Mar del Plata', '2235554321'), (6, 'Ana', 'Facade', '66554433', 'San Martin', 321, 'Mar del Plata', '2235556789');";

        try{
            Statement stmt = conexion.createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
