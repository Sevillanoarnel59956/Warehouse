
package pos.Item;

import com.formdev.flatlaf.FlatClientProperties;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import pos.Model.ItemModel;

/**
 *
 * @author USER
 */
public class Cart extends javax.swing.JPanel {

    
    public ItemModel getData() {
        return data;
    }

   private static final DecimalFormat df = new DecimalFormat("00.00");
    public void setData(ItemModel data) {
        this.data = data;
        if (data.getItemImage().getIcon()!=null) {
              itemImage.setImage(data.getItemImage().getIcon());
        }else{
            itemImage.setImage(new ImageIcon(getClass().getResource("/pos/Image/fries.jpg")));
        }
      
        lbTitle.setText(data.getTitle());   
        String priceData = df.format(data.getPrice());
        lbPrice.setText(priceData);
        String qtyStr = Integer.toString(data.getQuantity());
        
        txtQty.setText(qtyStr);
    }

    public JButton getaddBtn(ItemModel data){

        return addqty;
    }
    
     public JButton getminusBtn(ItemModel data){

        return minusqty;
    }
      public void setQtyData(int qtyData) {
        this.qtyData = qtyData;
        String qtyStr = Integer.toString(qtyData);
        txtQty.setText(qtyStr);
    }
      public JButton deleteIndexBtn(){
          
          return deleteIndex;
      }
    
     private int qtyData;;
    
   private ItemModel data;
    public Cart() {
        initComponents();
         jPanel1.putClientProperty(FlatClientProperties.STYLE,"arc: 25");
        setOpaque(false);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        itemImage = new pos.Swing.PictureBox();
        lbTitle = new javax.swing.JLabel();
        lbPrice1 = new javax.swing.JLabel();
        lbPrice = new javax.swing.JLabel();
        minusqty = new javax.swing.JButton();
        txtQty = new javax.swing.JTextField();
        addqty = new javax.swing.JButton();
        deleteIndex = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        lbTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(102, 102, 102));
        lbTitle.setText("Title");

        lbPrice1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbPrice1.setForeground(new java.awt.Color(102, 102, 102));
        lbPrice1.setText("Price: $");

        lbPrice.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbPrice.setForeground(new java.awt.Color(102, 102, 102));
        lbPrice.setText("Price");

        minusqty.setText("-");

        txtQty.setEditable(false);
        txtQty.setBackground(new java.awt.Color(255, 255, 255));
        txtQty.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtQty.setForeground(new java.awt.Color(102, 102, 102));
        txtQty.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtQty.setText("0");

        addqty.setText("+");
        addqty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addqtyActionPerformed(evt);
            }
        });

        deleteIndex.setBackground(new java.awt.Color(204, 0, 0));
        deleteIndex.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        deleteIndex.setForeground(new java.awt.Color(255, 255, 255));
        deleteIndex.setText("Remove");
        deleteIndex.setToolTipText("");
        deleteIndex.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteIndex.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        deleteIndex.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(itemImage, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbPrice1)
                                .addGap(1, 1, 1)
                                .addComponent(lbPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(minusqty, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addqty, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(deleteIndex, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(itemImage, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbPrice1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(minusqty)
                            .addComponent(addqty))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteIndex)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addqtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addqtyActionPerformed
        if (data.getQuantity()==qtyData) {
            return;
            
        }
    }//GEN-LAST:event_addqtyActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addqty;
    private javax.swing.JButton deleteIndex;
    private pos.Swing.PictureBox itemImage;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbPrice;
    private javax.swing.JLabel lbPrice1;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JButton minusqty;
    private javax.swing.JTextField txtQty;
    // End of variables declaration//GEN-END:variables
}
