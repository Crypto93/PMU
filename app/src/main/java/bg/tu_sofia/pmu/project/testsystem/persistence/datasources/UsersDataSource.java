package bg.tu_sofia.pmu.project.testsystem.persistence.datasources;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import bg.tu_sofia.pmu.project.testsystem.persistence.DBConstants;
import bg.tu_sofia.pmu.project.testsystem.persistence.DBHelper;
import bg.tu_sofia.pmu.project.testsystem.persistence.model.User;

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

    public boolean insertUser(User user) {
        ContentValues cv = new ContentValues();
        cv.put(USERNAME, user.getUsername());
        cv.put(USER_PASSWORD, user.getPassword());
        if (User.UserType.ADMIN == user.getUserType())
            cv.put(IS_ADMIN, ADMIN_TRUE);
        else
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
                return User.createAdminUser(usernameFromDb, passFromDB);
            else
                return User.createStudentUser(usernameFromDb, passFromDB);
        }
    }

    public int getUserID(String user) {
        String[] params = new String[]{user};
        Cursor res = readableDB.rawQuery(SELECT_USER_ID_BY_NAME, params);

        res.moveToNext();
        return res.getInt(res.getColumnIndex(USER_ID));
    }

}
