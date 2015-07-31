package app.smartBilling.com.controller;

import android.content.Context;

import app.smartBilling.com.model.User;
import app.smartBilling.com.service.AuthService;

/**
 * Created by vikas on 31/07/15.
 */
public class RegistrationController {

    private AuthService authService;

    public RegistrationController(Context context) {
        authService = new AuthService(context);
    }

    public void registerUser(User user){
        authService.saveUser(user);
    }
}
