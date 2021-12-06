package services;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
//import javax.swing.JOptionPane;
import modelo.Cliente;

public class ReniecS {
    
    public static void main(String[] args) throws Exception {
        Cliente cliente = new Cliente();
        cliente.setDNICLI("70335061");
        buscarDNI(cliente);
    }

    public static void buscarDNI(Cliente cliente) throws Exception {
        String leerdni = cliente.getDNICLI();
        String token = "?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Imp1bGlvLnF1aXNwZUB2YWxsZWdyYW5kZS5lZHUucGUifQ.6M-P2QMMvKFZEeMvTUXvkOooM02N_pWqt0OdlaYW3PM";
        String enlace = "https://dniruc.apisperu.com/api/v1/dni/" + leerdni + token;
        try {
            URL url = new URL(enlace);
            URLConnection request = url.openConnection();
            request.connect();
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            if (root.isJsonObject()) {
                JsonObject rootobj = root.getAsJsonObject();
                String nombres = rootobj.get("nombres").getAsString();
                String apellido_paterno = rootobj.get("apellidoPaterno").getAsString();
                String apellido_materno = rootobj.get("apellidoMaterno").getAsString();
                cliente.setNOMCLI(CamelCaseS.camelMayuscula(nombres));
                cliente.setAPECLI(CamelCaseS.camelMayuscula(apellido_paterno + " " + apellido_materno));
                cliente.setDIRCLI("");
                cliente.setEMACLI("");
                cliente.setCELCLI("");
                cliente.setCODUBI("Seleccione");
                System.out.println("RESULTADO:\n");
                System.out.println(nombres + "\n" + apellido_paterno + "\n" + apellido_materno + "\n");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BUSQUEDA", "DNI Exitoso"));
            } else {
                cliente.setNOMCLI("");
                cliente.setAPECLI("");
                cliente.setDIRCLI("");
                cliente.setEMACLI("");
                cliente.setCELCLI("");
                cliente.setCODUBI("Seleccione");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "DNI no Encontrado o el servidor falló"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "DNI no Encontrado o el servidor falló"));
            System.out.println("Error en buscarDNI_S " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}
