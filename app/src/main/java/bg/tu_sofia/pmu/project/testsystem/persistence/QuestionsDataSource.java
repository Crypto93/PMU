package bg.tu_sofia.pmu.project.testsystem.persistence;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import java.util.ArrayList;

import bg.tu_sofia.pmu.project.testsystem.utils.ClosedTypeQuestion;
import bg.tu_sofia.pmu.project.testsystem.utils.OpenTypeQuestion;
import bg.tu_sofia.pmu.project.testsystem.utils.Question;

/**
 * Created by Stefan Chuklev on 12.6.2016 г..
 */
public class QuestionsDataSource extends DBHelper {
    public QuestionsDataSource(Context context) {
        super(context);
    }

    private Cursor getClosedQuestionByCategory(int catId, int numberOfQuestions) {
        SQLiteDatabase db = getReadableDatabase();
        String[] params = new String[]{String.valueOf(catId), String.valueOf(numberOfQuestions)};
        Cursor res = db.rawQuery(SELECT_RANDOM_CLOSED_QUESTIONS_BY_CAT, params);

        return res;
    }

    private Cursor getOpenQuestionByCategory(int catId, int numberOfQuestions) {
        SQLiteDatabase db = getReadableDatabase();
        String[] params = new String[]{String.valueOf(catId), String.valueOf(numberOfQuestions)};
        Cursor res = db.rawQuery(SELECT_RANDOM_OPEN_QUESTIONS_BY_CAT, params);

        return res;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public ArrayList<Question> getTestQuestions(int closedQuestionsNumber, int openQuestionsNumber, int catId) {
        ArrayList<Question> questions = new ArrayList<>();

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


                Question question = new ClosedTypeQuestion(questionText, correctAnswer, answers);

                questions.add(question);
            }
        }

        try (Cursor res = getOpenQuestionByCategory(catId, openQuestionsNumber)) {
            while (res.moveToNext()) {
                String questionText = res.getString(res.getColumnIndex(QUESTION_TEXT));

                Question question = new OpenTypeQuestion(questionText);

                questions.add(question);
            }
        }

        return questions;
    }
}
