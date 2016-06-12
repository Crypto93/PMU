package bg.tu_sofia.pmu.project.testsystem.persistence;

/**
 * Created by Stefan Chuklev on 12.6.2016 г..
 */
interface DBConstants {

    static final String DATABASE_NAME = "TestSystemDB.db";

    static final int DATABASE_VERSION = 1;

    static final String CHECKED_TRUE = "1";
    static final String CHECKED_FALSE = "0";

    static final String ADMIN_TRUE = "1";
    static final String ADMIN_FALSE = "0";

    static final String OPEN_TYPE_QUESTION = "1";
    static final String CLOSED_TYPE_QUESTION = "0";

    //USERS table
    static final String USERS_TABLE = "USERS";
    static final String USER_ID = "_id";
    static final String USERNAME = "username";
    static final String USER_PASSWORD = "pass";
    static final String IS_ADMIN = "isAdmin";

    //RESULTS table
    static final String RESULTS_TABLE = "RESULTS";
    static final String RESULT_ID = "_id";
    static final String RESULT_USER_ID = "user_id";
    static final String RESULT_TEST_ID = "test_id";
    static final String RESULT_CATEGORY_ID = "cat_id";

    //QUESTIONS table
    static final String QUESTIONS_TABLE = "QUESTIONS";
    static final String QUESTION_ID = "_id";
    static final String QUESTION_TYPE = "type";
    static final String QUESTION_CATEGORY = "cat_id";
    static final String QUESTION_TEXT = "text";
    static final String QUESTION_ANSWER_CORRECT = "ans_correct";
    static final String QUESTION_ANSWER_1 = "ans_1";
    static final String QUESTION_ANSWER_2 = "ans_2";
    static final String QUESTION_ANSWER_3 = "ans_3";

    //TESTS table
    static final String TESTS_TABLE = "TESTS";
    static final String TEST_ID = "_id";
    static final String TEST_OBJECT = "test_object";
    static final String TEST_POINTS = "points";
    static final String TEST_CHECKED = "checked";
    static final String TEST_DATETIME = "datetime";

    //CATEGORIES table
    static final String CATEGORIES_TABLE = "CATEGORIES";
    static final String CATEGORY_ID = "_id";
    static final String CATEGORY_NAME = "name";

    // STATEMENTS
    static final String CREATE_USER_TABLE = "CREATE TABLE " + USERS_TABLE + " (" +
            USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            USERNAME + " VARCHAR(30), " +
            USER_PASSWORD + " VARCHAR(64), " +
            IS_ADMIN + " BOOLEAN DEFAULT " + ADMIN_FALSE +
            ");";

    static final String CREATE_RESULTS_TABLE = "CREATE TABLE " + RESULTS_TABLE +
            " (" + RESULT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            RESULT_USER_ID + " INTEGER, " +
            RESULT_CATEGORY_ID + " INTEGER, " +
            RESULT_TEST_ID + " INTEGER, " +
            "FOREIGN KEY(" + RESULT_USER_ID + ") REFERENCES " + USERS_TABLE + "("+ USER_ID +")" +
            "FOREIGN KEY(" + RESULT_CATEGORY_ID + ") REFERENCES " + CATEGORIES_TABLE + "("+ CATEGORY_ID +")" +
            "FOREIGN KEY(" + RESULT_TEST_ID + ") REFERENCES " + TESTS_TABLE + "("+ TEST_ID +")" +
            ");";

    static final String CREATE_TESTS_TABLE = "CREATE TABLE " + TESTS_TABLE + " (" +
            TEST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TEST_POINTS + " INTEGER, " +
            TEST_CHECKED + " BOOLEAN DEFAULT " + CHECKED_FALSE + ", " +
            TEST_OBJECT + "BLOB, " +
            TEST_DATETIME + " DATETIME DEFAULT CURRENT_TIMESTAMP" +
            ");";

    static final String CREATE_CATEGORIES_TABLE = "CREATE TABLE " + CATEGORIES_TABLE + " (" +
            CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CATEGORY_NAME + " VARCHAR(64)" +
            ");";

    static final String CREATE_QUESTIONS_TABLE = "CREATE TABLE " + QUESTIONS_TABLE + " (" +
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

    static final String SELECT_USER = "SELECT * FROM " + USERS_TABLE + " WHERE " + USERNAME + " =?";


}
