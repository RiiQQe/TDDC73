package com.example.michael.tddc73project;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by michael on 2016-12-05.
 */

public class PasswordStrength extends LinearLayout {

    private PasswordAlgorithm passwordAlgorithm;
    private ProgressBar progressBar;
    private EditText passwordField;
    private TextView passwordStrengthTxt;

    public PasswordStrength(Context context) {
        super(context);
        init(context);
    }

    public PasswordStrength(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PasswordStrength(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.password_field, this);

        passwordAlgorithm = new PasswordAlgorithm();

        passwordField = (EditText) findViewById(R.id.PasswordTxtField);
        progressBar = (ProgressBar) findViewById(R.id.PasswordProgressBar);
        passwordStrengthTxt = (TextView) findViewById(R.id.PasswordStrengthTxt);

        progressBar.setProgress(0);
        passwordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                int result = passwordAlgorithm.checkPassword(editable.toString());

                progressBar.setProgress(result);

                if (result == 1) {
                    setProgressColor(R.color.progressWeak);

                    passwordStrengthTxt.setText("Weak");
                } else if (result == 2) {
                    setProgressColor(R.color.progressFair);

                    passwordStrengthTxt.setText("Fair");
                } else if (result == 3) {
                    setProgressColor(R.color.progressStrong);

                    passwordStrengthTxt.setText("Strong");
                } else {
                    setProgressColor(R.color.progressDefault);

                    passwordStrengthTxt.setText("Too short");
                }

            }
        });
    }

    public EditText getPasswordField() {
        return passwordField;
    }

    public void addPasswordFieldText(String req) {
        if (passwordField != null) {
            String newHint = passwordField.getHint().toString() + req;
            passwordField.setHint(newHint);
        }
    }

    private void setProgressColor(int color) {
        progressBar.getProgressDrawable().setColorFilter(
                getResources().getColor(color), android.graphics.PorterDuff.Mode.SRC_IN);
    }
}
