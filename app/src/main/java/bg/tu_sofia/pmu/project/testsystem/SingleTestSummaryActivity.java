package bg.tu_sofia.pmu.project.testsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleTestSummaryActivity extends AppCompatActivity {

    private TextView categoryTextView;
    private TextView correctTextView;
    private TextView wrongTextView;
    private TextView percentageTextView;

    private ImageView icon;

    private Button printButton;
    private Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_test_summary);
    }

    private void init() {

    }
}
