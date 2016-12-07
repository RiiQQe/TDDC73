package com.example.michael.tddc73project;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class PasswordStrengthTest {
    private static final String weakMessage = "Test";
    private static final String fairMessage = "Test1";
    private static final String strongMessage = "TestTest1";

    @Rule
    public ActivityTestRule<MainActivity> menuActivityTestRule =
            new ActivityTestRule<>(MainActivity.class, true, true);

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
    }

}
