package com.example.michael.tddc73project;

import android.content.Context;
import android.graphics.Color;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.action.TypeTextAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private static final String weakMessage = "Test";
    private static final String fairMessage = "Test1";
    private static final String strongMessage = "TestTest1";

    @Rule
    public ActivityTestRule<MainActivity> menuActivityTestRule =
            new ActivityTestRule<>(MainActivity.class, true, true);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.michael.tddc73project", appContext.getPackageName());
    }

    @Test
    public void passwordStrengthComponent() {
        //Test that it says weak with 'Test'
        onView(withId(R.id.PasswordTxtField))
                .perform(replaceText(weakMessage));

        onView(withId(R.id.PasswordTxtField))
                .check(matches(withText(weakMessage)));

        onView(withId(R.id.PasswordStrengthTxt))
                .check(matches(withText("Weak")));

        //Test that it says fair with 'Test1'
        onView(withId(R.id.PasswordTxtField))
                .perform(replaceText(fairMessage));

        onView(withId(R.id.PasswordTxtField))
                .check(matches(withText(fairMessage)));

        onView(withId(R.id.PasswordStrengthTxt))
                .check(matches(withText("Fair")));

        //Test that it says strong with 'TestTest1'
        onView(withId(R.id.PasswordTxtField))
                .perform(replaceText(strongMessage));

        onView(withId(R.id.PasswordTxtField))
                .check(matches(withText(strongMessage)));

        onView(withId(R.id.PasswordStrengthTxt))
                .check(matches(withText("Strong")));

        onView(withId(R.id.PasswordTxtField))
                .perform(clearText());
    }

    @Test
    public void signUpFormComponent() {
        // Check if there is a EditText with the hint 'Full name'
        // The use of multiple withChild is because SignUpForm is built up like this tree
        // SignUpForm
        //     |
        //   mainrl
        //     |
        //   mainll
        //     |
        //   ll1
        //     |
        //   fullNameField

        onView(withId(R.id.SignUpForm))
                .check(matches(withChild(withChild(withChild(withChild(withHint("Full name")))))));


        onView(withText(is("save"))).perform(click());

        onView(withId(R.id.SignUpForm))
              .check(matches(withChild(withChild(withChild(withHint("Email *")))))).check(matches(isDisplayed()));


        onView(withHint(is("Email *"))).
                check(matches(ColorMatcher.withBackgroundColor(Color.RED)));

        onView(withHint(is("Password *"))).check(matches(ColorMatcher.withBackgroundColor(Color.RED)));




    }



}
