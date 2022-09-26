package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;


public class ProductsDao {
    //Instanciar conexión
    ConnectionMySQL cn = new ConnectionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    //registrar Producto
        public boolean registerProductQuery(Products product){
        String query = "INSERT INTO products (code, name, description, unit_price, created, updated, category_id)"
                + "VALUES (?,?,?,?,?,?,?)";
        
        Timestamp datetime = new Timestamp (new Date().getTime());
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, product.getCode());
            pst.setString(2, product.getName());
            pst.setString(3, product.getDescription());
            pst.setDouble(4, product.getUnit_price());
            pst.setTimestamp(5, datetime);
            pst.setTimestamp(6, datetime);
            pst.setInt(7, product.getCategory_id());
            pst.execute();
            return true;
            
                    
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "error al registrar producto");
            return false;
        
        }
        
    } 
            
    //Listar producto
        public List listProductQuery(String value){
        List<Products> list_products = new ArrayList(); 
        String query = "SELECT pro.*, ca.name AS category_name FROM products pro, categories ca WHERE pro.category_id = ca.id";
        String query_search_customer = "SELECT pro.*, ca.name AS category_name FROM products pro INNER JOIN categories ca" + "ON pro.category_id = ca.id WHERE pro.name LIKE '%" + value + "%'";
        try{
            conn = cn.getConnection();
            if (value.equalsIgnoreCase("")) { //Si no ingresa nada ejecuta esto, sino el else
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
        } else {
            pst = conn.prepareStatement(query_search_customer);
            rs = pst.executeQuery();
        }
        while (rs.next()) {
            Products product = new Products();
            product.setId(rs.getInt("id"));
            product.setCode(rs.getInt("code"));
            product.setName(rs.getString("name"));
            product.setUnit_price(rs.getDouble("unit_price"));
            product.setProduct_quantity(rs.getInt("product_quatity"));
            product.setCategory_name(rs.getString("category_name"));
            list_products.add(product);
            }
        
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());                         
        }
        return list_products;
    }
         
    //Modificar producto  
        public boolean updateProductQuery(Products product){
        String query = "UPDATE products SET code = ?, name = ?, description = ?, unit_price = ?, updated = ?," + "category_id = ? WHERE id = ?";     
        Timestamp datetime = new Timestamp (new Date().getTime());
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, product.getCode());
            pst.setString(2, product.getName());
            pst.setString(3, product.getDescription());
            pst.setDouble(4, product.getUnit_price());
            pst.setTimestamp(5, datetime);
            pst.setInt(6, product.getCategory_id());
            pst.setInt(7, product.getId());
            pst.execute();
            return true;
            
                    
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "error al modificar producto");
            return false;
        
        }
    } 
        
    //Eliminar producto
        public boolean deleteProductQuery(int id){
        String query = "DELETE FROM products WHERE id " + id;
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.execute();
            return true;            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No puede eliminar el producto");
            return false;
        
        }
    }
        
    //Buscar producto
        public Products searchProductQuery(int id){
        String query = "SELECT pro.*, ca.name AS category_name FROM products pro " + "INNER JOIN categories ca ON pro.category_id = ca.id WHERE pro.id = ?";   
        Products product = new Products();
        try{
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
          
            if(rs.next()){
                product.setId(rs.getInt("id"));
                product.setCode(rs.getInt("code"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setUnit_price(rs.getDouble("unit_price"));
                product.setCategory_name(rs.getString("category_name"));
            }     
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return product;
    }
              
}
