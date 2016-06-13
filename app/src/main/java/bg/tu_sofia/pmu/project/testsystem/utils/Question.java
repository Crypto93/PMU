package bg.tu_sofia.pmu.project.testsystem.utils;

import java.util.ArrayList;

/**
 * Created by Stefan Chuklev on 13.6.2016 г..
 */
public abstract class Question {

    private String question;

    protected Question(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public static ClosedTypeQuestion createClosedTypeQuestion(String question, String correctAnswer, ArrayList<String> answers) {
        return new ClosedTypeQuestion(question, correctAnswer, answers);
    }

    public static OpenTypeQuestion createOpenTypeQuestion(String question) {
        return new OpenTypeQuestion(question);
    }

    public abstract String getCorrectAnswer();
    public abstract ArrayList<String> getAnswers();

}
