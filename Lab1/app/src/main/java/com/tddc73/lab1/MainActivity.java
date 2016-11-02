package com.tddc73.lab1;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout2);

        //Variables for layout2
        TextView textView1l2 = new TextView(this);
        TextView textView2l2 = new TextView(this);
        TextView textView3l2 = new TextView(this);
        TextView textView4l2 = new TextView(this);

        EditText editText1l2 = new EditText(this);
        EditText editText2l2 = new EditText(this);
        EditText editText3l2 = new EditText(this);

        SeekBar seekBarl2 = new SeekBar(this);

        textView1l2.setText("Namn");
        textView1l2.setTextSize(20);
        LinearLayout.LayoutParams params3l1 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT);
        params3l1.setMargins(0,15,0,0);
        textView1l2.setLayoutParams(params3l1);

        textView2l2.setText("Lösenord");
        textView2l2.setTextSize(20);

        textView3l2.setText("Email");
        textView3l2.setTextSize(20);

        textView4l2.setText("Ålder");
        textView4l2.setTextSize(20);

        LinearLayout linearLayout1l2 = new LinearLayout(this);
        linearLayout1l2.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout linearLayout2l2 = new LinearLayout(this);
        linearLayout2l2.setOrientation(LinearLayout.VERTICAL);

        linearLayout2l2.addView(textView1l2);
        linearLayout2l2.addView(textView2l2);
        linearLayout2l2.addView(textView3l2);
        linearLayout2l2.addView(textView4l2);

        LinearLayout linearLayout3l2 = new LinearLayout(this);
        linearLayout3l2.setOrientation(LinearLayout.VERTICAL);

        linearLayout3l2.addView(editText1l2);
        linearLayout3l2.addView(editText2l2);
        linearLayout3l2.addView(editText3l2);
        linearLayout3l2.addView(seekBarl2);

        linearLayout1l2.addView(linearLayout2l2);
        linearLayout1l2.addView(linearLayout3l2);

        //setContentView(linearLayout1l2);



    }


}

