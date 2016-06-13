package bg.tu_sofia.pmu.project.testsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        init();
    }

    private void init() {
        Button addQuestionButton = (Button) findViewById(R.id.addQuestionButton);
        addQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, AddQuestionActivity.class);
                AdminActivity.this.startActivity(intent);
            }
        });

        Button addCategoryButton = (Button) findViewById(R.id.addCatB);
        addCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, AddCategoryActivity.class);
                AdminActivity.this.startActivity(intent);
            }
        });


        Button referenceButton = (Button) findViewById(R.id.referenceButton);
        referenceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // To be implemnted
            }
        });


    }
}
