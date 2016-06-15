package bg.tu_sofia.pmu.project.testsystem.utils;

import android.content.Context;

import java.util.LinkedList;
import java.util.UUID;

import bg.tu_sofia.pmu.project.testsystem.persistence.datasources.CategoriesDataSource;
import bg.tu_sofia.pmu.project.testsystem.persistence.datasources.QuestionsDataSource;
import bg.tu_sofia.pmu.project.testsystem.persistence.model.Question;

/**
 * Created by Stefan Chuklev on 13.6.2016 г..
 */
public class TestBuilder {

    public static Test generateTest(Context ctx, String category, int closedQuestionsNumber, int openQuestionsNumber) {
        CategoriesDataSource cds = new CategoriesDataSource(ctx);
        QuestionsDataSource qds = new QuestionsDataSource(ctx);

        int categoryId = cds.getCategoryID(category);

        LinkedList<Question> questions = qds.getTestQuestions(closedQuestionsNumber, openQuestionsNumber, categoryId);

        Test test = new Test();
        test.setUser(CacheControler.getInstance().getUser().getUsername());
        test.setTestID(UUID.randomUUID().toString());
        test.setChecked(false);
        test.setQuestions(questions);
        test.setTakenOn(System.currentTimeMillis());
        test.setCategory(category);

        CacheControler.getInstance().setCurrentTest(test);

        return test;
    }


}
