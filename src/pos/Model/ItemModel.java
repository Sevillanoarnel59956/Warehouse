/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos.Model;

import javax.swing.Icon;
import pos.Categories.Categories;


public class ItemModel {

    /**
     * @return the availableStocks
     */
    public int getAvailableStocks() {
        return availableStocks;
    }

    /**
     * @param availableStocks the availableStocks to set
     */
    public void setAvailableStocks(int availableStocks) {
        this.availableStocks = availableStocks;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the itemImage
     */
    public FileImageModel getItemImage() {
        return itemImage;
    }

    public void setItemImage(FileImageModel itemImage) {
        this.itemImage = itemImage;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public String getItemId() {
        return ItemId;
    }

    public void setItemId(String ItemId) {
        this.ItemId = ItemId;
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

  
   
    public void setItemImage(Icon itemImage) {
        this.itemImage = new FileImageModel(itemImage);
    }

  
    public String getTitle() {
        return title;
    }

   
    public void setTitle(String title) {
        this.title = title;
    }

 

    public void setPrice(int price) {
        this.setPrice(price);
    }

    public ItemModel(String ItemId, Categories category, FileImageModel itemImage, String title, double price, String status, int quantity) {
        this.ItemId = ItemId;
        this.category = category;
        this.itemImage = itemImage;
        this.title = title;
        this.price = price;
        this.status = status;
        this.quantity = quantity;
    }

  

    public ItemModel() {
    }
    private String ItemId;
    private Categories category;
    private FileImageModel itemImage;
    private String title;
    private double price;
    private String status;
    private int quantity;
    private int availableStocks;
  
}
