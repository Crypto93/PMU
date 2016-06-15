package bg.tu_sofia.pmu.project.testsystem.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

import bg.tu_sofia.pmu.project.testsystem.R;
import bg.tu_sofia.pmu.project.testsystem.persistence.datasources.CategoriesDataSource;
import bg.tu_sofia.pmu.project.testsystem.utils.CacheControler;
import bg.tu_sofia.pmu.project.testsystem.utils.TestSystemConstants;

public class StudentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        init();
    }

    private void init() {

        populateCategoriesSpinner();

        Button testButton = (Button) findViewById(R.id.testButton);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentActivity.this, QuestionActivity.class);
                intent.putExtra(TestSystemConstants.CATEGORY_KEY, getChosenCategory());
                intent.putExtra(TestSystemConstants.IS_TIMED_KEY, TestSystemConstants.NON_TIMED_TEST);
                StudentActivity.this.startActivity(intent);
            }
        });

        Button timedTestButton = (Button) findViewById(R.id.timedTestButton);
        timedTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentActivity.this, QuestionActivity.class);
                intent.putExtra(TestSystemConstants.CATEGORY_KEY, getChosenCategory());
                intent.putExtra(TestSystemConstants.IS_TIMED_KEY, TestSystemConstants.TIMED_TEST);
                StudentActivity.this.startActivity(intent);
            }
        });

        Button studentReferencebutton = (Button) findViewById(R.id.studentReferenceButton);
        studentReferencebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentActivity.this, ResultsListActivity.class);
                intent.putExtra(TestSystemConstants.USER_KEY, CacheControler.getInstance().getUser().getUsername());
                StudentActivity.this.startActivity(intent);
            }
        });

    }

    private void populateCategoriesSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        CategoriesDataSource catDS = new CategoriesDataSource(this);
        ArrayList<String> catList = catDS.getCattegories();
        String[] cats = catList.toArray(new String[catList.size()]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cats);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private String getChosenCategory() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        return spinner.getSelectedItem().toString();
    }
}
