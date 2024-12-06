/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos.Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import pos.Database.GetDatabaseConnection;
import pos.Factory.OrderInterface;
import pos.Model.ItemModel;
import pos.Model.OrdersModel;

public class OrdersController implements OrderInterface{
 private PreparedStatement ps;
    private ResultSet rs;
    GetDatabaseConnection databaseConnection;
    public OrdersController() {
         databaseConnection = new GetDatabaseConnection();
    }

    @Override
    public void createOrder(OrdersModel orders) {
        try {
           String sql = "INSERT INTO orders_table (OrdersID, Cashier, ProductID, Title, Category, Price, Cash, `Change`, Total) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
           ps = databaseConnection.prepareStatement(sql);
           
            for (ItemModel product : orders.getList_of_products()) {
                ps.setString(1, orders.getOrdersId());
                ps.setString(2, orders.getCashier());
                ps.setString(3, product.getItemId());
                ps.setString(4, product.getTitle());
                ps.setString(5, product.getCategory().toString());
                ps.setDouble(6, product.getPrice());
                ps.setDouble(7, orders.getCash());
                ps.setDouble(8, orders.getChange());
                ps.setDouble(9, orders.getTotal());
                
                ps.addBatch();
                
                
            }
           
           ps.executeBatch();
            JOptionPane.showMessageDialog(null, "Succesfully Batching");
           
           
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        // Close resources
        if (ps != null) {
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    }
    
}
