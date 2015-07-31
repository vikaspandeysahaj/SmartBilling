package app.smartBilling.com.controller;

import android.content.Context;

import org.json.JSONException;

import java.io.IOException;

import app.smartBilling.com.model.User;
import app.smartBilling.com.service.AuthService;

/**
 * Created by vikas on 31/07/15.
 */
public class AuthController {
    private AuthService authService;

    public AuthController(Context context) {
        authService = new AuthService(context);
    }

    public void registerUser(User user){
        authService.saveUser(user);
    }

    public User authenticateUser() throws IOException, JSONException {
        return authService.authenticate();
    }
}
