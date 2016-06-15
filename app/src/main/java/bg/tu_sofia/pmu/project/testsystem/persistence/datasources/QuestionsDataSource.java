package bg.tu_sofia.pmu.project.testsystem.persistence.datasources;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import java.util.ArrayList;
import java.util.LinkedList;

import bg.tu_sofia.pmu.project.testsystem.persistence.DBConstants;
import bg.tu_sofia.pmu.project.testsystem.persistence.DBHelper;
import bg.tu_sofia.pmu.project.testsystem.persistence.model.Question;

/**
 * Created by Stefan Chuklev on 12.6.2016 г..
 */
public class QuestionsDataSource implements DBConstants {

    private SQLiteDatabase readableDB = null;
    private SQLiteDatabase writableDB = null;

    public QuestionsDataSource(Context context) {
        readableDB = DBHelper.getInstance(context).getPooledReadableDB();
        writableDB = DBHelper.getInstance(context).getPooledWritableDB();
    }

    private Cursor getClosedQuestionByCategory(int catId, int numberOfQuestions) {
        String[] params = new String[]{String.valueOf(catId), String.valueOf(numberOfQuestions)};
        Cursor res = readableDB.rawQuery(SELECT_RANDOM_CLOSED_QUESTIONS_BY_CAT, params);

        return res;
    }



    private Cursor getOpenQuestionByCategory(int catId, int numberOfQuestions) {
        String[] params = new String[]{String.valueOf(catId), String.valueOf(numberOfQuestions)};
        Cursor res = readableDB.rawQuery(SELECT_RANDOM_OPEN_QUESTIONS_BY_CAT, params);

        return res;
    }

    public int getCategoryID(String category) {
        String[] params = new String[]{category};
        Cursor res = readableDB.rawQuery(SELECT_CAT_ID_BY_NAME, params);

        res.moveToNext();
        return res.getInt(res.getColumnIndex(CATEGORY_ID));
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public LinkedList<Question> getTestQuestions(int closedQuestionsNumber, int openQuestionsNumber, int catId) {
        LinkedList<Question> questions = new LinkedList<>();

        try (Cursor res = getClosedQuestionByCategory(catId, closedQuestionsNumber)) {
            while (res.moveToNext()) {
                String questionText = res.getString(res.getColumnIndex(QUESTION_TEXT));
                String correctAnswer = res.getString(res.getColumnIndex(QUESTION_ANSWER_CORRECT));
                String answer1 = res.getString(res.getColumnIndex(QUESTION_ANSWER_1));
                String answer2 = res.getString(res.getColumnIndex(QUESTION_ANSWER_2));
                String answer3 = res.getString(res.getColumnIndex(QUESTION_ANSWER_3));

                ArrayList<String> answers = new ArrayList<>();
                answers.add(correctAnswer);
                answers.add(answer1);
                answers.add(answer2);
                answers.add(answer3);


                Question question = Question.createClosedTypeQuestion(questionText, correctAnswer, answers);

                questions.add(question);
            }
        }

        try (Cursor res = getOpenQuestionByCategory(catId, openQuestionsNumber)) {
            while (res.moveToNext()) {
                String questionText = res.getString(res.getColumnIndex(QUESTION_TEXT));

                Question question = Question.createOpenTypeQuestion(questionText);

                questions.add(question);
            }
        }

        return questions;
    }

    public boolean addClosedQuestion(String question, String correctAns, String otherAns1, String otherAns2, String otherAns3, String category) {
        ContentValues cv = new ContentValues();
        cv.put(QUESTION_TEXT, question);
        cv.put(QUESTION_TYPE, CLOSED_TYPE_QUESTION);
        cv.put(QUESTION_ANSWER_CORRECT, correctAns);
        cv.put(QUESTION_ANSWER_1, otherAns1);
        cv.put(QUESTION_ANSWER_2, otherAns2);
        cv.put(QUESTION_ANSWER_3, otherAns3);
        cv.put(QUESTION_CATEGORY, getCategoryID(category));

        long isInserted = writableDB.insert(QUESTIONS_TABLE, null, cv);

        if (isInserted == -1)
            return false;
        else
            return true;
    }

    public boolean addOpenQuestion(String question, String category) {
        ContentValues cv = new ContentValues();
        cv.put(QUESTION_TEXT, question);
        cv.put(QUESTION_TYPE, OPEN_TYPE_QUESTION);
        cv.put(QUESTION_CATEGORY, getCategoryID(category));

        long isInserted = writableDB.insert(QUESTIONS_TABLE, null, cv);

        if (isInserted == -1)
            return false;
        else
            return true;
    }

    //test purposes
    static boolean isPopulated = false;
    public void populateQuestions() {
        if (!isPopulated) {
            addOpenQuestion("Декларирайте обект от тип String в java.", "JAVA");
            addOpenQuestion("Обяснете понятието Garbage collector в Java.", "JAVA");
            addClosedQuestion("Кой интерфейс се използва за сериализиране на обекти?", "Serializable", "Serial", "Comparable", "Byteable", "JAVA");
            addClosedQuestion("Кой интерфейс се използва за сериализиране на обекти?", "Serializable", "Serial", "Comparable", "Byteable", "JAVA");
            addClosedQuestion("С коя ключова дума хвърляме нов Exception в Java?", "throw", "thrown", "Throwable", "throws", "JAVA");
            addClosedQuestion("Kоя ключова дума се използва за синхронизация в Java?", "synchronized", "private", "concurent", "singleton", "JAVA");
            addClosedQuestion("Кой се грижи за непотребните обекти в Java?", "Garbage collector", "finalize()", "System.cleaner()", "free()", "JAVA");
            addClosedQuestion("С коя ключова дума се създава нов референтен тип в Java?", "new", "create", "build", "make", "JAVA");

            addClosedQuestion("Коя е столицата на Канада?", "Отава", "Торонто", "Вашингтон", "София", "География");
            addClosedQuestion("Коя е столицата на Русия?", "Москва", "Минск", "Вашингтон", "София", "География");
            addClosedQuestion("Коя е столицата на България?", "София", "Торонто", "Лондон", "Пловдив", "География");
            addClosedQuestion("Коя е столицата на Аглия?", "Лондон", "Торонто", "Париж", "София", "География");
            addClosedQuestion("На кой полуостров е разположена България?", "Балкански", "Арабски", "Пиринейски", "Апенински", "География");
            addClosedQuestion("Коя е най-източната точка на България?", "нос Шабла", "нос Емине", "устието на река Тимок", "връх Вейката", "География");
            addOpenQuestion("Колко е висок връх Мусала?", "География");
            isPopulated = true;
        }


    }


}
