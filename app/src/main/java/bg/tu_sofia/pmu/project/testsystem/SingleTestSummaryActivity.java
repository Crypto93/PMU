package bg.tu_sofia.pmu.project.testsystem;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import bg.tu_sofia.pmu.project.testsystem.utils.CacheControler;
import bg.tu_sofia.pmu.project.testsystem.utils.Test;

public class SingleTestSummaryActivity extends Activity {

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

        init();
    }

    private void init() {
        categoryTextView = (TextView) findViewById(R.id.catLabel);
        correctTextView = (TextView) findViewById(R.id.correctLabel);
        wrongTextView = (TextView) findViewById(R.id.wrongLabel);
        percentageTextView = (TextView) findViewById(R.id.percetageLabel);

        icon = (ImageView) findViewById(R.id.imageView);

        printButton = (Button) findViewById(R.id.print_button);
        printButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = takeScreenshot();
                saveBitmap(bitmap);
            }
        });

        homeButton = (Button) findViewById(R.id.home_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingleTestSummaryActivity.this.finish();
            }
        });

        Test test = CacheControler.getInstance().getCurrentTest();

        categoryTextView.setText(CacheControler.getInstance().getCurrentTest().getCategory());
        correctTextView.setText(getResources().getString(R.string.correct_label) + test.getCorrectAnswers());
        wrongTextView.setText(getResources().getString(R.string.wrong_label) + test.getWrongAnswers());
        float percentage;
        try {
            percentage = (test.getCorrectAnswers() * 100) / (test.getCorrectAnswers() + test.getWrongAnswers());
        } catch (ArithmeticException ae) {
            percentage = 0;
        }

        percentageTextView.setText(getResources().getString(R.string.percentage_label) + percentage + "%");

        if (percentage > 50f)
            icon.setImageResource(R.drawable.ok_icon);
        else
            icon.setImageResource(R.drawable.cross_icon);

    }

    public Bitmap takeScreenshot() {
        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    public void saveBitmap(Bitmap bitmap) {
        File imagePath = new File(Environment.getExternalStorageDirectory() + "/screenshot.png");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }
}
