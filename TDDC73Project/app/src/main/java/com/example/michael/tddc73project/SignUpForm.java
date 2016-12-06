package com.example.michael.tddc73project;

import android.app.ActionBar;
import android.app.Notification;
import android.content.Context;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputContentInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Created by michael on 2016-11-29.
 */

public class SignUpForm extends LinearLayout {

    LinearLayout mainll;

    String required = " *";

    public SignUpForm(Context ctx) {
        super(ctx);
        init();
    }

    public SignUpForm(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
        init();
    }
    public SignUpForm(Context ctx, AttributeSet attrs, int defStyle) {
        super(ctx, attrs, defStyle);
        init();
    }

    public void addNameField(boolean compulsory) {
        LinearLayout ll1 = new LinearLayout(getContext());
        ll1.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));
        EditText fullName = new EditText(getContext());

        String req = compulsory ? required : "";

        fullName.setHint("Full name" + req);
        fullName.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,ActionBar.LayoutParams.MATCH_PARENT));

        ll1.addView(fullName);
        mainll.addView(ll1);
    }

    public void addPasswordField(boolean compulsory) {
        //TODO: CHANGE TO CUSTOM PASSWORD WITH STRENGTH

        PasswordStrength ps = new PasswordStrength(getContext());

        //PasswordStrength ps = (PasswordStrength) findViewById(R.id.passwordStrength);

        String req = compulsory ? required : "";

        //password.setHint("Password" + req);
        //password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        mainll.addView(ps);
    }

    private void init() {
        mainll = new LinearLayout(getContext());
        mainll.setId(View.generateViewId());
        mainll.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));
        mainll.setOrientation(LinearLayout.VERTICAL);

        RelativeLayout mainrl = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT);

        params.addRule(RelativeLayout.BELOW, mainll.getId());

        Button save = new Button(getContext());
        save.setLayoutParams(params);
        save.setText("save");

        mainrl.addView(mainll);
        mainrl.addView(save);
        addView(mainrl);
    }

    public void addEditableText(String hint) {
        EditText editText = new EditText(getContext());

        editText.setHint(hint);
        mainll.addView(editText);
    }

    public void addEmailField(boolean compulsory) {
        EditText emailField = new EditText(getContext());
        emailField.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        String req = compulsory ? required : "";

        emailField.setHint("Email" + req);

        mainll.addView(emailField);
    }


    public void addGender(boolean compulsory) {
        RadioGroup genderRadio = new RadioGroup(getContext());

        LinearLayout ll = new LinearLayout(getContext());
        ll.setOrientation(LinearLayout.HORIZONTAL);

        TextView gender = new TextView(getContext());
        gender.setTextSize(20);
        String req = compulsory ? required : "";
        gender.setText("Gender:" + req);

        genderRadio.setOrientation(RadioGroup.HORIZONTAL);

        RadioButton male = new RadioButton(getContext());
        male.setText("Male");

        RadioButton female = new RadioButton(getContext());
        female.setText("Female");

        RadioButton other = new RadioButton(getContext());
        other.setText("Other");

        genderRadio.addView(male);
        genderRadio.addView(female);
        genderRadio.addView(other);

        ll.addView(gender);
        ll.addView(genderRadio);

        mainll.addView(ll);
    }
}
