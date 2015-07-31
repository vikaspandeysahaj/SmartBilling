package app.smartBilling.com.dao;

import java.util.List;

import app.smartBilling.com.model.Product;


/**
 * Created by vikas on 31/07/15.
 */
public interface ProductDao {

    void save(Product product);
    List<Product> getAll();
    Product findProductByBarCode(String barcode);


}
