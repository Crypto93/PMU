package bg.tu_sofia.pmu.project.testsystem.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bg.tu_sofia.pmu.project.testsystem.R;
import bg.tu_sofia.pmu.project.testsystem.persistence.datasources.CategoriesDataSource;

public class AddCategoryActivity extends AppCompatActivity {

    EditText addCatEditText;
    Button addCatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        init();
    }

    private void init() {
        addCatButton = (Button) findViewById(R.id.addCatButton);
        addCatEditText = (EditText) findViewById(R.id.addCatEditText);

        final CategoriesDataSource cds = new CategoriesDataSource(this);

        addCatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    if (cds.insertCategory(addCatEditText.getText().toString())) {
                        Toast.makeText(AddCategoryActivity.this, getResources().getString(R.string.add_category_success), Toast.LENGTH_SHORT).show();
                        AddCategoryActivity.this.finish();
                    } else {
                        Toast.makeText(AddCategoryActivity.this, getResources().getString(R.string.add_category_failure), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddCategoryActivity.this, getResources().getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateFields() {
        if (addCatEditText.getText().toString().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
