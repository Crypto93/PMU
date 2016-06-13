package bg.tu_sofia.pmu.project.testsystem.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Stefan Chuklev on 12.6.2016 г..
 */
class DBHelper extends SQLiteOpenHelper implements DBConstants {

    private static DBHelper instance = null;

    private SQLiteDatabase readableDB = null;
    private SQLiteDatabase writableDB = null;

    public synchronized static DBHelper getInstance(Context ctx) {
        if (instance == null) {
            instance = new DBHelper(ctx);
        }
        return instance;
    }

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        readableDB = getReadableDatabase();
        writableDB = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_RESULTS_TABLE);
        db.execSQL(CREATE_QUESTIONS_TABLE);
        db.execSQL(CREATE_CATEGORIES_TABLE);
        db.execSQL(CREATE_TESTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CATEGORIES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + QUESTIONS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + RESULTS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TESTS_TABLE);
        onCreate(db);
    }

    public SQLiteDatabase getPooledReadableDB() {
        return readableDB;
    }

    public SQLiteDatabase getPooledWritableDB() { return writableDB; }
}
