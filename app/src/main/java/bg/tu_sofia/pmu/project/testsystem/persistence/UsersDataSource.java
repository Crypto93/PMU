package bg.tu_sofia.pmu.project.testsystem.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import bg.tu_sofia.pmu.project.testsystem.utils.User;

/**
 * Created by Stefan Chuklev on 12.6.2016 г..
 */
public class UsersDataSource implements DBConstants {

    private SQLiteDatabase readableDB = null;
    private SQLiteDatabase writableDB = null;

    public UsersDataSource(Context context) {
        readableDB = DBHelper.getInstance(context).getPooledReadableDB();
        writableDB = DBHelper.getInstance(context).getPooledWritableDB();
    }

    public boolean insertUser(String username, String password) {
        ContentValues cv = new ContentValues();
        cv.put(USERNAME, username);
        cv.put(USER_PASSWORD, password);
        cv.put(IS_ADMIN, ADMIN_FALSE);

        long isInserted = writableDB.insert(USERS_TABLE, null, cv);

        if (isInserted == -1)
            return false;
        else
            return true;
    }

    public boolean doesUserExist(String username) {
        String[] params = new String[]{username};
        Cursor res = readableDB.rawQuery(SELECT_USER, params);

        if (res.getCount() == 0)
            return true;
        else
            return false;

    }

    public User getUser(String username, String password) {
        String[] params = new String[]{username};
        Cursor res = readableDB.rawQuery(SELECT_USER, params);

        if (res.getCount() == 0)
            return null;

        res.moveToNext();
        int userID = res.getInt(0);
        String usernameFromDb = res.getString(1);
        String passFromDB = res.getString(2);
        int userType = res.getInt(3);

        if (!password.equals(passFromDB)) {
            return null;
        } else {
            if (res.getInt(3) == 1)
                return User.createAdminUser(userID, usernameFromDb, passFromDB);
            else
                return User.createStudentUser(userID, usernameFromDb, passFromDB);
        }
    }

}
