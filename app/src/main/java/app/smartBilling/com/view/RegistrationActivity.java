package app.smartBilling.com.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import app.smartBilling.com.R;
import app.smartBilling.com.SmartBillingApplication;
import app.smartBilling.com.controller.RegistrationController;
import app.smartBilling.com.model.User;

/**
 * Created by vikas on 31/07/15.
 */
public class RegistrationActivity extends Activity {

    private SmartBillingApplication application;
    private RegistrationController registrationController;
    private Button btnRegister;
    private EditText txtName;
    private EditText txtPhoneNumber;
    private EditText txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        init();
    }

    private void init() {
        this.btnRegister = (Button)findViewById(R.id.btnRegister);
        this.txtName = (EditText)findViewById(R.id.txtName);
        this.txtPhoneNumber = (EditText)findViewById(R.id.txtPhoneNumber);
        this.txtEmail = (EditText)findViewById(R.id.txtEmail);
        this.application = (SmartBillingApplication)getApplicationContext();
        this.registrationController = application.getRegistrationController();

        this.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateRegistrationForm()) {
                    registerUser();
                }
            }
        });
    }

    private void registerUser() {
        User user = new User(txtName.getText().toString(),
                txtPhoneNumber.getText().toString(),
                txtEmail.getText().toString());
        registrationController.registerUser(user);

        goToNextActivity();
    }

    private void goToNextActivity() {
        Intent intent = new Intent(this,ProductListActivity.class);
        startActivity(intent);
    }

    private boolean validateRegistrationForm(){
        return validateEmptyFields()&&validatePhoneNumber();
    }

    private boolean validateEmptyFields(){
        if(txtPhoneNumber.getText().toString().isEmpty()){

            displayToast("Phone number is required!");
            return false;
        }
        return true;
    }

    private boolean validatePhoneNumber(){
        String regx = "[0-9]{10}";
        if(!txtPhoneNumber.getText().toString().matches(regx)){

            displayToast("invalid mobile number!");
            return false;
        }
        return true;
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
