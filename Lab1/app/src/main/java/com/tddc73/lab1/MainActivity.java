package com.tddc73.lab1;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams params4 = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams params5 = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams params6 = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams params7 = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams params8 = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT);

        TextView tv1 = new TextView(this);
        tv1.setText("Hur trivs du pa liu");
        tv1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tv1.setId(View.generateViewId());
        tv1.setLayoutParams(params);

        CheckBox cb1 = new CheckBox(this);
        cb1.setText("bad");

        CheckBox cb2 = new CheckBox(this);
        cb2.setText("good");

        CheckBox cb3 = new CheckBox(this);
        cb3.setText("excellenteee");

        LinearLayout ll1 = new LinearLayout(this);
        ll1.setId(View.generateViewId());
        params2.addRule(RelativeLayout.BELOW, tv1.getId());
        ll1.setLayoutParams(params2);

        ll1.addView(cb1);
        ll1.addView(cb2);
        ll1.addView(cb3);

        TextView tv2 = new TextView(this);
        tv2.setId(View.generateViewId());
        tv2.setText("laser du pa lith?");
        tv2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        params3.addRule(RelativeLayout.BELOW, ll1.getId());
        tv2.setLayoutParams(params3);

        CheckBox cb4 = new CheckBox(this);
        cb4.setText("yes");

        CheckBox cb5 = new CheckBox(this);
        cb5.setText("no");

        LinearLayout ll2 = new LinearLayout(this);
        ll2.setId(View.generateViewId());
        params4.addRule(RelativeLayout.BELOW, tv2.getId());
        ll2.setLayoutParams(params4);

        ll2.addView(cb4);
        ll2.addView(cb5);

        ImageView iv1 = new ImageView(this);
        params5.addRule(RelativeLayout.BELOW, ll2.getId());
        iv1.setId(View.generateViewId());
        iv1.setImageDrawable(getDrawable(R.drawable.liulogo));
        iv1.setLayoutParams(params5);

        TextView tv3 = new TextView(this);
        params6.addRule(RelativeLayout.BELOW, iv1.getId());
        tv3.setId(View.generateViewId());
        tv3.setText("Ar detta liu loggan");
        tv3.setLayoutParams(params6);
        tv3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        CheckBox cb6 = new CheckBox(this);
        cb6.setText("yes");

        CheckBox cb7 = new CheckBox(this);
        cb7.setText("no");

        LinearLayout ll3 = new LinearLayout(this);
        params7.addRule(RelativeLayout.BELOW, tv3.getId());
        ll3.setLayoutParams(params7);
        ll3.setId(View.generateViewId());

        ll3.addView(cb6);
        ll3.addView(cb7);

        Button myButton = new Button(this);
        params8.addRule(RelativeLayout.BELOW, ll3.getId());
        myButton.setText("Skicka in");
        myButton.setLayoutParams(params8);

        RelativeLayout myLayout = new RelativeLayout(this);
        myLayout.addView(tv1);
        myLayout.addView(ll1);
        myLayout.addView(tv2);
        myLayout.addView(ll2);
        myLayout.addView(iv1);
        myLayout.addView(tv3);
        myLayout.addView(ll3);
        myLayout.addView(myButton);
        setContentView(myLayout);


        // setContentView(R.layout.activity_main);

    }


}

