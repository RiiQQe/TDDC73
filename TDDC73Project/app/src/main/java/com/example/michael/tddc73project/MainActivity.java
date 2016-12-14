package com.example.michael.tddc73project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * This class serves ass the Main Activity of the program. This is the a test program
 * for the SignUpForm component and the PasswordStrength component.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Variables for the test class.
     */
    SignUpForm signUpForm;
    public ArrayList<String> formValues = new ArrayList<String>();

    /**
     * Creates the test program.
     *
     * @param savedInstanceState saves the state, e.g. when the phone is flipped
     *                           to landscape.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signUpForm = (SignUpForm) findViewById(R.id.SignUpForm);

        signUpForm.addNameField(false);
        signUpForm.addEmailField(true);
        signUpForm.addGender(true);
        signUpForm.addPasswordField(true);

        signUpForm.setOnSave(new SignUpForm.OnSaveListener() {
            @Override
            public void onSave(ArrayList<String> formVals) {
                formValues.addAll(formVals);
            }
        });
    }
}
