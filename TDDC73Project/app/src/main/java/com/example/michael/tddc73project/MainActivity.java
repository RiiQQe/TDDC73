package com.example.michael.tddc73project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    SignUpForm signUpForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signUpForm = (SignUpForm) findViewById(R.id.SignUpForm);

        signUpForm.addNameField(false);
        signUpForm.addEmailField(true);
        signUpForm.addGender(true);
        signUpForm.addPasswordField(true);
    }
}
