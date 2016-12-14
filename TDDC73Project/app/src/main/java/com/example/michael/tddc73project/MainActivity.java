package com.example.michael.tddc73project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    SignUpForm signUpForm;

    public ArrayList<String> formValues = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signUpForm = (SignUpForm) findViewById(R.id.SignUpForm);

        signUpForm.addNameField(false);
        signUpForm.addEmailField(true);
        signUpForm.addGender(true);
        signUpForm.addPasswordField(true);

        signUpForm.setOnSave(new OnSaveListener() {
            @Override
            public void onSave(ArrayList<String> formVals) {
                formValues.addAll(formVals);
            }
        });
    }
}
