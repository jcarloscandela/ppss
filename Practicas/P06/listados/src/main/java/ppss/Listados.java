package ppss;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Listados {
    public String porApellidos(Connection con, String tableName) throws SQLException {
        Statement stm = con.createStatement();
        //realizamos la consulta y almacenamos el resultado en un ResultSet
        ResultSet rs = stm.executeQuery("SELECT apellido1, apellido2, nombre FROM " + tableName);
        String result = "";
        //recorremos el ResulSet
        while (rs.next()) {
            String ap1 = rs.getString("apellido1");
            String ap2 = rs.getString("apellido2");
            String nom= rs.getString("nombre");
            result += ap1 + ", " + ap2 + ", " + nom + "\n";
        }
        return result;
    }
}
