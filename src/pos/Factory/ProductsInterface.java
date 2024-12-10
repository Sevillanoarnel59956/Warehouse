
package pos.Factory;

import java.sql.SQLException;
import java.util.List;
import pos.Model.ItemModel;

/**
 *
 * @author USER
 */
public interface ProductsInterface {
    void createProducts(ItemModel Productsdata);
    void updateProducts(ItemModel Productsdata);
    void deleteProducts(ItemModel Productsdata);
  
    List<ItemModel>getProducts(String search,String categories, String orderBy)throws SQLException;
    List<ItemModel>searchProducts(String search)throws SQLException;
  
    
    
}
