package bg.tu_sofia.pmu.project.testsystem.persistence;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import java.util.ArrayList;

/**
 * Created by Stefan Chuklev on 12.6.2016 г..
 */
public class CategoriesDataSource implements DBConstants {

    private SQLiteDatabase readableDB = null;
    private SQLiteDatabase writableDB = null;

    public CategoriesDataSource(Context context) {
        readableDB = DBHelper.getInstance(context).getPooledReadableDB();
        writableDB = DBHelper.getInstance(context).getPooledWritableDB();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public ArrayList<String> getCattegories() {
        ArrayList<String> cats = new ArrayList<>();

        try (Cursor res = readableDB.rawQuery(SELECT_CATEGORIES, null)) {
            while (res.moveToNext()) {
                cats.add(res.getString(1));
            }
        }
        return cats;
    }

    public boolean insertCategory(String catName) {
        ContentValues cv = new ContentValues();
        cv.put(CATEGORY_NAME, catName);
        long isInserted = writableDB.insert(CATEGORIES_TABLE, null, cv);

        if (isInserted == -1)
            return false;
        else
            return true;

    }

    public int getCategoryID(String category) {
        String[] params = new String[]{category};
        Cursor res = readableDB.rawQuery(SELECT_CAT_ID_BY_NAME, params);

        res.moveToNext();
        return res.getInt(res.getColumnIndex(CATEGORY_ID));
    }
}
