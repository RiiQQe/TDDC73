package com.example.micke.lab3;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by micke on 2016-11-10.
 */

public class InteractiveSearcher extends EditText {
    private String hint;

    public InteractiveSearcher(Context context) {
        super(context);
        setInteractiveSearcher();
    }

    public InteractiveSearcher(Context context, AttributeSet attrs) {
        super(context, attrs);
        setInteractiveSearcher();
    }

    public InteractiveSearcher(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setInteractiveSearcher();
    }

    private void setInteractiveSearcher() {

    }
}
