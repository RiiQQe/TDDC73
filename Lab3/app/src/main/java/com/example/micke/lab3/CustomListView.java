package com.example.micke.lab3;

import android.content.ClipData;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CustomListView extends View {

    Paint backgroundPaint, textPaint, linePaint;

    String[] mData = {};

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CustomListView,
                0, 0);

        // TODO: Add nr of searchResults
        try {
            boolean mShowText = a.getBoolean(R.styleable.CustomListView_showText, false);
        } finally {
            a.recycle();
        }
        init();
    }

    private void init() {
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(60.0f);

        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setColor(Color.WHITE);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(Color.BLUE);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int itemHeight = 100;
        float width = 1000.0f;

        for (int i = 0; i < mData.length; i++) {
            String text = mData[i];
            float height = i * itemHeight;
            canvas.drawRect(0, height, width, height + itemHeight, backgroundPaint);
            canvas.drawText(text, 0, height+itemHeight - 20, textPaint);
            if (i > 0)
                canvas.drawLine(10.0f, height, width, height, linePaint);
        }
    }

    public void populate(String [] tempArr) {
        mData = tempArr;
        invalidate();
    }
}
