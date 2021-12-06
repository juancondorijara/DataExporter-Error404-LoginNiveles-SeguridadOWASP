package validator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import dao.Conexion;

@FacesValidator(value = "emailV")
public class EmailV extends Conexion implements Validator {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    public static boolean duplicadoEmail(String email) {
        try {
            PreparedStatement ps = Conexion.conectar().prepareStatement("SELECT EMACLI FROM CLIENTE WHERE EMACLI = '" + email + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
            ps.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Error en duplicadoEmail " + e.getMessage());
        }
        return false;
    }
    
    @Override
    public void validate(FacesContext arg0, UIComponent arg1, Object value) throws ValidatorException {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        String mail = ((String) value).trim();
        if (mail.isEmpty()) {  
        } else {
            Matcher matcher = pattern.matcher(mail);
            if (!matcher.matches()) {
                FacesMessage msg = new FacesMessage("Email Incorrecto");
                throw new ValidatorException(msg);
            }
        }
        String email = (String) value;
        if (duplicadoEmail(email) == true) {
            throw new ValidatorException(new FacesMessage("Duplicado, el EMAIL ya existe"));
        }
    }
}
