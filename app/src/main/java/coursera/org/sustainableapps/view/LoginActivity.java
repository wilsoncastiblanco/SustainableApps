package coursera.org.sustainableapps.view;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import coursera.org.sustainableapps.R;
import coursera.org.sustainableapps.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private LoginPresenter loginPresenter;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        this.loginPresenter = new LoginPresenter();
        this.loginPresenter.setView(this);
        setUpUi();
    }

    private void setUpUi() {
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        Button loginButton = (Button) findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textInputLayoutEmail.setErrorEnabled(false);
                textInputLayoutPassword.setErrorEnabled(false);

                loginPresenter.validate(textInputLayoutEmail.getEditText().getText().toString(),
                        textInputLayoutPassword.getEditText().getText().toString());
            }
        });
    }

    @Override
    public void showPasswordTooShortMessage() {
        textInputLayoutPassword.setError(getString(R.string.login_error_password_too_short));
    }

    @Override
    public void showInvalidPasswordMessage() {
        textInputLayoutPassword.setError(getString(R.string.login_error_password_invalid));
    }

    @Override
    public void showInvalidEmailAddressMessage() {
        textInputLayoutEmail.setError(getString(R.string.login_error_email_invalid));
    }

    @Override
    public void showLoginSuccessMessage() {
        Toast.makeText(getApplicationContext(), getString(R.string.login_success), Toast.LENGTH_SHORT).show();
    }
}
