package validator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import dao.Conexion;
//https://www.youtube.com/watch?v=UEbgJ2CrYJE

@FacesValidator(value = "dniV")
public class DniV extends Conexion implements Validator {

    public static boolean duplicadoDNI(String dni) {
        try {
            PreparedStatement ps = Conexion.conectar().prepareStatement("SELECT DNICLI FROM CLIENTE WHERE DNICLI = '" + dni + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
            ps.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Error en duplicadoDNI " + e.getMessage());
        }
        return false;
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String numero = value.toString().trim();
        if (numero.length() != 0 && numero.length() < 8) {
            String formato = "^d\\d\\d\\d\\d\\d\\d\\d$";
            boolean val = Pattern.matches(formato, numero);
            if (!val) {
                throw new ValidatorException(new FacesMessage("El formato es ########"));
            }
        }
        String dni = (String) value;
        if (duplicadoDNI(dni) == true) {
            throw new ValidatorException(new FacesMessage("Duplicado, el DNI ya existe"));
        }
    }
}
