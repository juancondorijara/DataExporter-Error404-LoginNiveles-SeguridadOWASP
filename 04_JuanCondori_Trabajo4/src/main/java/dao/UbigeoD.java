package dao;

import java.util.List;
import modelo.Ubigeo;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UbigeoD extends Conexion {
    
    public List listarTodos() throws Exception {
        List<Ubigeo> listado = null;
        Ubigeo ubigeo;
        String sql = "select * from UBIGEO";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ubigeo = new Ubigeo();
                ubigeo.setCODUBI(rs.getString("CODUBI"));
                ubigeo.setDEPUBI(rs.getString("DEPUBI"));
                ubigeo.setPROUBI(rs.getString("PROUBI"));
                ubigeo.setDISUBI(rs.getString("DISUBI"));
                listado.add(ubigeo);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en ListarTodosD" + e.getMessage());
        } finally {
            this.cerrar();
        }
        return listado;
    }
    
}
