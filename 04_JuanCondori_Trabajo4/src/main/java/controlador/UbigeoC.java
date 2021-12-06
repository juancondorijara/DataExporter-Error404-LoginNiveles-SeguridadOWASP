package controlador;

import dao.UbigeoD;
import modelo.Ubigeo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import lombok.Data;

@Data

//Notación CDI
@Named(value = "ubigeoC")
//@Dependent
@SessionScoped
public class UbigeoC implements Serializable {

    private Ubigeo ubigeo;
    private UbigeoD dao;
    private List<Ubigeo> listadoUbigeo;

    public UbigeoC() {
        ubigeo = new Ubigeo();
        dao = new UbigeoD();
        listadoUbigeo = new ArrayList<>();
    }

    public void listar() {
        try {
            listadoUbigeo = dao.listarTodos();
        } catch (Exception e) {
            System.out.println("Error en ListarC " + e.getMessage());
        }
    }

    @PostConstruct
    public void construir() {
        try {
            listar();
        } catch (Exception e) {
        }
    }

}
