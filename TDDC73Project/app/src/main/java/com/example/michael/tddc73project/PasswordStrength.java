package com.example.michael.tddc73project;

import android.content.Context;
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

public class PasswordStrength extends LinearLayout{

    private PasswordAlgorithm passwordAlgorithm;
    private ProgressBar progressBar;
    private EditText passwordField;
    private TextView passwordStrengthTxt;

    public PasswordStrength(Context context) {
        super(context);
    }

    public PasswordStrength(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

                if(result == 1) {
                    progressBar.getProgressDrawable().setColorFilter(
                            getResources().getColor(R.color.progressWeak), android.graphics.PorterDuff.Mode.SRC_IN);

                    passwordStrengthTxt.setText("Weak");

                }
                else if(result == 2) {
                    progressBar.getProgressDrawable().setColorFilter(
                            getResources().getColor(R.color.progressFair), android.graphics.PorterDuff.Mode.SRC_IN);

                    passwordStrengthTxt.setText("Fair");
                }
                else if(result == 3) {
                    progressBar.getProgressDrawable().setColorFilter(
                            getResources().getColor(R.color.progressStrong), android.graphics.PorterDuff.Mode.SRC_IN);

                    passwordStrengthTxt.setText("Strong");
                }
                else {
                    progressBar.getProgressDrawable().setColorFilter(
                            getResources().getColor(R.color.progressDefault), android.graphics.PorterDuff.Mode.SRC_IN);

                    passwordStrengthTxt.setText("Too short");
                }

            }
        });

    }

    public PasswordStrength(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
