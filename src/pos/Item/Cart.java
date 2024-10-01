
package pos.Item;

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

  
    public void setData(ItemModel data) {
        this.data = data;
        itemImage.setImage(data.getItemImage());
        lbTitle.setText(data.getTitle());
        String priceStr = Integer.toString(data.getPrice());
        lbPrice.setText(priceStr);
        String qtyStr = Integer.toString(data.getQuantity());
        lbPrice.setText(priceStr);
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
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        itemImage = new pos.Swing.PictureBox();
        lbTitle = new javax.swing.JLabel();
        lbPrice = new javax.swing.JLabel();
        txtQty = new javax.swing.JTextField();
        minusqty = new javax.swing.JButton();
        addqty = new javax.swing.JButton();
        lbPrice1 = new javax.swing.JLabel();
        deleteIndex = new javax.swing.JButton();

        lbTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(102, 102, 102));
        lbTitle.setText("Title");

        lbPrice.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbPrice.setForeground(new java.awt.Color(102, 102, 102));
        lbPrice.setText("Price");

        txtQty.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtQty.setForeground(new java.awt.Color(102, 102, 102));
        txtQty.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtQty.setText("0");

        minusqty.setText("-");

        addqty.setText("+");

        lbPrice1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbPrice1.setForeground(new java.awt.Color(102, 102, 102));
        lbPrice1.setText("Price: $");

        deleteIndex.setBackground(new java.awt.Color(204, 0, 0));
        deleteIndex.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        deleteIndex.setForeground(new java.awt.Color(255, 255, 255));
        deleteIndex.setText("x");
        deleteIndex.setToolTipText("");
        deleteIndex.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteIndex.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        deleteIndex.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(itemImage, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbPrice1)
                        .addGap(1, 1, 1)
                        .addComponent(lbPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(minusqty, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addqty, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(deleteIndex, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(6, 6, 6))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(itemImage, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbPrice1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(minusqty)
                            .addComponent(addqty))
                        .addGap(5, 5, 5)
                        .addComponent(deleteIndex)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addqty;
    private javax.swing.JButton deleteIndex;
    private pos.Swing.PictureBox itemImage;
    private javax.swing.JLabel lbPrice;
    private javax.swing.JLabel lbPrice1;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JButton minusqty;
    private javax.swing.JTextField txtQty;
    // End of variables declaration//GEN-END:variables
}
