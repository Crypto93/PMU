package bg.tu_sofia.pmu.project.testsystem.persistence.datasources;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import bg.tu_sofia.pmu.project.testsystem.persistence.DBConstants;
import bg.tu_sofia.pmu.project.testsystem.persistence.DBHelper;
import bg.tu_sofia.pmu.project.testsystem.persistence.model.Result;
import bg.tu_sofia.pmu.project.testsystem.utils.TestSystemConstants;

/**
 * Created by Stefan Chuklev on 12.6.2016 г..
 */
public class ResultsDataSource implements DBConstants {
    private SQLiteDatabase readableDB = null;
    private SQLiteDatabase writableDB = null;
    private Context ctx = null;

    public ResultsDataSource(Context context) {
        readableDB = DBHelper.getInstance(context).getPooledReadableDB();
        writableDB = DBHelper.getInstance(context).getPooledWritableDB();
        ctx = context;
    }

    public boolean insertTestResult(Result res) {
        UsersDataSource uds = new UsersDataSource(ctx);
        CategoriesDataSource cds = new CategoriesDataSource(ctx);
        ContentValues cv = new ContentValues();
        cv.put(RESULT_USER_ID, uds.getUserID(res.getUser()));
        cv.put(RESULT_CATEGORY_ID, cds.getCategoryID(res.getCategory()));
        cv.put(RESULT_CORRECT_ANSWERS, res.getCorrectAnswers());
        cv.put(RESULT_WRONG_ANSWERS, res.getWrongAnswers());
        cv.put(RESULT_DATETIME_TAKEN, res.getDateTaken());

        long isInserted = writableDB.insert(RESULTS_TABLE, null, cv);

        if (isInserted == -1)
            return false;
        else
            return true;
    }

    public ArrayList<Result> getTestResultsForAllUsers() {
        return getTestResultsPerUser("ALL");
    }

    public ArrayList<Result> getTestResultsPerUser(String user) {
        ArrayList<Result> results = new ArrayList<>();

        String[] params = null;
        Cursor res = null;
        if (TestSystemConstants.ALL_USERS.equals(user)) {
            res = readableDB.rawQuery(SELECT_ALL_TESTS, params = new String[]{});
        } else {
            res = readableDB.rawQuery(SELECT_SPECIFIC_USER_TESTS, params = new String[]{user});
        }

        try (Cursor resource = res) {
            while (res.moveToNext()) {
                Result result = new Result(
                        resource.getString(res.getColumnIndex(USERNAME)),
                        resource.getString(res.getColumnIndex(CATEGORY_NAME)),
                        resource.getInt(res.getColumnIndex(RESULT_CORRECT_ANSWERS)),
                        resource.getInt(res.getColumnIndex(RESULT_WRONG_ANSWERS)),
                        resource.getLong(res.getColumnIndex(RESULT_DATETIME_TAKEN))
                );
                results.add(result);
            }
        }
        return results;
    }




}
