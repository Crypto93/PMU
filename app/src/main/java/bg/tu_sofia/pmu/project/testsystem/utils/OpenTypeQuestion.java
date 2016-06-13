package bg.tu_sofia.pmu.project.testsystem.utils;

import java.util.ArrayList;

/**
 * Created by Stefan Chuklev on 13.6.2016 г..
 */
public class OpenTypeQuestion extends Question {

    private String openAnswer;

    public OpenTypeQuestion(String question) {
        super(question);
    }

    @Override
    public String getCorrectAnswer() {
        // do nothing
        return null;
    }

    @Override
    public ArrayList<String> getAnswers() {
        // do nothing
        return null;
    }

    public String getOpenAnswer() {
        return openAnswer;
    }

    public void setOpenAnswer(String openAnswer) {
        this.openAnswer = openAnswer;
    }
}
