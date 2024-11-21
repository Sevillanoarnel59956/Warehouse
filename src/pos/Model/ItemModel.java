/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos.Model;

import javax.swing.Icon;


public class ItemModel {

   
    public int getCartIndex() {
        return cartIndex;
    }

    public void setCartIndex(int cartIndex) {
        this.cartIndex = cartIndex;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

  
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

  
    public Icon getItemImage() {
        return itemImage;
    }

   
    public void setItemImage(Icon itemImage) {
        this.itemImage = itemImage;
    }

  
    public String getTitle() {
        return title;
    }

   
    public void setTitle(String title) {
        this.title = title;
    }

   
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ItemModel(Icon itemImage, String title, int price, String status) {
        this.itemImage = itemImage;
        this.title = title;
        this.price = price;
        this.status = status;
    }

    public ItemModel() {
    }
    private Icon itemImage;
    private String title;
    private int price;
    private String status;
    private int quantity;
    private int cartIndex;

    @Override
    public String toString() {
        return "Title: "+title +" |"+" Quantity :" + quantity +" | " + cartIndex; 
    }
    
    
}
