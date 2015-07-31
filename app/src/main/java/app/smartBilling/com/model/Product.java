package app.smartBilling.com.model;

import java.util.UUID;

/**
 * Created by vikas on 31/07/15.
 */
public class Product {
    private String uuid;
    private String title;
    private String barcode;
    private String price;
    private String company;

    public Product(){ }

    public Product(String title, String barcode, String price, String company){
        this.uuid = UUID.randomUUID().toString();
        this.title = title;
        this.barcode = barcode;
        this.price = price;
        this.setCompany(company);
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
