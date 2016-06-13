package bg.tu_sofia.pmu.project.testsystem.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Stefan Chuklev on 13.6.2016 г..
 */
public class Test {

    public static enum QUESTION_STATUS {
        UNANSWERED, CORRECT, WRONG, NOT_RATED_OPEN_QUESTION
    }

    private HashMap<Question, QUESTION_STATUS> questions;
    private int testID;
    private long takenOn;
    private int userID;
    private boolean checked = false;
    private int correctAnswers;
    private int wrongAnswers;


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

    // TO DO make open question logic check ++--


}
