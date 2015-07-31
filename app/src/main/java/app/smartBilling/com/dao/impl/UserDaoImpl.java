package app.smartBilling.com.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import app.smartBilling.com.dao.UserDao;
import app.smartBilling.com.helper.SQLDaoHelper;
import app.smartBilling.com.model.User;

/**
 * Created by vikas on 31/07/15.
 */
public class UserDaoImpl extends SQLDaoHelper implements UserDao{

    public UserDaoImpl(Context context) {
        super(context);
    }

    @Override
    public void save(User user) {
        db.insert("USER", null, getContentValues(user));
    }

    private ContentValues getContentValues(User user){
        ContentValues contentValues = new ContentValues();
        contentValues.put("uuid", user.getUuid());
        contentValues.put("name", user.getName());
        contentValues.put("phoneNumber", user.getPhoneNumber());
        contentValues.put("email", user.getEmail());
        return contentValues;
    }

    @Override
    public List<User> getAll() {

        List<User> users = new ArrayList<User>();
        String[] tableColumns = new String[] {"uuid","name","phoneNumber","email"};
        Cursor cursor = db.query("USER", tableColumns, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            User user = new User();
            user.setUuid(cursor.getString(0));
            user.setName(cursor.getString(1));
            user.setPhoneNumber(cursor.getString(2));
            user.setEmail(cursor.getString(3));
            users.add(user);
            cursor.moveToNext();
        }

        return users;
    }
}
