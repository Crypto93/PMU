package bg.tu_sofia.pmu.project.testsystem;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import bg.tu_sofia.pmu.project.testsystem.utils.Question;
import bg.tu_sofia.pmu.project.testsystem.utils.Test;
import bg.tu_sofia.pmu.project.testsystem.utils.TestBuilder;
import bg.tu_sofia.pmu.project.testsystem.utils.TestSystemConstants;

public class QuestionActivity extends Activity {

    private Test test;

    private Question currentQuestion = null;
    private boolean isCurrentQuestionOpenType = false;
    private LinkedList<Question> unansweredQuestions = null;

    private TextView timeLeftLabel;
    private TextView timeLeft;
    private TextView question;
    private EditText openAnswerEditText;
    private Button submitTestButton;
    private Button nextButton;
    private Button answerButton;
    private RadioGroup radioGroup;

    private RadioButton answer1;
    private RadioButton answer2;
    private RadioButton answer3;
    private RadioButton answer4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        init();

        createTest();

        startTest();

    }

    private void init() {
        timeLeftLabel = (TextView) findViewById(R.id.time_left_label);
        timeLeft = (TextView) findViewById(R.id.time_left);
        question = (TextView) findViewById(R.id.questionTextView);
        openAnswerEditText = (EditText) findViewById(R.id.openAnswerText);

        submitTestButton = (Button) findViewById(R.id.submit_test_button);
        nextButton = (Button) findViewById(R.id.next_button);
        answerButton = (Button) findViewById(R.id.answer_button);

        radioGroup = (RadioGroup) findViewById(R.id.answer_radio_group);

        answer1 = (RadioButton) findViewById(R.id.answer1);
        answer2 = (RadioButton) findViewById(R.id.answer2);
        answer3 = (RadioButton) findViewById(R.id.answer3);
        answer4 = (RadioButton) findViewById(R.id.answer4);

        onClickOfAnswer();
        onClickOfNext();
        onClickOfSubmit();
    }

    private void onClickOfSubmit() {
        submitTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test.testSubmit();
                finishActivityAndCallResults();
            }
        });
    }

    private void onClickOfNext() {
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextUnansweredQuestion();
            }
        });
    }

    private void onClickOfAnswer() {
        answerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unansweredQuestions.remove(currentQuestion);
                if (isCurrentQuestionOpenType) {
                    test.answerQuestion(currentQuestion, openAnswerEditText.getText().toString());
                } else {
                    int selectedID = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = (RadioButton) findViewById(selectedID);
                    test.answerQuestion(currentQuestion, String.valueOf(radioButton.getText()));
                }
                currentQuestion = null;
                nextUnansweredQuestion();
            }
        });
    }

    private void createTest() {
        String category = getIntent().getExtras().getString(TestSystemConstants.CATEGORY_KEY);

        test = TestBuilder.generateTest(this, category, 4, 1);

        if (TestSystemConstants.TIMED_TEST.equals(getIntent().getExtras().getString(TestSystemConstants.IS_TIMED_KEY))) {
            test.setIsTimed(true);
            new CountDownTimer(18000, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    String FORMAT = "%02d:%02d:%02d";
                    timeLeftLabel.setVisibility(View.VISIBLE);
                    timeLeft.setVisibility(View.VISIBLE);
                    timeLeft.setText("" + String.format(FORMAT,
                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                }

                @Override
                public void onFinish() {
                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(QuestionActivity.this);
                    dlgAlert.setMessage(getResources().getString(R.string.times_up_message));
                    dlgAlert.setTitle(getResources().getString(R.string.times_up_label));
                    dlgAlert.setPositiveButton(getResources().getString(R.string.ok_button), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finishActivityAndCallResults();
                        }
                    });
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                }
            }.start();
        } else if (TestSystemConstants.NON_TIMED_TEST.equals(getIntent().getExtras().getString(TestSystemConstants.IS_TIMED_KEY))) {
            test.setIsTimed(false);
            timeLeftLabel.setVisibility(View.INVISIBLE);
            timeLeft.setVisibility(View.INVISIBLE);
        }
    }

    private void startTest() {
        unansweredQuestions = test.getUnansweredQuestions();
        nextUnansweredQuestion();
    }

    private void nextUnansweredQuestion() {
        if (!unansweredQuestions.isEmpty()) {

            Collections.shuffle(unansweredQuestions);

            currentQuestion = unansweredQuestions.peek();
            question.setText(currentQuestion.getQuestion());

            if (currentQuestion.getType() == Question.QUESTION_TYPE.CLOSED) {
                isCurrentQuestionOpenType = false;
                showClosedQuestionsLayout();
                Collections.shuffle(currentQuestion.getAnswers());
                answer1.setText(currentQuestion.getAnswers().get(0));
                answer2.setText(currentQuestion.getAnswers().get(1));
                answer3.setText(currentQuestion.getAnswers().get(2));
                answer4.setText(currentQuestion.getAnswers().get(3));
            } else {
                isCurrentQuestionOpenType = true;
                showOpenQuestionsLayout();
            }
        } else {
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(QuestionActivity.this);
            dlgAlert.setMessage(getResources().getString(R.string.all_answered));
            dlgAlert.setPositiveButton(getResources().getString(R.string.test_end_button), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finishActivityAndCallResults();
                }
            });
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
        }

    }


    private void showClosedQuestionsLayout() {
        openAnswerEditText.setVisibility(View.INVISIBLE);
        radioGroup.setVisibility(View.VISIBLE);
    }

    private void showOpenQuestionsLayout() {
        openAnswerEditText.setVisibility(View.VISIBLE);
        radioGroup.setVisibility(View.INVISIBLE);
    }

    private void finishActivityAndCallResults() {
        test.testSubmit();
        Intent intent = new Intent(QuestionActivity.this, SingleTestSummaryActivity.class);
        QuestionActivity.this.startActivity(intent);
        QuestionActivity.this.finish();
    }


}
