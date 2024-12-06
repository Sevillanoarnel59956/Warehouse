/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos.Factory;

import pos.Controller.OrdersController;
import pos.Controller.ProductsController;

/**
 *
 * @author USER
 */
public class ProductsFactory {
    public ProductsInterface CreateProductsController(){
        return new ProductsController();
    }
    public OrderInterface CreateOrderController(){
        return new OrdersController();
    }
}
