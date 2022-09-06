
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CustomersDao {
      //Instanciar conexión
    ConnectionMySQL cn = new ConnectionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
      //registrar cliente
    public boolean registerCustomerQuery(Customers customer){
        String query = "INSERT INTO customers (id, full_name, address, telephone, email, created, updated)"
                + "VALUES (?,?,?,?,?,?,?,?)";
        
        Timestamp datetime = new Timestamp (new Date().getTime());
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, customer.getId());
            pst.setString(2, customer.getFull_name());
            pst.setString(3, customer.getAddress());
            pst.setString(4, customer.getTelephone());
            pst.setString(5, customer.getEmail());
            pst.setTimestamp(6, datetime);
            pst.setTimestamp(7, datetime);
            pst.execute();
            return true;
            
                    
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "error al registrar cliente");
            return false;
        
        }
        
    }
}
