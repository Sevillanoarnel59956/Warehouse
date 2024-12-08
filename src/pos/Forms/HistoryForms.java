/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package pos.Forms;

import com.formdev.flatlaf.FlatClientProperties;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import pos.Factory.OrderInterface;
import pos.Factory.ProductsFactory;
import pos.Factory.ProductsInterface;
import pos.Model.ItemModel;
import pos.Model.OrdersModel;

/**
 *
 * @author USER
 */
public class HistoryForms extends javax.swing.JPanel {

     private ProductsFactory productsFactory = new ProductsFactory();
    private OrderInterface orderController = productsFactory.CreateOrderController();
    public HistoryForms() {
        initComponents();
         init();
        populateOrdersToTable();
       
    }
    private void init(){
          historyTable.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "height:30;"
                + "hoverBackground:null;"
                + "pressedBackground:null;"
                + "separatorColor:$TableHeader.background;"
                + "font:bold;");
        historyTable.putClientProperty(FlatClientProperties.STYLE, ""
                 + "rowHeight:60;"
                + "showHorizontalLines:true;"
                + "intercellSpacing:0,1;"
                + "cellFocusColor:$TableHeader.hoverBackground;"
                + "selectionBackground:$TableHeader.hoverBackground;"
                + "selectionForeground:$Table.foreground;");
    }
private void populateOrdersToTable() {
    // Fetch the list of orders
    List<OrdersModel> orderList = orderController.getOrderedProducts("test");

    DefaultTableModel model = (DefaultTableModel) historyTable.getModel();
    
    // Clear any existing rows in the table
    model.setRowCount(0);
    for (OrdersModel order : orderList) {
        String orderId = order.getOrdersId();
        String cashier = order.getCashier();
        double total = order.getTotal();
        double cash = order.getCash();
        double change = order.getChange();

        // Flag to track if it's the first product in the order
        boolean isFirstProduct = true;

        // Loop through the products in the order to add each one as a row
        for (ItemModel item : order.getList_of_products()) {
            // Extract product details
            String title = item.getTitle();
            String category = item.getCategory().toString();
            int qty = item.getQuantity();
            double price = item.getPrice();

            // If it's the first product, add the order details (orderId, cashier, etc.)
            if (isFirstProduct) {
                model.addRow(new Object[]{orderId, cashier, cash, change, total, title, category, qty, price});
                isFirstProduct = false; // Set the flag to false after adding the first product
            } else {
                // If it's not the first product, leave the order details blank
                model.addRow(new Object[]{"", "", "", "", "", title, category, qty, price});
            }
        }
    }
}



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        historyTable = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();

        historyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "OrdersID", "Cashier", "Cash", "Change", "Total", "Title", "Category", "Quantity", "Price"
            }
        ));
        jScrollPane1.setViewportView(historyTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1197, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable historyTable;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
