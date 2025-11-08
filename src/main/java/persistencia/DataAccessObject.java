package persistencia;

public class DataAccessObject {

    public DataAccessObject() {

    }

    public void guardar(Object obj) {
        // TODO Implementar lógica para guardar el objeto en la base de datos
    }

    public Object cargar(int id) {
        // TODO Implementar lógica para cargar el objeto desde la base de datos usando su ID
        return null;
    }

    public void eliminar(int id) {
        // TODO Implementar lógica para eliminar el objeto de la base de datos usando su ID
    }

    public void actualizar(Object obj) {
        // TODO Implementar lógica para actualizar el objeto en la base de datos
    }

    public PersonaDTO CrearDTO(int id) {
        // TODO Implementar lógica para crear y devolver un DataTransferObject
        return new PersonaDTO();
    }
}
