package com.example.michael.tddc73project;

import android.util.Log;

/**
 * Created by michael on 2016-12-05.
 */

public class PasswordAlgorithm {

    public PasswordAlgorithm(){

    }

    public int checkPassword(String password){

        int total = 0;
        boolean containsUpperCase = false;
        boolean containsNumber = false;

        //Control length
        if(password.length() > 8) {
            total++;
        }

        //Control if password contains upper case and digit
        for(int i = 0; i < password.length(); i++) {
            if(Character.isUpperCase(password.charAt(i))) {
                containsUpperCase = true;
            }

            if(Character.isDigit(password.charAt(i))) {
                containsNumber = true;
            }
        }

        if(containsUpperCase)
            total++;
        if(containsNumber)
            total++;

        return total;

    }
}
