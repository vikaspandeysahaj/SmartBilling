package app.smartBilling.com.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by vikas on 14/07/15.
 */
public class SQLDaoHelper {

    protected SQLiteDatabase db;
    protected SQLDBHelper sqldbHelper;

    public SQLDaoHelper(Context context){
        sqldbHelper = new SQLDBHelper(context);
        db = sqldbHelper.getReadableDatabase();
    }

    public void close() {
        db.close();
    }
}
