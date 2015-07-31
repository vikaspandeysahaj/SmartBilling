package app.smartBilling.com.service;

import android.content.Context;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import app.smartBilling.com.dao.UserDao;
import app.smartBilling.com.dao.impl.UserDaoImpl;
import app.smartBilling.com.model.User;

/**
 * Created by vikas on 31/07/15.
 */
public class AuthService {

    private UserDao userDao;

    public AuthService(Context context) {
        userDao = new UserDaoImpl(context);
    }

    public void saveUser(User user) {
        userDao.save(user);
    }

    public User authenticate() throws IOException, JSONException {
        List<User> users = userDao.getAll();
        if (users.size() > 0) {
            User loggedInUser = users.get(0);
            if (!loggedInUser.getPhoneNumber().isEmpty()) {
                return loggedInUser;
            }
        }
        return null;
    }
}
