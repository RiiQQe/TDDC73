package com.tddc73.lab1;

import android.renderscript.Type;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.layout2);

        //Variables for layout2
        TextView textView1 = new TextView(this);
        TextView textView2 = new TextView(this);
        TextView textView3 = new TextView(this);
        TextView textView4 = new TextView(this);

        EditText editText1 = new EditText(this);
        editText1.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        EditText editText2 = new EditText(this);
        editText2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        EditText editText3 = new EditText(this);
        editText3.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        SeekBar seekBar = new SeekBar(this);
        LinearLayout.LayoutParams paramsLayoutSeekBar = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT);
        paramsLayoutSeekBar.setMargins(0,30,0,0);
        seekBar.setLayoutParams(paramsLayoutSeekBar);

        textView1.setText("Namn");
        textView1.setTextSize(20);
        LinearLayout.LayoutParams paramsNamn = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsNamn.setMargins(0,35,0,0);
        textView1.setLayoutParams(paramsNamn);

        textView2.setText("Lösenord");
        textView2.setTextSize(20);
        LinearLayout.LayoutParams paramsPassword = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsPassword.setMargins(0,40,0,0);
        textView2.setLayoutParams(paramsPassword);

        textView3.setText("Email");
        textView3.setTextSize(20);
        LinearLayout.LayoutParams paramsEmail = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT);
        paramsEmail.setMargins(0,40,0,0);
        textView3.setLayoutParams(paramsEmail);

        textView4.setText("Ålder");
        textView4.setTextSize(20);
        LinearLayout.LayoutParams paramsAge = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT);
        paramsAge.setMargins(0,40,0,0);
        textView4.setLayoutParams(paramsAge);

        LinearLayout linearLayout1 = new LinearLayout(this);
        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout linearLayout2 = new LinearLayout(this);
        linearLayout2.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams paramsLayout2l2 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT, 0.4f);
        linearLayout2.setLayoutParams(paramsLayout2l2);

        linearLayout2.addView(textView1);
        linearLayout2.addView(textView2);
        linearLayout2.addView(textView3);
        linearLayout2.addView(textView4);

        LinearLayout linearLayout3 = new LinearLayout(this);
        linearLayout3.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams paramsLayout3l2 = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT, 0.2f);
        linearLayout3.setLayoutParams(paramsLayout3l2);

        linearLayout3.addView(editText1);
        linearLayout3.addView(editText2);
        linearLayout3.addView(editText3);
        linearLayout3.addView(seekBar);

        linearLayout1.addView(linearLayout2);
        linearLayout1.addView(linearLayout3);

        setContentView(linearLayout1);



    }


}

