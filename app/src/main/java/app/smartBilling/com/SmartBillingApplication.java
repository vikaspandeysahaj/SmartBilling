package app.smartBilling.com;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import app.smartBilling.com.controller.AuthController;
import app.smartBilling.com.controller.ProductController;
import app.smartBilling.com.controller.RegistrationController;
import app.smartBilling.com.model.Product;
import app.smartBilling.com.model.User;

/**
 * Created by vikas on 31/07/15.
 */
public class SmartBillingApplication extends Application {

    public static List<Product> productList = new ArrayList<Product>();
    private RegistrationController registrationController;
    private AuthController authController;
    private ProductController productController;
    private User loggedInUser;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public AuthController getAuthController(){
        if(authController==null){
            authController = new AuthController(getApplicationContext());
        }
        return authController;
    }

    public RegistrationController getRegistrationController(){
        if(registrationController==null){
            registrationController = new RegistrationController(getApplicationContext());
        }
        return registrationController;
    }

    public ProductController getProductController(){
        if(productController==null){
            productController = new ProductController(getApplicationContext());
        }
        return productController;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
