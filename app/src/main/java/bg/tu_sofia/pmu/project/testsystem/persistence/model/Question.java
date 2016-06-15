package bg.tu_sofia.pmu.project.testsystem.persistence.model;

import java.util.ArrayList;

/**
 * Created by Stefan Chuklev on 13.6.2016 г..
 */
public class Question {

    public static enum QUESTION_TYPE {
        OPEN, CLOSED
    }

    private String question;
    private QUESTION_TYPE type;

    // closed type
    private String correctAnswer;
    private ArrayList<String> answers;

    // Open type
    private String openAnswer;

    private Question(String question) {
        this.question = question;
    }

    private Question(String question, String correctAnswer, ArrayList<String> answers) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.answers = answers;
        this.type = QUESTION_TYPE.CLOSED;
    }


    public String getQuestion() {
        return question;
    }

    public static Question createClosedTypeQuestion(String question, String correctAnswer, ArrayList<String> answers) {
        return new Question(question, correctAnswer, answers);
    }

    public static Question createOpenTypeQuestion(String question) {
        return new Question(question);
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public String getOpenAnswer() {
        return openAnswer;
    }

    public void setOpenAnswer(String openAnswer) {
        this.openAnswer = openAnswer;
    }

    public QUESTION_TYPE getType() {
        return type;
    }
}
