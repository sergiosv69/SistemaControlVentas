package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import javax.swing.JOptionPane;

public class SuppliersDao {

    //Instanciar la conexion
    ConnectionMySQL cn = new ConnectionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    //Registrar proveedor
    public boolean registerSupplierQuery(Suppliers supplier) {
        String query = "INSET INTO suppliers (name, description, address, telephone, email, city, created, updated)"
                + "VALUES (?,?,?,?,?,?,?,?)";

        Timestamp datetime = new Timestamp(new Date().getTime());

        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setString(1, supplier.getName());
            pst.setString(2, supplier.getDescription());
            pst.setString(3, supplier.getAddress());
            pst.setString(4, supplier.getTelephone());
            pst.setString(5, supplier.getEmail());
            pst.setString(6, supplier.getCity());
            pst.setTimestamp(7, datetime);
            pst.setTimestamp(7, datetime);
            pst.execute();  //ejecutamos la consulta
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar proveedor");
            return false;
        }

    }

}
