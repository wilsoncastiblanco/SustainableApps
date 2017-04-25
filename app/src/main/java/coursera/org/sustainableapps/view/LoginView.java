package coursera.org.sustainableapps.view;

/**
 * Created by wilsoncastiblanco on 4/24/17.
 */

public interface LoginView {
    void showPasswordTooShortMessage();

    void showInvalidPasswordMessage();

    void showInvalidEmailAddressMessage();

    void showLoginSuccessMessage();
}
