package controlador;

import dao.UsuarioD;
import modelo.Usuario;
import java.io.IOException;
import java.io.Serializable;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.enterprise.context.SessionScoped;
import lombok.Data;

@Data

//Notación CDI
@Named(value = "usuarioC")
//@Dependent
@SessionScoped
public class UsuarioC implements Serializable {

    private Usuario usuario;
    private UsuarioD dao;
    private int captcha = 0;
    private int intentos = 0;
    private boolean bloquear = false;

    public UsuarioC() {
        usuario = new Usuario();
        dao = new UsuarioD();
    }

    public void login() throws Exception {
        try {
            dao.login(usuario);
        } catch (Exception e) {
            System.out.println("Error en login_C " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void iniciarSesion() throws Exception {
        try {
            usuario = dao.login(usuario);
            this.login();
            if (dao.logueo == true) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("objetoUsuario", usuario);
                if (dao.nivel == 1) {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/04_JuanCondori_Trabajo4/faces/vistas/Usuario1.xhtml");
                }
                if (dao.nivel == 2) {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/04_JuanCondori_Trabajo4/faces/vistas/Usuario2.xhtml");
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Usuario/Contraseña Incorrecto"));
                System.out.println("Usuario/Contraseña Incorrecto");
            }
        } catch (Exception e) {
            System.out.println("Error en iniciarSesion_C " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void limpiar() {
        usuario = new Usuario();
    }

//    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("objetoUsuario", usuario);
    // Obtener el objeto de la sesión activa
    public static Usuario obtenerObjetoSesion() {
        return (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("objetoUsuario");
    }

    // Si la sesión no está iniciada no permitirá entrar a otra vista de la aplicación y por tanto llevara al Login
    public void seguridadSesion() throws IOException {
        if (obtenerObjetoSesion() == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/04_JuanCondori_Trabajo4/faces/Login.xhtml");
        }
    }

    // Cerrar y limpiar la sesión y direccionar al xhtml inicial del proyecto
    public void cerrarSesion() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/04_JuanCondori_Trabajo4/faces/Login.xhtml");
    }

}
