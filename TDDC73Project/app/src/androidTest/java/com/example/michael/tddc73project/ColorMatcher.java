package com.example.michael.tddc73project;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.internal.util.Checks;
import android.util.Log;
import android.view.View;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

/**
 * Created by michael on 2016-12-07.
 */

public class ColorMatcher {

    public static Matcher<View> withBackgroundColor(final int color) {
        Checks.checkNotNull(color);
        return new BoundedMatcher<View, EditText>(EditText.class) {
            @Override
            public boolean matchesSafely(EditText et) {

                return color == ((ColorDrawable) et.getBackground()).getColor();
            }
            @Override
            public void describeTo(Description description) {
                description.appendText("with background color: " + color);
            }
        };
    }


}
