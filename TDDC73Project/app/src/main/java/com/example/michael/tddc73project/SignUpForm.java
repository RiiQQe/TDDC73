package com.example.michael.tddc73project;

import android.app.ActionBar;
import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by michael on 2016-11-29.
 */

public class SignUpForm extends LinearLayout {

    LinearLayout mainll;

    String required = " *";

    private Button save;
    private EditText fullNameField, emailField;

    private OnSaveListener onSaveListener;
    public interface OnSaveListener{
        public void onSave(Map<String, String> formVals);
    }

    // Set a listener for our interface
    public void setOnSave(OnSaveListener listener) {
        onSaveListener = listener;

        save.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> formVals = new HashMap<String, String>();
                formVals.put("kalle", "test");

                formVals.put("kalle2", fullNameField.getText().toString());

                onSaveListener.onSave(formVals);
            }
        });
    }

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
        fullNameField = new EditText(getContext());

        String req = compulsory ? required : "";

        fullNameField.setHint("Full name" + req);
        fullNameField.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,ActionBar.LayoutParams.MATCH_PARENT));

        ll1.addView(fullNameField);
        mainll.addView(ll1);
    }

    public void addPasswordField(boolean compulsory) {
        //TODO: CHANGE TO CUSTOM PASSWORD WITH STRENGTH

        PasswordStrength ps = new PasswordStrength(getContext());

        //PasswordStrength ps = (PasswordStrength) findViewById(R.id.passwordStrength);

        //TODO: ADD THIS TO THE ARRAYLIST
        EditText passwordField = ps.getPasswordField();

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

        save = new Button(getContext());

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
