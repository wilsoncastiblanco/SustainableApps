package coursera.org.sustainableapps.model;

/**
 * Created by wilsoncastiblanco on 4/24/17.
 */

public class User {

    private final String password;
    private final String email;


    public User(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public boolean isPasswordEmpty() {
        return this.password.trim().isEmpty();
    }

    public boolean isAShortPassword() {
        return this.password.trim().length() < 8;
    }

    public boolean emailHasAtSign() {
        return this.email.contains("@");
    }

    public boolean hasValidCharactersBeforeSign() {
        int indexOfSign = this.email.indexOf("@");
        String beforeCharacters = this.email.substring(0, indexOfSign);
        return beforeCharacters.length() > 1;
    }

    public boolean hasValidCharactersAfterSign() {
        int indexOfSign = this.email.indexOf("@");
        String afterCharacters = this.email.substring(indexOfSign, email.length() - 1);
        return afterCharacters.contains(".") && afterCharacters.length() > 3;
    }

    public boolean isEmailValid() {
        return emailHasAtSign() && hasValidCharactersAfterSign() && hasValidCharactersBeforeSign();
    }

    public boolean isPasswordValid() {
        return !isPasswordEmpty() && !isAShortPassword();
    }
}
