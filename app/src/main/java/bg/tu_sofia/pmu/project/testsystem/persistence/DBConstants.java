package bg.tu_sofia.pmu.project.testsystem.persistence;

/**
 * Created by Stefan Chuklev on 12.6.2016 г..
 */
public interface DBConstants {

    static final String DATABASE_NAME = "TestSystemDB.db";

    public static final int DATABASE_VERSION = 1;

    public static final String CHECKED_TRUE = "1";
    public static final String CHECKED_FALSE = "0";

    public static final String ADMIN_TRUE = "1";
    public static final String ADMIN_FALSE = "0";

    public static final String OPEN_TYPE_QUESTION = "1";
    public static final String CLOSED_TYPE_QUESTION = "0";

    //USERS table
    public static final String USERS_TABLE = "USERS";
    public static final String USER_ID = "user_id";
    public static final String USERNAME = "username";
    public static final String USER_PASSWORD = "pass";
    public static final String IS_ADMIN = "isAdmin";

    //RESULTS table
    public static final String RESULTS_TABLE = "RESULTS";
    public static final String RESULT_ID = "result_id";
    public static final String RESULT_USER_ID = "res_user_id";
    public static final String RESULT_CORRECT_ANSWERS = "correct";
    public static final String RESULT_WRONG_ANSWERS = "wrong";
    public static final String RESULT_CATEGORY_ID = "cat_id";
    public static final String RESULT_DATETIME_TAKEN = "datetime_taken";


    //QUESTIONS table
    public static final String QUESTIONS_TABLE = "QUESTIONS";
    public static final String QUESTION_ID = "question_id";
    public static final String QUESTION_TYPE = "type";
    public static final String QUESTION_CATEGORY = "cat_id";
    public static final String QUESTION_TEXT = "text";
    public static final String QUESTION_ANSWER_CORRECT = "ans_correct";
    public static final String QUESTION_ANSWER_1 = "ans_1";
    public static final String QUESTION_ANSWER_2 = "ans_2";
    public static final String QUESTION_ANSWER_3 = "ans_3";

    //CATEGORIES table
    public static final String CATEGORIES_TABLE = "CATEGORIES";
    public static final String CATEGORY_ID = "category_id";
    public static final String CATEGORY_NAME = "name";

    // STATEMENTS
    public static final String CREATE_USER_TABLE = "CREATE TABLE " + USERS_TABLE + " (" +
            USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            USERNAME + " VARCHAR(30), " +
            USER_PASSWORD + " VARCHAR(64), " +
            IS_ADMIN + " BOOLEAN DEFAULT " + ADMIN_FALSE +
            ");";

    public static final String CREATE_RESULTS_TABLE = "CREATE TABLE " + RESULTS_TABLE +
            " (" + RESULT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            RESULT_USER_ID + " INTEGER, " +
            RESULT_CATEGORY_ID + " INTEGER, " +
            RESULT_CORRECT_ANSWERS + " INTEGER, " +
            RESULT_WRONG_ANSWERS + " INTEGER, " +
            RESULT_DATETIME_TAKEN + " LONG, " +
            "FOREIGN KEY(" + RESULT_USER_ID + ") REFERENCES " + USERS_TABLE + "("+ USER_ID +")" +
            "FOREIGN KEY(" + RESULT_CATEGORY_ID + ") REFERENCES " + CATEGORIES_TABLE + "("+ CATEGORY_ID +")" +
            ");";

    public static final String CREATE_CATEGORIES_TABLE = "CREATE TABLE " + CATEGORIES_TABLE + " (" +
            CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CATEGORY_NAME + " VARCHAR(64)" +
            ");";

    public static final String CREATE_QUESTIONS_TABLE = "CREATE TABLE " + QUESTIONS_TABLE + " (" +
            QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            QUESTION_TEXT + " TEXT NOT NULL, " +
            QUESTION_TYPE + " TINYINT NOT NULL, " +
            QUESTION_CATEGORY + " INTEGER, " +
            QUESTION_ANSWER_CORRECT + " TEXT, " +
            QUESTION_ANSWER_1 + " TEXT, " +
            QUESTION_ANSWER_2 + " TEXT, " +
            QUESTION_ANSWER_3 + " TEXT, " +
            "FOREIGN KEY(" + QUESTION_CATEGORY + ") REFERENCES " + CATEGORIES_TABLE + "("+ CATEGORY_ID +")" +
            ");";

    public static final String SELECT_USER = "SELECT * FROM " + USERS_TABLE + " WHERE " + USERNAME + " =?";
    public static final String SELECT_USERNAME_BY_ID = "SELECT * FROM " + USERS_TABLE + " WHERE " + USER_ID + " =?";

    public static final String SELECT_CATEGORIES = "SELECT * FROM " + CATEGORIES_TABLE;
    public static final String SELECT_CATEGORY_NAME = "SELECT * FROM " + CATEGORIES_TABLE + " WHERE " + CATEGORY_ID + "=?";

    public static final String SELECT_RANDOM_CLOSED_QUESTIONS_BY_CAT = "SELECT DISTINCT * FROM " + QUESTIONS_TABLE +
            " WHERE " + QUESTION_TYPE + "=" + CLOSED_TYPE_QUESTION +
            " AND " + QUESTION_CATEGORY +
            "=? ORDER BY RANDOM() LIMIT ?";

    public static final String SELECT_RANDOM_OPEN_QUESTIONS_BY_CAT = "SELECT DISTINCT * FROM " + QUESTIONS_TABLE +
            " WHERE " + QUESTION_TYPE + "=" + OPEN_TYPE_QUESTION +
            " AND " + QUESTION_CATEGORY +
            "=? ORDER BY RANDOM() LIMIT ?";

    public static final String SELECT_CAT_ID_BY_NAME = "SELECT " + CATEGORY_ID + " FROM " + CATEGORIES_TABLE + " WHERE " + CATEGORY_NAME + " =?";
    public static final String SELECT_USER_ID_BY_NAME = "SELECT " + USER_ID + " FROM " + USERS_TABLE + " WHERE " + USERNAME + " =?";

    public static final String SELECT_ALL_TESTS = "SELECT *" +
            " FROM " + RESULTS_TABLE;

    public static final String SELECT_SPECIFIC_USER_TESTS = "SELECT *" +
            " FROM " + RESULTS_TABLE +
            " WHERE " + RESULT_USER_ID + "=?";

    // Testing purposes
    public static String INSER_QUESTIONS = "INSERT INTO QUESTIONS VALUES(?, ?, ?, ?, ?, ?, ?, ?)";





}
