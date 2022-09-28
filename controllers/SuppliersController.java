package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import static models.EmployeesDao.rol_user;
import models.Suppliers;
import models.SuppliersDao;
import views.SystemView;


public class SuppliersController implements ActionListener{
  private Suppliers supplier;
  private SuppliersDao suppliersDao;
  private SystemView views;
  String rol = rol_user;

    public SuppliersController(Suppliers suplier, SuppliersDao suplierDao, SystemView views) {
        this.supplier = suplier;
        this.suppliersDao = suplierDao;
        this.views = views;
        //boton de registro proveedores
        this.views.btn_register_suppliers.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== views.btn_register_suppliers){
            if(views.txt_suppliers_name.getText().equals("")
                || views.txt_suppliers_description.getText().equals("")
                || views.txt_suppliers_address.getText().equals("")
                || views.txt_suppliers_telephone.getText().equals("")
                || views.txt_suppliers_email.getText().equals("")
                || views.cmb_suppliers_city.getSelectedItem().equals("")){
                
                JOptionPane.showMessageDialog(null, "todos los campos son obligatorios");
         
            }else{
                //Realizar insersion
                supplier.setName(views.txt_suppliers_name.getText().trim());
                supplier.setDescription(views.txt_suppliers_description.getText().trim());
                supplier.setAddress(views.txt_suppliers_address.getText().trim());
                supplier.setTelephone(views.txt_suppliers_telephone.getText().trim());
                supplier.setEmail(views.txt_suppliers_email.getText().trim());
                supplier.setCity(views.cmb_suppliers_city.getSelectedItem().toString());
                
                if(suppliersDao.registerSupplierQuery(supplier)){
                  JOptionPane.showMessageDialog(null, "Proveedor registrado con exito");  
                }else{
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error al registrar el proveedor");
                }    
            }
            
            
                    
            
        }
    }
  
  
}
