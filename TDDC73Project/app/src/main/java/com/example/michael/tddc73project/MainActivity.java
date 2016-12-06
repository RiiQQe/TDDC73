package com.example.michael.tddc73project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    SignUpForm signUpForm;

    Map<String, String> formMap = new HashMap<String, String>();

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
            public void onSave(Map<String, String> formVals) {
                // Do stuff on save
                formMap.putAll(formVals);
            }
        });
    }
}
