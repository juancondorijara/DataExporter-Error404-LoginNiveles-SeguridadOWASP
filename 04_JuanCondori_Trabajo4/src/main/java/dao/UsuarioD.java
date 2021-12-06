package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Usuario;

public class UsuarioD extends Conexion {

    public static Boolean logueo = false;
    public static int nivel = 0;

    public Usuario login(Usuario usuario) throws Exception {
        Usuario user = new Usuario();
        String sql = "select USUUSU, PWDUSU, NIVUSU from USUARIO where USUUSU=? and PWDUSU=? ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, usuario.getUSUUSU());
            ps.setString(2, usuario.getPWDUSU());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                logueo = true;
                user.setUSUUSU(rs.getString("USUUSU"));
                user.setPWDUSU(rs.getString("PWDUSU"));
                user.setNIVUSU(rs.getInt("NIVUSU"));
                nivel = rs.getInt("NIVUSU");
            } else {
                logueo = false;
            }
            ps.close();
            rs.close();
            return user;
        } catch (Exception e) {
            System.out.println("Errorr en login_D " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
}
