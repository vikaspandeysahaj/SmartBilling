package app.smartBilling.com.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;

import app.smartBilling.com.R;
import app.smartBilling.com.SmartBillingApplication;
import app.smartBilling.com.controller.AuthController;
import app.smartBilling.com.controller.RegistrationController;
import app.smartBilling.com.model.User;


public class SplashActivity extends Activity {

    private SmartBillingApplication application;
    private AuthController authController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
        try {
            performAuthentication();
        } catch (Exception e) {
            displayToast(e.getMessage());
        }
    }

    private void init() {
        application = (SmartBillingApplication)getApplicationContext();
        authController = application.getAuthController();
    }

    private void performAuthentication() throws IOException, JSONException {
        User user = authController.authenticateUser();
        application.setLoggedInUser(user);
        if(user !=null){
            Intent intent = new Intent(this, ProductListActivity.class);
            startActivity(intent);
        }
        else {
            Intent intent1 = new Intent(this, RegistrationActivity.class);
            startActivity(intent1);
        }
    }

    private void displayToast(final String message) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
