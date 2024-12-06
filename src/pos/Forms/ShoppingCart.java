
package pos.Forms;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import pos.Categories.Categories;
import pos.Event.EventCart;
import pos.Event.EventItem;
import pos.Factory.OrderInterface;
import pos.Factory.ProductsFactory;
import pos.Factory.ProductsInterface;
import pos.Item.Cart;
import pos.Item.PosItem;
import pos.Model.FileImageModel;
import pos.Model.ItemModel;
import pos.Model.OrdersModel;
import pos.PopUpFoms.CartForm;
import pos.PopUpFoms.ItemForm;
import raven.modal.ModalDialog;
import raven.modal.Toast;
import raven.modal.component.SimpleModalBorder;
import raven.modal.demo.layout.ResponsiveLayout;
import raven.modal.demo.simple.SimpleMessageModal;
import raven.modal.listener.ModalCallback;
import raven.modal.listener.ModalController;
import raven.modal.option.Location;
import raven.modal.option.Option;


public class ShoppingCart extends javax.swing.JPanel {


    private EventItem event;
    private EventCart eventCart;
    private List<ItemModel>cart = new ArrayList<>();
    private double total;
    private double change;
    private ProductsFactory productsFactory = new ProductsFactory();
    private ProductsInterface productsController = productsFactory.CreateProductsController();
    private OrderInterface orderController = productsFactory.CreateOrderController();
    public ShoppingCart() throws SQLException {
        initComponents();        
        init();

    }
    private void init() throws SQLException{
        searchField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search");
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(20);
               jScrollPane2.getVerticalScrollBar().setUnitIncrement(20);
        ResponsiveLayout listItemLayout = new ResponsiveLayout(ResponsiveLayout.JustifyContent.FIT_CONTENT, 
                new Dimension(-1, -1), 2, 2);
        ResponsiveLayout cartlistLayout = new ResponsiveLayout(ResponsiveLayout.JustifyContent.FIT_CONTENT, 
                new Dimension(-1, -1), 2, 2);
       
        cartlistLayout.setColumn(1);
        cartlistLayout.setSize(new Dimension(250, 150));
           for (Categories category : Categories.values()) {
            categoriesCb.addItem(category.toString());
        }
       testData(searchField.getText(),(String)categoriesCb.getSelectedItem(),(String)sortedBy.getSelectedItem());
        System.out.println((String)sortedBy.getSelectedItem());
        listItemLayout.setColumn(3); 
        listItemLayout.setSize(new Dimension(320, 200));
        listItemLayout.setHorizontalGap(7);
        listItemLayout.setVerticalGap(7);
        listItem.setLayout(listItemLayout);
      
         
        cartItems.setLayout(cartlistLayout);
      
    }
    
    public void addItems(ItemModel data){
        PosItem item = new PosItem();
        item.setData(data);
        
        item.getUpdateBtn().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Update");
                UpdateProducts(data);
            }
               
        });
        
         item.getOrderBtn().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Order");
                 if (data.getStatus().equals("Unavailable")) {
                   System.out.println("this product is Unavailable");
                    Toast.show(jPanel3, Toast.Type.INFO, "This product is Unavailable!");
               }else{
                   addtoCart(data);
               }
            }
        
        
        });
        item.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    event.ItemClick(item, data);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
               item.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
             item.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
            
            
  
        });
        listItem.add(item);
        repaint();
        revalidate();  
    }
    
    public void addToCart(ItemModel data){
        Cart cartshop = new Cart();
        cartshop.setData(data);
         int availableStocks = data.getAvailableStocks();
        
       cartshop.getaddBtn(data).addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        int index = cart.indexOf(data);
        if (index != -1) {
            
            ItemModel currentItem = cart.get(index);
           
                int newQty = currentItem.getQuantity() + 1;
            if (newQty > availableStocks) {
                System.out.println("Exceeds the limit");
            } else {
                currentItem.setQuantity(newQty);
                cartshop.setQtyData(newQty);
                cart.set(index, currentItem);
}

        }

        // Recalculate totals and changes
        calculateTotal();
        getCalculatedChange();

      
    }
});
    
        cartshop.getminusBtn(data).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               int index = cart.indexOf(data);
                if (index != -1) {
                // Decrement the quantity, but prevent it from going below 1
                ItemModel currentItem = cart.get(index);
                int currentQty = currentItem.getQuantity();
                if (currentQty > 1) {
                    int newQty = currentQty - 1;
                    currentItem.setQuantity(newQty);
                    
                    // Update the display quantity
                    cartshop.setQtyData(newQty);
                    calculateTotal();
                      getCalculatedChange();
                    // Update cart list
                    cart.set(index, currentItem);
                }
            }
                        for (int i = 0; i < cart.size(); i++) {
                            ItemModel item = cart.get(i);
                            // Print index and item
                            System.out.println("Index: " + i + " - Item: " + item);
                        }
            }
  
        });
        
        cartshop.deleteIndexBtn().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = cart.indexOf(data);
        
        if (index != -1) {
          
            cart.remove(index);
     
            cartItems.remove(cartshop);
         
            repaint();
            revalidate();
        }
            calculateTotal();
            getCalculatedChange();
            }
       
        });
        cartshop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               eventCart.ItemClick(cartItems, data);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
              cartshop.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
              cartshop.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

        });
        cartItems.add(cartshop);
        repaint();
        revalidate();
    }
    
    private double calculateChange(){
         total = 0.00; 
         change = 0.00;
    for (ItemModel list : cart) {
        double totalData = list.getPrice() * list.getQuantity();
        total += totalData;  // Accumulate the total
        
        int cashData = Integer.parseInt(txtCash.getText());
        int totalChange = cashData -=total;
        
        change = totalChange;
        
    }
      setChange(change);
      return change;
    }
    private void getCalculatedChange(){
        try {
            String changeData = df.format(calculateChange());
        
        lbChange.setText(changeData);
        } catch (NumberFormatException e) {
            
        }     
    }
    private static final DecimalFormat df = new DecimalFormat("00.00");
  private void calculateTotal() {
    total = 0;  // Reset total before recalculating
    for (ItemModel list : cart) {
        double totalData = list.getPrice() * list.getQuantity();
        total += totalData;  // Accumulate the total
    }
    
    setTotal(total);  // Set the total
}

       public EventItem getEvent() {
        return event;
    }

    public void setEvent(EventItem event) {
        this.event = event;
    }
    
       public EventCart getEventCart() {
        return eventCart;
    }

    public void setEventCart(EventCart eventCart) {
        this.eventCart = eventCart;
    }
      public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
        String totalData = df.format(getTotal());
        lbTotal.setText(totalData);
    }
    public double getChange() {
        return change;
    }
    public void setChange(double change) {
        this.change = change;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        cartItems = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cashierCb = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCash = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbChange = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        orderIdLbl = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listItem = new javax.swing.JPanel();
        categoriesCb = new javax.swing.JComboBox<>();
        searchField = new javax.swing.JTextField();
        sortedBy = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        cartItems.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout cartItemsLayout = new javax.swing.GroupLayout(cartItems);
        cartItems.setLayout(cartItemsLayout);
        cartItemsLayout.setHorizontalGroup(
            cartItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 261, Short.MAX_VALUE)
        );
        cartItemsLayout.setVerticalGroup(
            cartItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 461, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(cartItems);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Order : #");

        cashierCb.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cashierCb.setForeground(new java.awt.Color(102, 102, 102));
        cashierCb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADMIN", "DAZZLE", "CALYLE", "ARNEL", "KHIANNE" }));
        cashierCb.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cashierCb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Cashier");

        lbTotal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbTotal.setForeground(new java.awt.Color(0, 51, 51));
        lbTotal.setText("00.00");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 51));
        jLabel4.setText("Total: $");

        txtCash.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtCash.setForeground(new java.awt.Color(102, 102, 102));
        txtCash.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtCash.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCashKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCashKeyTyped(evt);
            }
        });

        jLabel5.setText("Cash: $");

        jLabel6.setText("Change: $");

        lbChange.setText("00.00");

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 51));
        jButton1.setText("PAY");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        orderIdLbl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        orderIdLbl.setForeground(new java.awt.Color(102, 102, 102));
        orderIdLbl.setText("1094729");

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(lbChange, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(24, 24, 24))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(txtCash)))
                        .addGap(58, 58, 58)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(lbTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(10, 10, 10))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(orderIdLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(12, 12, 12))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cashierCb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(2, 2, 2)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cashierCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(orderIdLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTotal)
                    .addComponent(jLabel4)
                    .addComponent(txtCash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lbChange)
                    .addComponent(jButton1))
                .addGap(23, 23, 23))
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        listItem.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout listItemLayout = new javax.swing.GroupLayout(listItem);
        listItem.setLayout(listItemLayout);
        listItemLayout.setHorizontalGroup(
            listItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 926, Short.MAX_VALUE)
        );
        listItemLayout.setVerticalGroup(
            listItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 574, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(listItem);

        categoriesCb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriesCbActionPerformed(evt);
            }
        });

        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchFieldKeyReleased(evt);
            }
        });

        sortedBy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "asc", "desc" }));
        sortedBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortedByActionPerformed(evt);
            }
        });

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(categoriesCb, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sortedBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(35, 35, 35)))
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 261, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(categoriesCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sortedBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
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

    private void txtCashKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCashKeyReleased
        try {
             getCalculatedChange();
              
        } catch (NumberFormatException e) {
            
        }
       
    }//GEN-LAST:event_txtCashKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        InsertItem();
      
    }//GEN-LAST:event_jButton2ActionPerformed

    private void categoriesCbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriesCbActionPerformed
        try {
            testData(searchField.getText(),(String)categoriesCb.getSelectedItem(),(String)sortedBy.getSelectedItem());
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_categoriesCbActionPerformed

    private void sortedByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortedByActionPerformed
       try {
           testData(searchField.getText(),(String)categoriesCb.getSelectedItem(),(String)sortedBy.getSelectedItem());
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_sortedByActionPerformed

    private void searchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyReleased
        try {
           searchData(searchField.getText());
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchFieldKeyReleased

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
      try {
           testData(searchField.getText(),(String)categoriesCb.getSelectedItem(),(String)sortedBy.getSelectedItem());
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchFieldActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            
            if (!cart.isEmpty()) {
                double cashData = Double.parseDouble(txtCash.getText());
            orderController.createOrder(new OrdersModel(orderIdLbl.getText(),(String) cashierCb.getSelectedItem(), cart, total, change, cashData));
           
            
              cart.clear();
             cartData();
            
            
            }else{
                System.out.println("cart is empty");
            }
            
           
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
     cart.clear();
     
     cartData();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtCashKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCashKeyTyped
        char c = evt.getKeyChar();
    String text = txtCash.getText();

    // Allow digits, backspace, delete, and one decimal point
    if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE || c == '.')) {
        evt.consume(); // Prevent invalid input
    }
    // Prevent multiple decimal points
    if (c == '.' && text.contains(".")) {
        evt.consume();
    }
    }//GEN-LAST:event_txtCashKeyTyped
 private void InsertItem(){
     ItemForm itemForm = new ItemForm();
      Option option = ModalDialog.createOption();
      SimpleModalBorder.Option[] options = new SimpleModalBorder.Option[]{new SimpleModalBorder.Option("Add to Cart", SimpleModalBorder.YES_OPTION)
                  ,new SimpleModalBorder.Option("Cancel", SimpleModalBorder.CANCEL_OPTION)};
       option.getLayoutOption().setSize(327, 577)
                .setLocation(Location.RIGHT, Location.CENTER)
                .setAnimateDistance(0.9f, 0);
       
          ModalDialog.showModal(this, new SimpleModalBorder(
                itemForm, "ADD PRODUCT'S", options,
                (controller, action) -> {
                    if (action==SimpleModalBorder.YES_OPTION) {
                        try {
                              productsController.createProducts(itemForm.getItemModelData());
                            Toast.show(this, Toast.Type.INFO, "Succesfully Added");
                           
                        } catch (NumberFormatException e) {
                            Toast.show(this, Toast.Type.WARNING, "Invalid quantity or price input!");
                             controller.consume();
                        }
                        try {     
                            testData(searchField.getText(),(String)categoriesCb.getSelectedItem(),(String)sortedBy.getSelectedItem());
                        } catch (SQLException ex) {
                            Logger.getLogger(ShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
                        }                      
                    }
                  
                }   ), option); 
 }
 
 private void UpdateProducts(ItemModel productData){
     ItemForm itemForm = new ItemForm();
     itemForm.setItemModelData(productData);
      Option option = ModalDialog.createOption();
      SimpleModalBorder.Option[] options = new SimpleModalBorder.Option[]{new SimpleModalBorder.Option("Update", SimpleModalBorder.YES_OPTION)
                  ,new SimpleModalBorder.Option("Delete", SimpleModalBorder.CANCEL_OPTION)};
       option.getLayoutOption().setSize(327, 577)
                .setLocation(Location.RIGHT, Location.CENTER)
                .setAnimateDistance(0.9f, 0);
       
          ModalDialog.showModal(this, new SimpleModalBorder(
                itemForm, "Update product's", options,
                (controller, action) -> {
                    if (action==SimpleModalBorder.YES_OPTION) {
                        try {
                             productsController.updateProducts(itemForm.getItemModelData());
                         Toast.show(this, Toast.Type.INFO, "Succesfully Updated");
                        } catch (NumberFormatException e) {
                            Toast.show(this, Toast.Type.WARNING, "Invalid Price or Quantity!");
                        }     
                    }else{
                        ModalDialog.showModal(this, new SimpleMessageModal(SimpleMessageModal.Type.WARNING, "Are you sure you want to Delete this product?"
                                , "Delete Info", SimpleModalBorder.YES_OPTION, new ModalCallback() {
                            @Override
                            public void action(ModalController mc, int i) {
                                if (i == SimpleModalBorder.YES_OPTION) {
                                     productsController.deleteProducts(itemForm.getItemModelData());
                                   try {
                                    testData(searchField.getText(),(String)categoriesCb.getSelectedItem(),(String)sortedBy.getSelectedItem());
                                   }
                                   catch (SQLException ex) {
                                        Logger.getLogger(ShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
                            }                                  
                                }   
                               
                            }
                          
                         }));   
                      
                    }
         try {
             testData(searchField.getText(),(String)categoriesCb.getSelectedItem(),(String)sortedBy.getSelectedItem());
         } catch (SQLException ex) {
             Logger.getLogger(ShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
         }
                  
        
                }   ), option); 
  
 }
    private void addtoCart(ItemModel data){
        CartForm cartForm = new CartForm();
        cartForm.setData(data);
        Option option = ModalDialog.createOption();    
        option.getLayoutOption().setSize(-1, 1f)
                .setLocation(Location.CENTER, Location.CENTER)
                .setAnimateDistance(0.9f, 0);
       
          SimpleModalBorder.Option[] options = new SimpleModalBorder.Option[]{new SimpleModalBorder.Option("Add to Cart", SimpleModalBorder.YES_OPTION)
                  ,new SimpleModalBorder.Option("Cancel", SimpleModalBorder.CANCEL_OPTION)};
      
        ModalDialog.showModal(this, new SimpleModalBorder(
                cartForm, "ADD TO CART", options,
                (controller, action) -> {
                    if (action==SimpleModalBorder.YES_OPTION) {
                      ItemModel itemModel = cartForm.getData();

                        for (ItemModel  list: cart) {
                            if (list.getTitle().equals(data.getTitle())) {
                               Toast.show(this, Toast.Type.INFO,list.getTitle()+ " is already in the cart");
                                return;
                            }                            
                            }
                   cart.add(itemModel); 
                    calculateTotal();
                      getCalculatedChange();
                        System.out.println("Items in the cart:");
                        for (int i = 0; i < cart.size(); i++) {
                            ItemModel item = cart.get(i);
                            // Print index and item
                            System.out.println("Index: " + i + " - Item: " + item);
                        }
                        cartData();
                    }
                }), option); 
    }
   private void testData(String search,String categories,String sortedBy) throws SQLException{
     
    listItem.removeAll();
    List<ItemModel> listofProduct = productsController.getProducts(search,categories,sortedBy);
    for (ItemModel itemModel : listofProduct) { 
        addItems(itemModel);
    }
         this.setEvent(new EventItem() {
           @Override
           public void ItemClick(Component com, ItemModel data) {
               if (data.getStatus().equals("Unavailable")) {
                   System.out.println("this product is Unavailable");
                    Toast.show(jPanel3, Toast.Type.INFO, "This product is Unavailable!");
               }else{
                   addtoCart(data);
               }       
           }
       }); 
   }
   private void searchData(String search) throws SQLException{
     
    listItem.removeAll();
    List<ItemModel> listofProduct = productsController.searchProducts(search);
    for (ItemModel itemModel : listofProduct) { 
        addItems(itemModel);
    }
         this.setEvent(new EventItem() {
           @Override
           public void ItemClick(Component com, ItemModel data) {
               if (data.getStatus().equals("Unavailable")) {
                   System.out.println("this product is Unavailable");
                    Toast.show(jPanel3, Toast.Type.INFO, "This product is Unavailable!");
               }else{
                   addtoCart(data);
               }       
           }
       }); 
   }
   private void cartData(){
       cartItems.removeAll();     
       for (ItemModel itemModel : cart) {
             addToCart(itemModel);
       }  
       this.setEventCart(new EventCart() {
           @Override
           public void ItemClick(Component com, ItemModel data) {
               int index = cart.indexOf(data);
            if (index != -1) {
               
                System.out.println(data.getQuantity());
            } else {
                System.out.println("Item not found in the cart.");
            }
           }
       });
       
   }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cartItems;
    private javax.swing.JComboBox<String> cashierCb;
    private javax.swing.JComboBox<String> categoriesCb;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbChange;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JPanel listItem;
    private javax.swing.JLabel orderIdLbl;
    private javax.swing.JTextField searchField;
    private javax.swing.JComboBox<String> sortedBy;
    private javax.swing.JTextField txtCash;
    // End of variables declaration//GEN-END:variables
}
