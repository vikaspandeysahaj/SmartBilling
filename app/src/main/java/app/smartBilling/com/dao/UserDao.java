package app.smartBilling.com.dao;

import java.util.List;

import app.smartBilling.com.model.User;

/**
 * Created by vikas on 31/07/15.
 */
public interface UserDao {
    void save(User user);

    List<User> getAll();
}
