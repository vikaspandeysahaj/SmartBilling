package app.smartBilling.com.controller;

import android.content.Context;

import app.smartBilling.com.helper.BarcodeHelper;
import app.smartBilling.com.model.Product;
import app.smartBilling.com.service.ProductService;

/**
 * Created by vikas on 31/07/15.
 */
public class ProductController {

    private ProductService productService;
    public ProductController(Context context) {
        productService = new ProductService(context);
        CreateTempProducts();
    }

    public Product getProductByBarcode(String format, String barcode) {
        return productService.getProductByBarcode(barcode);

    }

    public void CreateTempProducts(){
        productService.CreateTempProducts();
    }
}
