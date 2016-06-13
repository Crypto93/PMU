package bg.tu_sofia.pmu.project.testsystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bg.tu_sofia.pmu.project.testsystem.persistence.UsersDataSource;
import bg.tu_sofia.pmu.project.testsystem.utils.User;

import static bg.tu_sofia.pmu.project.testsystem.R.string.existing_email_error;
import static bg.tu_sofia.pmu.project.testsystem.R.string.invalid_email_format;
import static bg.tu_sofia.pmu.project.testsystem.R.string.invalid_password_format;
import static bg.tu_sofia.pmu.project.testsystem.R.string.passwords_does_not_match;
import static bg.tu_sofia.pmu.project.testsystem.R.string.successfull_registration;
import static bg.tu_sofia.pmu.project.testsystem.R.string.unsuccessfull_registration;

public class RegisterActivity extends AppCompatActivity {

    private EditText regEmail= null;
    private EditText password = null;
    private EditText confirmPassword = null;

    private String passwordText = null;
    private String confPasswordText = null;
    private String regMailText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init() {

        regEmail = (EditText) findViewById(R.id.registerEmailEditText);
        password = (EditText) findViewById(R.id.registerPassword);
        confirmPassword = (EditText) findViewById(R.id.registerPasswordConfirm);

        Button registerButton = (Button) findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordText = password.getText().toString();
                confPasswordText = confirmPassword.getText().toString();
                regMailText = regEmail.getText().toString();

                UsersDataSource uds = new UsersDataSource(RegisterActivity.this);
                if (isEmailValid() && isPasswordValid()) {
                    if (uds.doesUserExist(regMailText)) {
                        if (uds.insertUser(regMailText, passwordText)) {
                            Toast.makeText(RegisterActivity.this, getStringResource(successfull_registration), Toast.LENGTH_SHORT).show();
                            RegisterActivity.this.finish();
                        } else {
                            Toast.makeText(RegisterActivity.this, getStringResource(unsuccessfull_registration), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, getStringResource(existing_email_error), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean isEmailValid() {

        if (!regMailText.isEmpty() && regMailText.contains("@")) {
            Log.d("DEBUG", "help1 "  + regMailText.toString());
            return true;
        } else {
            Log.d("DEBUG", "help2" + regMailText.toString() + passwordText + confPasswordText);
            Toast.makeText(this, getStringResource(invalid_email_format), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean isPasswordValid() {

        if (passwordText.isEmpty() || passwordText.length() < 4 || confPasswordText.isEmpty() || confPasswordText.length() < 4) {
            Log.d("DEBUG", "help3");
            Toast.makeText(this, getStringResource(invalid_password_format), Toast.LENGTH_SHORT).show();
            return false;
        } else if (!passwordText.equals(confPasswordText)) {
            Log.d("DEBUG", "help4");
            Toast.makeText(this, getStringResource(passwords_does_not_match), Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }

    }

    private String getStringResource(int resource) {
        return getResources().getString(resource);
    }
}
