package app.smartBilling.com.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vikas on 13/07/15.
 */
public class SQLDBHelper extends SQLiteOpenHelper {


    private static String DATABASE_NAME = "smartBilling";

    public SQLDBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createUserTable(db);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DATABASE_NAME);
        onCreate(db);
    }


    private void createUserTable(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE USER " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "uuid VARCHAR(50)," +
                "name VARCHAR(50)," +
                "phoneNumber VARCHAR(50)," +
                "email VARCHAR(50));");
    }



}
