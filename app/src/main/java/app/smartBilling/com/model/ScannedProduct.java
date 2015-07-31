package app.smartBilling.com.model;

/**
 * Created by manisharana on 31/07/15.
 */
public class ScannedProduct {
    private Product product;
    private int quantity;

    public ScannedProduct(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
