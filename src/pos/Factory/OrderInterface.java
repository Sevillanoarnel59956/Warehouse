/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos.Factory;

import java.util.List;
import pos.Model.OrdersModel;

/**
 *
 * @author USER
 */
public interface OrderInterface {
    void createOrder(OrdersModel orders);
    void updateProductsOrdered(OrdersModel orders);
    List<OrdersModel>getOrderedProducts(String orderNumber);
}
