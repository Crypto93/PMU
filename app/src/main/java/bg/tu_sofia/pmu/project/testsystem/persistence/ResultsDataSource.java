package bg.tu_sofia.pmu.project.testsystem.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Stefan Chuklev on 12.6.2016 г..
 */
public class ResultsDataSource implements DBConstants {
    private SQLiteDatabase readableDB = null;
    private SQLiteDatabase writableDB = null;

    public ResultsDataSource(Context context) {
        readableDB = DBHelper.getInstance(context).getPooledReadableDB();
        writableDB = DBHelper.getInstance(context).getPooledWritableDB();
    }
}
