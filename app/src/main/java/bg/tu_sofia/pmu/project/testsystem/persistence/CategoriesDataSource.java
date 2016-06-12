package bg.tu_sofia.pmu.project.testsystem.persistence;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import java.util.ArrayList;

/**
 * Created by Stefan Chuklev on 12.6.2016 г..
 */
public class CategoriesDataSource extends DBHelper {
    public CategoriesDataSource(Context context) {
        super(context);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public ArrayList<String> getCattegories() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> cats = new ArrayList<>();

        try (Cursor res = db.rawQuery(SELECT_CATEGORIES, null)) {
            while (res.moveToNext()) {
                cats.add(res.getString(1));
            }
        }

        return cats;

    }
}
