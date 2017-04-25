package coursera.org.sustainableapps;

import android.support.design.widget.TextInputLayout;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import coursera.org.sustainableapps.view.LoginActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * Tests for requirement 12
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LoginEspressoTest {
    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(LoginActivity.class);

    /**
     * Test for requirement 6
     */
    @Test
    public void passwordIsShort() {
        onView(withId(R.id.editTextEmail)).perform(typeText("test@coursera.com"), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword)).perform(typeText("short"), closeSoftKeyboard());
        onView(withId(R.id.buttonLogin)).perform(click());
        onView(withId(R.id.textInputLayoutPassword)).check(matches(withError("The provided password is too short")));
    }

    /**
     * Test for requirement 7
     */
    @Test
    public void passwordIsInvalid() {
        onView(withId(R.id.editTextEmail)).perform(typeText("test@coursera.com"), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword)).perform(typeText("     "), closeSoftKeyboard());
        onView(withId(R.id.buttonLogin)).perform(click());
        onView(withId(R.id.textInputLayoutPassword)).check(matches(withError("The provided password is invalid")));
    }

    /**
     * Test for requirement 8, 9
     */
    @Test
    public void emailDoesNotHaveSign() {
        onView(withId(R.id.editTextEmail)).perform(typeText("testcoursera.com"), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword)).perform(typeText("thisisavalidpassword"), closeSoftKeyboard());
        onView(withId(R.id.buttonLogin)).perform(click());
        onView(withId(R.id.textInputLayoutEmail)).check(matches(withError("Invalid email address")));
    }

    /**
     * Test for requirement 8, 9
     */
    @Test
    public void emailInvalidLocalPartLength() {
        onView(withId(R.id.editTextEmail)).perform(typeText("t@coursera.com"), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword)).perform(typeText("thisisavalidpassword"), closeSoftKeyboard());
        onView(withId(R.id.buttonLogin)).perform(click());
        onView(withId(R.id.textInputLayoutEmail)).check(matches(withError("Invalid email address")));
    }

    /**
     * Test for requirement 8, 9
     */
    @Test
    public void emailInvalidDomainPart() {
        onView(withId(R.id.editTextEmail)).perform(typeText("test@a"), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword)).perform(typeText("thisisavalidpassword"), closeSoftKeyboard());
        onView(withId(R.id.buttonLogin)).perform(click());
        onView(withId(R.id.textInputLayoutEmail)).check(matches(withError("Invalid email address")));
    }

    /**
     * Test for requirement 8, 9
     */
    @Test
    public void emailInvalidDomainPartLength() {
        onView(withId(R.id.editTextEmail)).perform(typeText("test@a.c"), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword)).perform(typeText("thisisavalidpassword"), closeSoftKeyboard());
        onView(withId(R.id.buttonLogin)).perform(click());
        onView(withId(R.id.textInputLayoutEmail)).check(matches(withError("Invalid email address")));
    }

    /**
     * Test for requirement 11
     */
    @Test
    public void loginSuccessfully_shouldShowToast() {
        onView(withId(R.id.editTextEmail)).perform(typeText("test@coursera.com"), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword)).perform(typeText("thisisavalidpassword"), closeSoftKeyboard());
        onView(withId(R.id.buttonLogin)).perform(click());

        onView(withText("Login success"))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    /**
     * Test for requirement 15
     */
    @Test
    public void checkPasswordInputType() {
        onView(withId(R.id.editTextPassword)).check(matches(withPasswordInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD)));
    }

    private static TypeSafeMatcher<View> withPasswordInputType(final int inputTypePassword) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                return item instanceof EditText && ((EditText) item).getInputType() == inputTypePassword + 1;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Not found error message [" + inputTypePassword + "]");
            }
        };
    }

    private static TypeSafeMatcher<View> withError(final String expected) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                return item instanceof TextInputLayout && ((TextInputLayout) item).getError().toString().equals(expected);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Not found error message [" + expected + "]");
            }
        };
    }
}
