package com.example.michael.tddc73project;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by michael on 2016-11-29.
 */

public class SignUpForm extends LinearLayout {

    public SignUpForm(Context ctx) {
        super(ctx);
    }
    public SignUpForm(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
    }
    public SignUpForm(Context ctx, AttributeSet attrs, int defStyle) {
        super(ctx, attrs, defStyle);
    }

    public void addEditableText(String hint) {
        EditText editText = new EditText(getContext());
    }
}
