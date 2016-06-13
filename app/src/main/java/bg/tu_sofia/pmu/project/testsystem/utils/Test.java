package bg.tu_sofia.pmu.project.testsystem.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Stefan Chuklev on 13.6.2016 г..
 */
public class Test implements Serializable {

    public static enum QUESTION_STATUS {
        UNANSWERED, CORRECT, WRONG, NOT_RATED_OPEN_QUESTION
    }

    public static enum TestType {
        TIMED, NOT_TIMED
    }

    private HashMap<Question, QUESTION_STATUS> questions;

    private String testID;
    private long takenOn;
    private int userID;
    private boolean checked = false;
    private int correctAnswers;
    private int wrongAnswers;
    private boolean isTimed;


    public void addQuestion(Question q) {
        questions.put(q, QUESTION_STATUS.UNANSWERED);
    }

    public void answerQuestion(Question q, String answer) {
        if (q instanceof ClosedTypeQuestion) {
            if (q.getCorrectAnswer().equals(answer))
                questions.put(q, QUESTION_STATUS.CORRECT);
            else
                questions.put(q, QUESTION_STATUS.WRONG);
        } else {
            questions.put(q, QUESTION_STATUS.NOT_RATED_OPEN_QUESTION);
        }
    }

    public void testSubmit() {
        Iterator it = questions.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if (pair.getValue() == QUESTION_STATUS.CORRECT) {
                correctAnswers++;
            } else {
                wrongAnswers++;
            }
        }
    }

    public ArrayList<Question> getOpenQuestions() {
        ArrayList<Question> list = new ArrayList<>();
        Iterator it = questions.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if (pair.getValue() == QUESTION_STATUS.NOT_RATED_OPEN_QUESTION) {
                list.add((Question) pair.getKey());
            }
        }

        return list;
    }

    public void openQuestionCheck(Question q, boolean isCorrect) {
        if (isCorrect) {
            questions.put(q, QUESTION_STATUS.CORRECT);
            correctAnswers++;
        } else {
            questions.put(q, QUESTION_STATUS.WRONG);
            wrongAnswers++;
        }

    }

    public void setQuestions(HashMap<Question, QUESTION_STATUS> questions) {
        this.questions = questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        for (Question q : questions) {
            addQuestion(q);
        }
    }

    public void setTestID(String testID) {
        this.testID = testID;
    }

    public void setTakenOn(long takenOn) {
        this.takenOn = takenOn;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public HashMap<Question, QUESTION_STATUS> getQuestions() {
        return questions;
    }

    public String getTestID() {
        return testID;
    }

    public long getTakenOn() {
        return takenOn;
    }

    public int getUserID() {
        return userID;
    }

    public boolean isChecked() {
        return checked;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }

    public boolean isTimed() {
        return isTimed;
    }

    public void setIsTimed(boolean isTimed) {

        this.isTimed = isTimed;
    }
}
