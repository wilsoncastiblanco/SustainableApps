package coursera.org.sustainableapps;

import org.junit.Test;

import java.security.spec.ECField;

import coursera.org.sustainableapps.model.User;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class LoginUnitTest {

    /**
     * Test for requirement 6
     */
    @Test
    public void aShortPasswordFails() throws Exception {
        User user = new User("short", "test@coursera.com");
        assertTrue(user.isAShortPassword());
    }

    /**
     * Test for requirement 7
     */
    @Test
    public void anEmptyPasswordFails() throws Exception {
        User user = new User("     ", "test@coursera.com");
        assertTrue(user.isPasswordEmpty());
    }

    /**
     * Test for requirement 8, 9
     */
    @Test
    public void anEmailWithoutSignFails() throws Exception {
        User user = new User("validpassword", "testcoursera.com");
        assertFalse(user.emailHasAtSign());
    }

    /**
     * Test for requirement 8, 9
     */
    @Test
    public void anEmailInvalidLocalPartLengthFails() {
        User user = new User("validpassword", "a@coursera.com");
        assertFalse(user.hasValidCharactersBeforeSign());
    }

    /**
     * Test for requirement 8, 9
     */
    @Test
    public void anEmailInvalidDomainPartFails() {
        User user = new User("validpassword", "test@ac");
        assertFalse(user.hasValidCharactersAfterSign());
    }

    /**
     * Test for requirement 8, 9
     */
    @Test
    public void emailInvalidDomainPartLengthFails() {
        User user = new User("validpassword", "test@a.c");
        assertFalse(user.hasValidCharactersAfterSign());
    }

    /**
     * Test for requirement 11
     */
    @Test
    public void aValidEmailPasses() {
        User user = new User("validpassword", "test@coursera.com");
        assertTrue(user.isEmailValid());
    }

    /**
     * Test for requirement 11
     */
    @Test
    public void aValidPasswordPasses() {
        User user = new User("validpassword", "test@coursera.com");
        assertTrue(user.isPasswordValid());
    }

}