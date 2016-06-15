package bg.tu_sofia.pmu.project.testsystem.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Stefan Chuklev on 12.6.2016 г..
 */
public class DBHelper extends SQLiteOpenHelper implements DBConstants {

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
        db.execSQL("INSERT INTO \"QUESTIONS\" VALUES (\"1\",\"Коя е столицата на България?\",\"0\",\"2\",\"София\",\"Отава\",\"Москва\",\"Панагюрище\");" +
                "INSERT INTO \"QUESTIONS\" VALUES (\"2\",\"Коя е столицата на Канада?\",\"0\",\"2\",\"Отава\",\"Минск\",\"Вашингтон\",\"Торонто\");" +
                "INSERT INTO \"QUESTIONS\" VALUES (\"3\",\"Коя е столицата на Канада?\",\"0\",\"2\",\"Отава\",\"Минск\",\"Вашингтон\",\"Торонто\");" +
                "INSERT INTO \"QUESTIONS\" VALUES (\"4\",\"Коя е столицата на Русия?\",\"0\",\"2\",\"Москва\",\"Минск\",\"Санкт Петърбург\",\"Вилнюс\");" +
                "INSERT INTO \"QUESTIONS\" VALUES (\"5\",\"На кой полуостров е разположена България?\",\"0\",\"2\",\"Балкански\",\"Апенински\",\"Арабски\",\"Пиринейски\");" +
                "INSERT INTO \"QUESTIONS\" VALUES (\"6\",\"Коя е най-източната точка на България?\",\"0\",\"2\",\"нос Шабла\",\"нос Емине\",\"устието на река Тимок\",\"връх Вейката\");" +
                "INSERT INTO \"QUESTIONS\" VALUES (\"8\",\"Височината на връх Мусала е?\",\"0\",\"2\",\"2925\",\"2565\",\"3125\",\"2995\");" +
                "INSERT INTO \"QUESTIONS\" VALUES (\"9\",\"Кой се грижи за непотребните обекти в Java?\",\"0\",\"1\",\"Garbage collector\",\"finalize()\",\"System.cleaner()\",\"free()\");" +
                "INSERT INTO \"QUESTIONS\" VALUES (\"10\",\"С коя ключова дума се създава нов референтен тип в Java?\",\"0\",\"1\",\"new\",\"create\",\"build\",\"make\");" +
                "INSERT INTO \"QUESTIONS\" VALUES (\"11\",\"С коя ключова дума се използва за синхронизация в Java?\",\"0\",\"1\",\"synchronized\",\"concurent\",\"singleton\",\"private\");" +
                "INSERT INTO \"QUESTIONS\" VALUES (\"12\",\"Обяснете понятието Garbage collector в Java.\",\"1\",\"1\",\"\",\"\",\"\",\"\");" +
                "INSERT INTO \"QUESTIONS\" VALUES (\"13\",\"Декларирайте обект от тип String в java.\",\"1\",\"1\",\"\",\"\",\"\",\"\");" +
                "INSERT INTO \"QUESTIONS\" VALUES (\"14\",\"Кой интерфейс се използва за сериализиране на обекти?\",\"0\",\"1\",\"Serializabale\",\"Serialise\",\"Serial\",\"ByteBuilder\");" +
                "INSERT INTO \"QUESTIONS\" VALUES (\"15\",\"С коя ключова дума хвърляме нов Exception в Java?\",\"0\",\"1\",\"throw\",\"throws\",\"catch\",\"try\");" +
                "INSERT INTO \"QUESTIONS\" VALUES (\"7\",\"Коя е най-северната точка на България?\",\"0\",\"2\",\"устието на река Тимок\",\"устието на река Камчия\",\"устието на река Велека\",\"връх Вейката\");" +
                "INSERT INTO \"USERS\" VALUES (\"1\",\"s.chuklev@gmai.com\",\"1q2w3e4r\",\"1\");" +
                "INSERT INTO \"USERS\" VALUES (\"2\",\"atasheva@tu-sofia.bg\",\"123123\",\"1\");" +
                "INSERT INTO \"USERS\" VALUES (\"3\",\"sudent@test.bg\",\"123123\",\"0\");" +
                "INSERT INTO \"USERS\" VALUES (\"4\",\"123@\",\"123123\",\"0\");" +
                "INSERT INTO \"CATEGORIES\" VALUES (\"1\",\"JAVA\");" +
                "INSERT INTO \"CATEGORIES\" VALUES (\"2\",\"География\");" +
                "INSERT INTO \"RESULTS\" VALUES (\"1\",\"4\",\"1\",\"4\",\"1\",\"16165616651\");" +
                "INSERT INTO \"RESULTS\" VALUES (\"2\",\"4\",\"0\",\"2\",\"3\",\"468\");");
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
        onCreate(db);
    }

    public SQLiteDatabase getPooledReadableDB() {return readableDB; }

    public SQLiteDatabase getPooledWritableDB() { return writableDB; }
}
