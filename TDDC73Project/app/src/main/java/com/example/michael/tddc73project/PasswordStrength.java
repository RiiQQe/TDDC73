package com.example.michael.tddc73project;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * This consists of all the methods handling the password.
 *
 * @author Rickard Lindstedt
 * @author Michael Sjöström
 */

public class PasswordStrength extends LinearLayout {

    /**
     * Private variables for the PasswordStrength class.
     */
    private ProgressBar progressBar;
    private EditText passwordField;
    private TextView passwordStrengthTxt;
    private Object passwordAlgorithm = null;

    /**
     * Constructor which only takes a context.
     *
     * @param context the activity context.
     */
    public PasswordStrength(Context context) {
        super(context);
        init(context);
    }

    /**
     * Constructor which also takes attributes.
     *
     * @param context   the activity context, sent as first variable.
     * @param algorithm the attributes, sent as second variable.
     */
    public PasswordStrength(Context context, Class algorithm) {
        super(context);
        try {
            passwordAlgorithm = algorithm.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        init(context);
    }

    /**
     * Constructor which takes context, attributes and a style.
     *
     * @param context      the activity context, sent as first variable.
     * @param algorithm    the attributes, sent as second variable.
     * @param defStyleAttr a style.
     */
    public PasswordStrength(Context context, Class algorithm, int defStyleAttr) {
        super(context);
        init(context);

    }

    /**
     * An init function which is called by each constructor. It initiates all variables that are
     * shared among the other methods. It also ads a TextWatcher to the passwordField to be able to
     * check the strength at each added or removed character. The password algorithm class is
     * created dynamically.
     *
     * @param context the activity context.
     */
    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.password_field, this);

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


                Method method = null;
                try {
                    method = passwordAlgorithm.getClass().getDeclaredMethod("checkPassword", String.class);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (SecurityException e) {
                    e.printStackTrace();
                }

                int result = 0;
                try {
                    result = (int) method.invoke(passwordAlgorithm, editable.toString());
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

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

    /**
     * Sets the color of the progressbar.
     *
     * @param color the color of the progressbar.
     */
    private void setProgressColor(int color) {
        progressBar.getProgressDrawable().setColorFilter(
                getResources().getColor(color), android.graphics.PorterDuff.Mode.SRC_IN);
    }

    /**
     * Getter which returns the passwordField EditText.
     *
     * @return the passwordField EditText.
     */
    public EditText getPasswordField() {
        return passwordField;
    }

    /**
     * Adds the specified text as a hint. This is used to add a "*" if the passwordField is
     * comulsory.
     *
     * @param req the text to be added to the hint.
     */
    public void addPasswordFieldText(String req) {
        if (passwordField != null) {
            String newHint = passwordField.getHint().toString() + req;
            passwordField.setHint(newHint);
        }
    }
}
