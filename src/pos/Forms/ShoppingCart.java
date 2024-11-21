
package pos.Forms;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import pos.Event.EventCart;
import pos.Event.EventItem;
import pos.Item.Cart;
import pos.Item.PosItem;
import pos.Model.ItemModel;
import pos.PopUpFoms.CartForm;
import raven.modal.ModalDialog;
import raven.modal.Toast;
import raven.modal.component.SimpleModalBorder;
import raven.modal.demo.layout.ResponsiveLayout;
import raven.modal.option.Location;
import raven.modal.option.Option;


public class ShoppingCart extends javax.swing.JPanel {


    private EventItem event;
    private EventCart eventCart;
    private List<ItemModel>cart = new ArrayList<>();
    private int total;
    private int change;
    public ShoppingCart() {
        initComponents();
             jScrollPane1.getVerticalScrollBar().setUnitIncrement(20);
               jScrollPane2.getVerticalScrollBar().setUnitIncrement(20);
        ResponsiveLayout listItemLayout = new ResponsiveLayout(ResponsiveLayout.JustifyContent.FIT_CONTENT, 
                new Dimension(-1, -1), 2, 2);
        ResponsiveLayout cartlistLayout = new ResponsiveLayout(ResponsiveLayout.JustifyContent.FIT_CONTENT, 
                new Dimension(-1, -1), 2, 2);
        listItemLayout.setColumn(4);
        cartlistLayout.setColumn(1);
        cartlistLayout.setSize(new Dimension(250, 150));
        listItemLayout.setSize(new Dimension(162, 261));
        listItemLayout.setHorizontalGap(7);
        listItemLayout.setVerticalGap(7);
        listItem.setLayout(listItemLayout);
         testData();
        cartItems.setLayout(cartlistLayout);
   
    }
    
    public void addItems(ItemModel data){
        PosItem item = new PosItem();
        item.setData(data);
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
        
        cartshop.getaddBtn(data).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { 
               int index = cart.indexOf(data);
               if (index != -1) {
                // Increment the quantity in the ItemModel
                ItemModel currentItem = cart.get(index);
                int newQty = currentItem.getQuantity() + 1;
                currentItem.setQuantity(newQty);
                
                // Update the display quantity
                cartshop.setQtyData(newQty); 
                // Update cart list
                cart.set(index, currentItem);
            }
              calculateTotal();
                getCalculatedChange();
                for (int i = 0; i < cart.size(); i++) {
                            ItemModel item = cart.get(i);
                            // Print index and item
                            System.out.println("Index: " + i + " - Item: " + item);
                        }
        
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
    
    private int calculateChange(){
         total = 0;  // Reset total before recalculating
         change = 0;
    for (ItemModel list : cart) {
        int totalData = list.getPrice() * list.getQuantity();
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
              String changeStr = Integer.toString(calculateChange());
        
        lbChange.setText(changeStr);
        } catch (NumberFormatException e) {
            
        }
      
        
    }
  private void calculateTotal() {
    total = 0;  // Reset total before recalculating
    for (ItemModel list : cart) {
        int totalData = list.getPrice() * list.getQuantity();
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
    
      public int getTotal() {
        return total;
    }

    
    public void setTotal(int total) {
        this.total = total;
        String totalStr = Integer.toString(getTotal());
        lbTotal.setText(totalStr);
    }

    public int getChange() {
        return change;
    }

    public void setChange(int change) {
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
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCash = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbChange = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listItem = new javax.swing.JPanel();

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
            .addGap(0, 453, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(cartItems);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Order : #");

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(102, 102, 102));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADMIN", "DAZZLE", "CALYLE", "ARNEL", "KHIANNE" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

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
        });

        jLabel5.setText("Cash: $");

        jLabel6.setText("Change: $");

        lbChange.setText("00.00");

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 51));
        jButton1.setText("PAY");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("1094729");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
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
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
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
            .addGap(0, 798, Short.MAX_VALUE)
        );
        listItemLayout.setVerticalGroup(
            listItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 574, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(listItem);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
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
   getCalculatedChange();
    }//GEN-LAST:event_txtCashKeyReleased

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
                cartForm, "Test", options,
                (controller, action) -> {
                    if (action==SimpleModalBorder.YES_OPTION) {
                      ItemModel itemModel = cartForm.getData();

                   //make a condition to not proceed if the name of the product is already in the list or cart
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
   private void testData(){
       listItem.removeAll();
       
       
       addItems(new ItemModel(new ImageIcon(getClass().getResource("/pos/Image/burger.jpg")), "Burger", 99, "Available"));
       addItems(new ItemModel(new ImageIcon(getClass().getResource("/pos/Image/fries.jpg")), "Fries", 49, "Available"));
       addItems(new ItemModel(new ImageIcon(getClass().getResource("/pos/Image/pizza.jpg")), "Pizza", 59, "Available"));
       addItems(new ItemModel(new ImageIcon(getClass().getResource("/pos/Image/burger.jpg")), "Burger", 99, "Available"));
       addItems(new ItemModel(new ImageIcon(getClass().getResource("/pos/Image/fries.jpg")), "Fries", 49, "Available"));
       addItems(new ItemModel(new ImageIcon(getClass().getResource("/pos/Image/pizza.jpg")), "Pizza", 59, "Available"));
       addItems(new ItemModel(new ImageIcon(getClass().getResource("/pos/Image/burger.jpg")), "Burger", 99, "Available"));
       addItems(new ItemModel(new ImageIcon(getClass().getResource("/pos/Image/fries.jpg")), "Fries", 49, "Available"));
       addItems(new ItemModel(new ImageIcon(getClass().getResource("/pos/Image/pizza.jpg")), "Pizza", 59, "Available"));
       addItems(new ItemModel(new ImageIcon(getClass().getResource("/pos/Image/burger.jpg")), "Burger", 99, "Available"));
       addItems(new ItemModel(new ImageIcon(getClass().getResource("/pos/Image/fries.jpg")), "Fries", 49, "Unavailable"));
       addItems(new ItemModel(new ImageIcon(getClass().getResource("/pos/Image/pizza.jpg")), "Pizza", 59, "Available"));
       
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
            
            // Print the index of the clicked item
            if (index != -1) {
                System.out.println("Item Index: " + index);
            } else {
                System.out.println("Item not found in the cart.");
            }
           }
       });
       
   }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cartItems;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JTextField txtCash;
    // End of variables declaration//GEN-END:variables
}
