package bg.tu_sofia.pmu.project.testsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

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
        Spinner catSpinner = (Spinner) findViewById(R.id.spinner);
    }
}
