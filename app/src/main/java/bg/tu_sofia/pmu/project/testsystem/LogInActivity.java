package bg.tu_sofia.pmu.project.testsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bg.tu_sofia.pmu.project.testsystem.persistence.UsersDataSource;
import bg.tu_sofia.pmu.project.testsystem.utils.CacheControler;

import static android.widget.Toast.LENGTH_SHORT;
import static bg.tu_sofia.pmu.project.testsystem.R.string.invalid_email_format;
import static bg.tu_sofia.pmu.project.testsystem.R.string.invalid_password_format;
import static bg.tu_sofia.pmu.project.testsystem.R.string.successfull_login;
import static bg.tu_sofia.pmu.project.testsystem.R.string.wrong_username_or_password;

public class LogInActivity extends AppCompatActivity {

    EditText emailEditText = null;
    EditText passEditText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        init();

    }

    private void init() {
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passEditText = (EditText) findViewById(R.id.passEditText);

        Button logInbutton = (Button) findViewById(R.id.logInButton);
        logInbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmailValid() && isPasswordValid()) {
                    UsersDataSource uds = new UsersDataSource(LogInActivity.this);
                    User user = uds.getUser(emailEditText.getText().toString(), passEditText.getText().toString());
                    if (user == null) {
                        Toast.makeText(LogInActivity.this, getStringResource(wrong_username_or_password), LENGTH_SHORT).show();
                    } else {
                        CacheControler.getInstance().setUser(user);
                        Toast.makeText(LogInActivity.this, getStringResource(successfull_login), LENGTH_SHORT).show();
                        if (user.getUserType() == User.UserType.ADMIN) {
                            Intent intent = new Intent(LogInActivity.this, AdminActivity.class);
                            LogInActivity.this.startActivity(intent);
                        } else {
                            Intent intent = new Intent(LogInActivity.this, StudentActivity.class);
                            LogInActivity.this.startActivity(intent);
                        }
                    }
                }
            }
        });

        Button registrationButton = (Button) findViewById(R.id.registrationButton);
        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
<<<<<<< 949564b51bfe7ade30be883d09f65003229e4008
                Intent intent = new Intent(LogInActivity.this, RegisterActivity.class);
                LogInActivity.this.startActivityForResult(intent, 0);
||||||| merged common ancestors

=======
                Intent intent = new Intent(LogInActivity.this, RegisterActivity.class);
                LogInActivity.this.startActivity(intent);
>>>>>>> Added LogIn and Register functionalities
            }
        });
    }

    private boolean isEmailValid() {
        String text = emailEditText.getText().toString();
        if (!text.isEmpty() && text.contains("@")) {
            return true;
        } else {
            Toast.makeText(this, getStringResource(invalid_email_format), LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean isPasswordValid() {
        String text = passEditText.getText().toString();
        if (!text.isEmpty() && text.length() > 4) {
            return true;
        } else {
            Toast.makeText(this, getStringResource(invalid_password_format), LENGTH_SHORT).show();
            return false;
        }
    }

    private String getStringResource(int resource) {
        return getResources().getString(resource);
    }
}
