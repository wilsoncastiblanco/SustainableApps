package coursera.org.sustainableapps.presenter;

import android.support.annotation.NonNull;

import coursera.org.sustainableapps.model.User;
import coursera.org.sustainableapps.view.LoginView;

/**
 * Created by wilsoncastiblanco on 4/24/17.
 */

public class LoginPresenter {
    private LoginView loginView;
    private User user;

    public void setView(@NonNull LoginView view) {
        this.loginView = view;
    }

    public void validate(String email, String password) {
        user = new User(password, email);
        if (isValidEmail() && isValidPassword()) {
            loginView.showLoginSuccessMessage();
        }
    }

    private boolean isValidPassword() {
        if (user.isPasswordEmpty()) {
            loginView.showInvalidPasswordMessage();
        } else if (user.isAShortPassword()) {
            loginView.showPasswordTooShortMessage();
        }
        return user.isPasswordValid();
    }

    private boolean isValidEmail() {
        boolean isValidEmail = user.isEmailValid();
        if (!isValidEmail) {
            loginView.showInvalidEmailAddressMessage();
        }
        return isValidEmail;
    }

}
