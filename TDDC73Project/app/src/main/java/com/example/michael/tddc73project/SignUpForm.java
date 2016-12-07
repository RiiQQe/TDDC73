package com.example.michael.tddc73project;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SignUpForm extends LinearLayout {

    LinearLayout mainll;

    String required = " *";

    private Button save;
    private EditText fullNameField, emailField;
    private HashMap<EditText, Boolean> map;
    private ArrayList<String> formValues = new ArrayList<String>();

    private RadioGroup genderRadio;
    private RadioButton male, female, other;

    private int hintColor;
    private int textColor;
    private int backgroundColor;
    private Drawable originalDrawable;

    private OnSaveListener onSaveListener;
    public interface OnSaveListener{
        public void onSave(ArrayList<String> formVals);
    }

    // Set a listener for our interface
    public void setOnSave(OnSaveListener listener) {
        onSaveListener = listener;

        save.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkAllFields()) onSaveListener.onSave(formValues);
            }
        });
    }

    private boolean checkAllFields() {
        formValues.clear();

        if (genderRadio != null) {
            RadioButton gender = (RadioButton) findViewById(genderRadio.getCheckedRadioButtonId());
            formValues.add(gender.getText().toString());
        }




        boolean shouldPassToActivity = true;
        for(Map.Entry<EditText, Boolean> entry : map.entrySet()) {
            if (!checkFieldColor(entry.getKey(), entry.getValue())) shouldPassToActivity = false;
            if (shouldPassToActivity) formValues.add(entry.getKey().getText().toString());
        }

        return shouldPassToActivity;

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

    public void addNameField(final boolean compulsory) {
        LinearLayout ll1 = new LinearLayout(getContext());
        ll1.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));
        fullNameField = new EditText(getContext());

        if(compulsory)
            addTextChangeListener(fullNameField);

        String req = compulsory ? required : "";

        fullNameField.setHint("Full name" + req);
        fullNameField.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,ActionBar.LayoutParams.MATCH_PARENT));

        ll1.addView(fullNameField);
        mainll.addView(ll1);
        map.put(fullNameField, compulsory);
    }

    public void addPasswordField( boolean compulsory) {
        //TODO: CHANGE TO CUSTOM PASSWORD WITH STRENGTH

        PasswordStrength ps = new PasswordStrength(getContext());

        EditText passwordField = ps.getPasswordField();

        if(compulsory)
            addTextChangeListener(passwordField);

        String req = compulsory ? required : "";

        ps.addPasswordFieldText(req);

        //password.setHint("Password" + req);
        //password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        mainll.addView(ps);
        map.put(passwordField, compulsory);
    }

    private void addTextChangeListener(final EditText et) {

        final Drawable orgDrawable = et.getBackground();

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() != 0) {
                    et.setBackgroundDrawable(orgDrawable);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void init() {
        mainll = new LinearLayout(getContext());
        mainll.setId(View.generateViewId());
        mainll.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));
        mainll.setOrientation(LinearLayout.VERTICAL);

        map = new HashMap<>();

        EditText temp = new EditText(getContext());
        originalDrawable = temp.getBackground();

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

    public void addEmailField( boolean compulsory) {
        EditText emailField = new EditText(getContext());
        emailField.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        if(compulsory)
            addTextChangeListener(emailField);

        String req = compulsory ? required : "";

        emailField.setHint("Email" + req);

        mainll.addView(emailField);
        map.put(emailField, compulsory);
    }


    public void addGender(boolean compulsory) {
        genderRadio = new RadioGroup(getContext());
        LinearLayout ll = new LinearLayout(getContext());
        ll.setOrientation(LinearLayout.HORIZONTAL);

        TextView gender = new TextView(getContext());
        gender.setTextSize(20);

        gender.setPadding(0,15,0,0);

        String req = compulsory ? required : "";
        gender.setText("Gender:" + req);

        genderRadio.setOrientation(RadioGroup.HORIZONTAL);

        male = new RadioButton(getContext());
        male.setText("Male");
        male.setId(View.generateViewId());

        female = new RadioButton(getContext());
        female.setText("Female");

        other = new RadioButton(getContext());
        other.setText("Other");

        genderRadio.addView(male);
        genderRadio.addView(female);
        genderRadio.addView(other);

        genderRadio.check(male.getId());

        ll.addView(gender);
        ll.addView(genderRadio);

        mainll.addView(ll);
    }

    public boolean checkFieldColor(EditText et, Boolean bool) {

        if(et.getHint().toString().contains("Email") && bool && !isValidEmail(et.getText().toString())) {
            et.setBackground(getResources().getDrawable(R.color.progressWeak));
            return false;

        }
        else if(et.getText().length() == 0 && bool == true) {
            et.setBackground(getResources().getDrawable(R.color.progressWeak));
            return false;
        } else
            return true;


    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
