package bg.tu_sofia.pmu.project.testsystem.utils;

import java.util.ArrayList;

/**
 * Created by Stefan Chuklev on 13.6.2016 г..
 */
public class ClosedTypeQuestion extends Question {

    private String correctAnswer;
    private ArrayList<String> answers;

    public ClosedTypeQuestion(String question, String correctAnswer, ArrayList<String> answers) {
        super(question);
        this.correctAnswer = correctAnswer;
        this.answers = answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }
}
