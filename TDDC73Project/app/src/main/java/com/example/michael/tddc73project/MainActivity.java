package com.example.michael.tddc73project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    SignUpForm signUpForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        signUpForm = (SignUpForm) findViewById(R.id.SignUpForm);

        signUpForm.addEditableText("Extra TextField");

        setContentView(R.layout.activity_main);
    }
}
