package controlador;

import dao.ClienteImpl;
import dao.UsuarioD;
import modelo.Cliente;
import modelo.Usuario;
import modelo.Ubigeo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.component.export.ExcelOptions;
import org.primefaces.component.export.PDFOptions;
import org.primefaces.component.export.PDFOrientationType;
import services.CamelCaseS;
import services.ReniecS;
import lombok.Data;

@Data

//Notación CDI
@Named(value = "clienteC")
//@Dependent
@SessionScoped
public class ClienteC implements Serializable {

    private Cliente cliente;
    private ClienteImpl dao;
    private List<Cliente> listadoCliente;
    private Ubigeo ubigeo;
    private int tipo = 1;
    private ExcelOptions xls;
    private PDFOptions pdf;
    private Usuario usuario;
    private UsuarioD daousu;

    public ClienteC() {
        cliente = new Cliente();
        dao = new ClienteImpl();
        listadoCliente = new ArrayList<>();
        ubigeo = new Ubigeo();
        usuario = new Usuario();
        daousu = new UsuarioD();
        customizationOptions();
    }

    public void buscarDNI() throws Exception {
        try {
            ReniecS.buscarDNI(cliente);
            Logger.getGlobal().log(Level.WARNING, cliente.getNOMCLI());
            Logger.getGlobal().log(Level.WARNING, cliente.getAPECLI());
        } catch (Exception e) {
            System.out.println("Error en buscarDNI_C " + e.getMessage());
        }
    }
    
    public void registrar() throws Exception {
        try {
            if (dao.duplicadoDNI(cliente, listadoCliente) == false) {
                cliente.setNOMCLI(CamelCaseS.camelMayuscula(cliente.getNOMCLI()));
                cliente.setAPECLI(CamelCaseS.camelMayuscula(cliente.getAPECLI()));
                cliente.setDIRCLI(CamelCaseS.camelMayuscula(cliente.getDIRCLI()));
                cliente.setEMACLI(CamelCaseS.camelMinuscula(cliente.getEMACLI()));
                dao.registrar(cliente);
                PrimeFaces.current().ajax().update("form:dlgDatos");
                limpiar();
                listar();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CORRECTO", "Registrado con Éxito"));
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "REVISE SU CORREO", "Se Envio su Contraseña"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "DUPLICADO", "El DNI ya Existe"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Al Registrar"));
            System.out.println("Error en RegistrarC " + e.getMessage());
        }
        cliente = new Cliente();
        listar();
    }

    public void modificar() throws Exception {
        try {
            cliente.setNOMCLI(CamelCaseS.camelMayuscula(cliente.getNOMCLI()));
            cliente.setAPECLI(CamelCaseS.camelMayuscula(cliente.getAPECLI()));
            cliente.setDIRCLI(CamelCaseS.camelMayuscula(cliente.getDIRCLI()));
            cliente.setEMACLI(CamelCaseS.camelMinuscula(cliente.getEMACLI()));
            dao.modificar(cliente);
            PrimeFaces.current().ajax().update("form3:dlgDatos");
            limpiar();
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CORRECTO", "Modificado con Éxito"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Al Modificar"));
            System.out.println("Error en ModificarC " + e.getMessage());
        }
        cliente = new Cliente();
        listar();
    }

    public void eliminarEstado() throws Exception {
        try {
            dao.eliminarEstado(cliente);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "CORRECTO", "Eliminado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en eliminarEstadoC " + e.getMessage());
        }
    }

    public void restaurarEstado() throws Exception {
        try {
            dao.restaurarEstado(cliente);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CORRECTO", "Restaurado con Exito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en restaurarEstadoC " + e.getMessage());
        }
    }

    public void limpiar() {
        cliente = new Cliente();
    }

    public void listar() {
        try {
            listadoCliente = dao.listarTodos(tipo);
        } catch (Exception e) {
            System.out.println("Error en ListarC " + e.getMessage());
        }
    }
    
    public void customizationOptions() {
        xls = new ExcelOptions();
        xls.setFacetBgColor("#19C7FF");
        xls.setFacetFontSize("10");
        xls.setFacetFontColor("#FFFFFF");
        xls.setFacetFontStyle("BOLD");
        xls.setCellFontColor("#000000");
        xls.setCellFontSize("8");
        xls.setFontName("Verdana");

        pdf = new PDFOptions();
        pdf.setFacetBgColor("#19C7FF");
        pdf.setFacetFontColor("#000000");
        pdf.setFacetFontStyle("BOLD");
        pdf.setCellFontSize("12");
        pdf.setFontName("Courier");
        pdf.setOrientation(PDFOrientationType.LANDSCAPE);
    }

    @PostConstruct
    public void construir() {
        listar();
    }

}
