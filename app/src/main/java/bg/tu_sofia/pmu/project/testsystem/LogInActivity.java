package bg.tu_sofia.pmu.project.testsystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;
import static bg.tu_sofia.pmu.project.testsystem.R.string.invalid_email_format;
import static bg.tu_sofia.pmu.project.testsystem.R.string.invalid_password_format;

public class LogInActivity extends AppCompatActivity {

    EditText emailText = null;
    EditText passText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        init();

    }

    private void init() {
        emailText = (EditText) findViewById(R.id.emailEditText);
        passText = (EditText) findViewById(R.id.passEditText);

        Button logInbutton = (Button) findViewById(R.id.logInButton);
        logInbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmailValid() && isPasswordValid()) {

                }
            }
        });

        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private boolean isEmailValid() {
        String text = emailText.getText().toString();
        if (!text.isEmpty() && text.contains("@")) {
            return true;
        } else {
            makeErrorToast(getStringResource(invalid_email_format));
            return false;
        }
    }

    private boolean isPasswordValid() {
        String text = passText.getText().toString();
        if (!text.isEmpty() && text.length() > 4) {
            return true;
        } else {
            makeErrorToast(getStringResource(invalid_password_format));
            return false;
        }
    }



    private void makeErrorToast(String message) {
        Toast errorToast = Toast.makeText(LogInActivity.this, message, LENGTH_SHORT);
        errorToast.show();
    }

    private String getStringResource(int resource) {
        return getResources().getString(resource);
    }
}
