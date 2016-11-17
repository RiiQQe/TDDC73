package com.example.micke.lab3;

import android.content.ClipData;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.EventLog;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class CustomListView extends View {

    Paint backgroundPaint, textPaint, linePaint;

    List<Item> itemList = new ArrayList<Item>();

    int itemHeight = 100;

    GestureDetector mDetector;

    int nrOfResults;

    String[] mData;

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mDetector = new GestureDetector(CustomListView.this.getContext(), new mListener());

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CustomListView,
                0, 0);

        try {
            //boolean mShowText = a.getBoolean(R.styleable.CustomListView_showText, false);
            nrOfResults = a.getInt(R.styleable.CustomListView_nrOfResults, 5);
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
        float width = 1000.0f;
        itemList.clear();
        int sizeOfDropdown = mData.length;

        if (mData.length > nrOfResults) sizeOfDropdown = nrOfResults;

        for (int i = 0; i < sizeOfDropdown; i++) {
            String text = mData[i];
            float height = i * itemHeight;
            Item itm = new Item(text, 0.0f, height + itemHeight, i);
            itemList.add(itm);
            canvas.drawRect(0, height, width, height + itemHeight, backgroundPaint);
            canvas.drawText(text, 0, height+itemHeight - 20, textPaint);
            if (i > 0)
                canvas.drawLine(10.0f, height, width, height, linePaint);
        }
    }

    public void updateNrOfResults(int k) {
        nrOfResults = k;
        invalidate();
    }

    public void populate(String [] tempArr) {
        mData = tempArr;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = mDetector.onTouchEvent(event);
        if (!result) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                mapTouchToItem(event);
                result = true;
            }
        }
        return result;
    }

    private void mapTouchToItem(MotionEvent event) {
        float yPos = event.getAxisValue(1, 0);

        for (int i = 0; i < itemList.size(); i++) {
            Item itm = itemList.get(i);
            float min = itm.getYMax() - 100.f;
            float max = itm.getYMax();
            if (min < yPos && yPos < max) {
                Log.i("kalle2", "WORKING ? " + itm.s);
            }
        }
    }

}

class mListener extends GestureDetector.SimpleOnGestureListener {
    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }
}

class Item {
    int index;
    float xMax, yMax;
    String s;

    Item(String s, float xMax, float yMax, int index) {
        this.s = s;
        this.index = index;
        this.xMax = xMax;
        this.yMax = yMax;
    }

    float getYMax() {
        return this.yMax;
    }
}
