package com.example.micke.lab3;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by micke on 2016-11-10.
 */

public class InteractiveSearcher extends EditText{
    private String hint;

    public InteractiveSearcher(Context context) {
        super(context);
        setInteractiveSearcher();
    }

    public InteractiveSearcher(Context context, AttributeSet attrs) {
        super(context, attrs);
        setInteractiveSearcher();
        // retrieved values correspond to the positions of the attributes
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.InteractiveSearcher);
        int count = typedArray.getIndexCount();

        try {

            for (int i = 0; i < count; ++i) {

                int attr = typedArray.getIndex(i);
                // the attr corresponds to the hint attribute
                if (attr == R.styleable.InteractiveSearcher_hint) {
                    hint = typedArray.getString(attr);
                    setHint(hint);
                }
            }
        }
            // the recycle() will be executed obligatorily
            finally{
                // for reuse
                typedArray.recycle();
            }

    }

    public InteractiveSearcher(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setInteractiveSearcher();
    }

    private void setInteractiveSearcher() {

    }


}
