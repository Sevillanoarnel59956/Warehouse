/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package pos.Forms;

import java.awt.Component;
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
    public ShoppingCart() {
        initComponents();
 
        ResponsiveLayout listItemLayout = new ResponsiveLayout(ResponsiveLayout.JustifyContent.FIT_CONTENT, 
                new Dimension(-1, -1), 2, 2);
        ResponsiveLayout cartlistLayout = new ResponsiveLayout(ResponsiveLayout.JustifyContent.FIT_CONTENT, 
                new Dimension(-1, -1), 2, 2);
        listItemLayout.setColumn(4);
        cartlistLayout.setColumn(1);
        cartlistLayout.setSize(new Dimension(246, 140));
        listItemLayout.setSize(new Dimension(162, 261));
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
            }
       
        });
        cartshop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               eventCart.ItemClick(cartItems, data);
            }
        
        
        });
        cartItems.add(cartshop);
        repaint();
        revalidate();
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        cartItems = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listItem = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout cartItemsLayout = new javax.swing.GroupLayout(cartItems);
        cartItems.setLayout(cartItemsLayout);
        cartItemsLayout.setHorizontalGroup(
            cartItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 259, Short.MAX_VALUE)
        );
        cartItemsLayout.setVerticalGroup(
            cartItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 431, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(cartItems);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Order #1094729");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2)
                .addGap(62, 62, 62))
        );

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
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

                        // Set cartIndex based on the current size of the cart
//                        itemModel.setCartIndex(cart.size());

                        // Add the item to the cart
                        cart.add(itemModel);

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
                   JOptionPane.showMessageDialog(null, "this product is Unavailable");
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel listItem;
    // End of variables declaration//GEN-END:variables
}
