package bg.tu_sofia.pmu.project.testsystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import bg.tu_sofia.pmu.project.testsystem.persistence.CategoriesDataSource;

public class StudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        init();
    }

    private void init() {

        populateCattegoriesSpinner();

        Button testButton = (Button) findViewById(R.id.testButton);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StudentActivity.this, getChosenCategory(), Toast.LENGTH_LONG).show();
            }
        });

        Button timedTestButton = (Button) findViewById(R.id.timedTestButton);
        timedTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void populateCattegoriesSpinner() {
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
