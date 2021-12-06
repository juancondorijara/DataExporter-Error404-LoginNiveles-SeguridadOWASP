package dao;

// Es la implementación de métodos abstractos de diversas familias
import java.util.List;

public interface ICRUD<Generic> {

    void registrar(Generic obj) throws Exception;

    void modificar(Generic obj) throws Exception;

    void eliminarEstado(Generic obj) throws Exception;
    
    void restaurarEstado(Generic obj) throws Exception;

    List<Generic> listarTodos(int tipo) throws Exception;
    
}
