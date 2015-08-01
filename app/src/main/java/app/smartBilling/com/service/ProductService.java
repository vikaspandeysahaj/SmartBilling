package app.smartBilling.com.service;

import android.content.Context;

import app.smartBilling.com.dao.ProductDao;
import app.smartBilling.com.dao.impl.ProductDaoImpl;
import app.smartBilling.com.model.Product;

/**
 * Created by vikas on 31/07/15.
 */
public class ProductService {

    private ProductDao productDao;
    public ProductService(Context context) {
        productDao = new ProductDaoImpl(context);
    }

    public Product getProductByBarcode(String barcode){
        return productDao.findProductByBarCode(barcode);
    }

    public void CreateTempProducts() {

        if(productDao.findProductByBarCode("1234567890")==null) {

            Product product = new Product("Product 1", "1234567890", "4000", "abc", "6000");
            Product product1 = new Product("Product 2", "39123439", "4000", "xyz", "6000");
            Product product2 = new Product("Product 3", "640509040147", "4000", "asdf", "6000");


            productDao.save(product);
            productDao.save(product1);
            productDao.save(product2);
        }
    }

    public Product getProductByUUID(String productId) {
        return productDao.findProductByUUID(productId);
    }
}
