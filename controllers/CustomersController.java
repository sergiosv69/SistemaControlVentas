package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Customers;
import models.CustomersDao;
import views.SystemView;

public class CustomersController implements ActionListener, MouseListener, KeyListener {

    private Customers customer;
    private CustomersDao customerDao;
    private SystemView views;

    DefaultTableModel model = new DefaultTableModel();

    public CustomersController(Customers customer, CustomersDao customerDao, SystemView views) {
        this.customer = customer;
        this.customerDao = customerDao;
        this.views = views;

        //Boton de registrar clientes
        this.views.btn_register_customer.addActionListener(this);
        //BUSCADOR
        this.views.txt_search_customer.addKeyListener(this);
        this.views.customers_table.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btn_register_customer) {
            //Verificamos si los campos estan vacios
            if (views.txt_customer_id.getText().equals("")
                    || views.txt_customer_fullname.getText().equals("")
                    || views.txt_customer_address.getText().equals("")
                    || views.txt_customer_telephone.getText().equals("")
                    || views.txt_customer_email.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
            } else {
                customer.setId(Integer.parseInt(views.txt_customer_id.getText().trim()));
                customer.setFull_name(views.txt_customer_fullname.getText().trim());
                customer.setAddress(views.txt_customer_address.getText().trim());
                customer.setTelephone(views.txt_customer_telephone.getText().trim());
                customer.setEmail(views.txt_customer_email.getText().trim());

                if (customerDao.registerCustomerQuery(customer)) {
                    cleanTable();
                    listAllCustomers();
                    JOptionPane.showMessageDialog(null, "Cliente registrado con éxito");
                } else {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al registrar al cliente");
                }
            }
        }
    }

    //Listar Clientes
    public void listAllCustomers() {
        List<Customers> list = customerDao.listCustomerQuery(views.txt_search_customer.getText());
        model = (DefaultTableModel) views.customers_table.getModel();

        Object[] row = new Object[5];

        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getFull_name();
            row[2] = list.get(i).getAddress();
            row[3] = list.get(i).getTelephone();
            row[4] = list.get(i).getEmail();
            model.addRow(row);
        }
        views.customers_table.setModel(model);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.customers_table) {
            int row = views.customers_table.rowAtPoint(e.getPoint());
            views.txt_customer_id.setText(views.customers_table.getValueAt(row, 0).toString());
            views.txt_customer_fullname.setText(views.customers_table.getValueAt(row, 1).toString());
            views.txt_customer_address.setText(views.customers_table.getValueAt(row, 2).toString());
            views.txt_customer_telephone.setText(views.customers_table.getValueAt(row, 3).toString());
            views.txt_customer_email.setText(views.customers_table.getValueAt(row, 4).toString());
            //Deshabilitar BOTONES
            views.btn_register_customer.setEnabled(false);
            views.txt_customer_id.setEditable(false);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == views.txt_search_customer) {
            //Limpiar Tabla
            cleanTable();
            //Listar Clientes
            listAllCustomers();
        }
    }

    public void cleanTable() {
        for (int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }
}
