package com.example.michael.tddc73project;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class SignUpFormTest {

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

        // Check that a radiobutton with the text 'Male' is rendered
        // Similar tree as above
        onView(withId(R.id.SignUpForm))
                .check(matches(withChild(withChild(withChild(withChild(withChild(withText("Male"))))))));

        // Check if there is a EditText with the hint 'Password *'
        onView(withId(R.id.PasswordTxtField))
                .check(matches(withHint("Password *")));

    }
}
