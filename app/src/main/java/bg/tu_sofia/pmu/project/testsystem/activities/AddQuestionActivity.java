package bg.tu_sofia.pmu.project.testsystem.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import bg.tu_sofia.pmu.project.testsystem.R;
import bg.tu_sofia.pmu.project.testsystem.persistence.datasources.CategoriesDataSource;
import bg.tu_sofia.pmu.project.testsystem.persistence.datasources.QuestionsDataSource;

public class AddQuestionActivity extends Activity {

    private EditText questionEditText;
    private EditText correctAnswerEditText;
    private EditText otherAnswer1EditText;
    private EditText otherAnswer2EditText;
    private EditText otherAnswer3EditText;
    private Spinner questionTypeSpinner;
    private Spinner catSpinner;
    private Button addQuestionbutton;

    private String questionText;
    private String correctAnswerText;
    private String otherAnswer1Text;
    private String otherAnswer2Text;
    private String otherAnswer3Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        init();
    }

    private void init() {
        questionEditText = (EditText) findViewById(R.id.addQuestionText);
        correctAnswerEditText = (EditText) findViewById(R.id.correctAnswerText);
        otherAnswer1EditText = (EditText) findViewById(R.id.otherAnswerText1);
        otherAnswer2EditText = (EditText) findViewById(R.id.otherAnswerText2);
        otherAnswer3EditText = (EditText) findViewById(R.id.otherAnswerText3);
        questionTypeSpinner = (Spinner) findViewById(R.id.question_types_spinner);
        catSpinner = (Spinner) findViewById(R.id.catSpinner);
        addQuestionbutton = (Button) findViewById(R.id.addQuestionB);

        populateQuestionTypeSpinner();
        populateCategoriesSpinner();

        addQuestionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionText = questionEditText.getText().toString();
                correctAnswerText = correctAnswerEditText.getText().toString();
                otherAnswer1Text = otherAnswer1EditText.getText().toString();
                otherAnswer2Text = otherAnswer2EditText.getText().toString();
                otherAnswer3Text = otherAnswer3EditText.getText().toString();
                String category = getChosenCategory();

                QuestionsDataSource qds = new QuestionsDataSource(AddQuestionActivity.this);

                if (getChosenType().equals(getResources().getString(R.string.close_question))) {
                    if (validateFields()) {
                        if (qds.addClosedQuestion(questionText, correctAnswerText, otherAnswer1Text, otherAnswer2Text, otherAnswer3Text, category)) {
                            Toast.makeText(AddQuestionActivity.this, getResources().getString(R.string.add_question_success), Toast.LENGTH_SHORT);
                            AddQuestionActivity.this.finish();
                        } else {
                            Toast.makeText(AddQuestionActivity.this, getResources().getString(R.string.add_question_failure), Toast.LENGTH_SHORT);
                        }
                    } else {
                        Toast.makeText(AddQuestionActivity.this, getResources().getString(R.string.empty_fields), Toast.LENGTH_SHORT);
                    }
                } else if (getChosenType().equals(getResources().getString(R.string.open_question))) {
                    if (validateFields()) {
                        if (qds.addOpenQuestion(questionText, category)) {
                            Toast.makeText(AddQuestionActivity.this, getResources().getString(R.string.add_question_success), Toast.LENGTH_SHORT);
                            AddQuestionActivity.this.finish();
                        } else {
                            Toast.makeText(AddQuestionActivity.this, getResources().getString(R.string.add_question_failure), Toast.LENGTH_SHORT);
                        }
                    } else {
                        Toast.makeText(AddQuestionActivity.this, getResources().getString(R.string.empty_fields), Toast.LENGTH_SHORT);
                    }
                }
            }
        });

        questionTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (questionTypeSpinner.getSelectedItem().equals(getResources().getString(R.string.close_question))) {
                    showAnswerFields();
                } else if (questionTypeSpinner.getSelectedItem().equals(getResources().getString(R.string.open_question))) {
                    hideAndNullAnswerFields();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private boolean validateFields() {
        if (questionText.isEmpty()) {
            return false;
        } else if (questionText.isEmpty() || correctAnswerText.isEmpty() || otherAnswer1Text.isEmpty() || otherAnswer2Text.isEmpty() || otherAnswer3Text.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    private void hideAndNullAnswerFields() {
        correctAnswerEditText.setVisibility(View.INVISIBLE);
        otherAnswer1EditText.setVisibility(View.INVISIBLE);
        otherAnswer2EditText.setVisibility(View.INVISIBLE);
        otherAnswer3EditText.setVisibility(View.INVISIBLE);

        correctAnswerEditText.setText(null);
        otherAnswer1EditText.setText(null);
        otherAnswer2EditText.setText(null);
        otherAnswer3EditText.setText(null);
    }

    private void showAnswerFields() {
        correctAnswerEditText.setVisibility(View.VISIBLE);
        otherAnswer1EditText.setVisibility(View.VISIBLE);
        otherAnswer2EditText.setVisibility(View.VISIBLE);
        otherAnswer3EditText.setVisibility(View.VISIBLE);
    }

    private void populateQuestionTypeSpinner() {
        final String[] qTypeSpinnerStrings = getResources().getStringArray(R.array.question_types);
        ArrayList<String> qTypeSpinnerStringsList = new ArrayList<>(Arrays.asList(qTypeSpinnerStrings));
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, qTypeSpinnerStringsList);
        questionTypeSpinner.setAdapter(spinnerAdapter);
    }

    private String getChosenType() {
        return catSpinner.getSelectedItem().toString();
    }

    private void populateCategoriesSpinner() {
        CategoriesDataSource catDS = new CategoriesDataSource(this);
        ArrayList<String> catList = catDS.getCattegories();
        String[] cats = catList.toArray(new String[catList.size()]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cats);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        catSpinner.setAdapter(adapter);
    }

    private String getChosenCategory() {
        return catSpinner.getSelectedItem().toString();
    }
}
