package com.tddc73.lab1;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // --------------------------------- EX 1 --------------------------------------
        LinearLayout.LayoutParams paramsl1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams params2l1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT);
        params2l1.gravity = Gravity.CENTER;

        LinearLayout.LayoutParams params3l1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT);

        // Create Button that matches parent in width and wraps content in height
        Button myButtonl1 = new Button(this);
        myButtonl1.setText(R.string.buttontext);
        myButtonl1.setLayoutParams(paramsl1);

        EditText myEditTextl1 = new EditText(this);
        myEditTextl1.setLayoutParams(paramsl1);

        RatingBar myRatingBarl1 = new RatingBar(this);
        myRatingBarl1.setNumStars(5);
        myRatingBarl1.setRating(0.0f);
        myRatingBarl1.setLayoutParams(params2l1);

        EditText myEditText2l1 = new EditText(this);
        myEditText2l1.setLayoutParams(params3l1);

        LinearLayout layout1 = new LinearLayout(this);
        layout1.setOrientation(LinearLayout.VERTICAL);
        layout1.addView(myButtonl1);
        layout1.addView(myEditTextl1);
        layout1.addView(myRatingBarl1);
        layout1.addView(myEditText2l1);

        // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ EX 1 ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

        // Run with different layouts
        // EX 1 PROGRAMMICALLY
        //setContentView(layout1);

        // EX 1 XML
        //setContentView(R.layout.layout1);


    }


}

