package bg.tu_sofia.pmu.project.testsystem.utils;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

import bg.tu_sofia.pmu.project.testsystem.persistence.QuestionsDataSource;

/**
 * Created by Stefan Chuklev on 13.6.2016 г..
 */
public class TestBuilder {

    public static Test generateTest(Context ctx, int categoryId, int closedQuestionsNumber, int openQuestionsNumber) {
        QuestionsDataSource qds = new QuestionsDataSource(ctx);
        ArrayList<Question> questions = qds.getTestQuestions(closedQuestionsNumber, openQuestionsNumber, categoryId);

        Test test = new Test();
        test.setUserID(CacheControler.getInstance().getUser().getUserID());
        test.setTestID(UUID.randomUUID().toString());
        test.setChecked(false);
        test.setQuestions(questions);

        return test;
    }


}
