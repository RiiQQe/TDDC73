package com.example.michael.tddc73project;

/**
 * This class consists of one function that returns an integer in the range 0-3
 * which decides the strength of the password.
 *
 * @author Rickard Lindstedt
 * @author Michael Sjöström
 */

public class PasswordAlgorithm {

    /**
     * Creates an instance of PasswordAlgorithm.
     */
    public PasswordAlgorithm() {

    }

    /**
     * Checks so that the password is at least 8 charachters long,
     * contains at least one digit and one uppercase letter.
     *
     * @param password the password to be meassured.
     * @return total strength represented as an integer from 0-3.
     */
    public int checkPassword(String password) {

        int total = 0;
        boolean containsUpperCase = false;
        boolean containsNumber = false;

        //Control length
        if (password.length() > 8) {
            total++;
        }

        //Control if password contains upper case and digit
        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                containsUpperCase = true;
            }

            if (Character.isDigit(password.charAt(i))) {
                containsNumber = true;
            }
        }

        if (containsUpperCase)
            total++;
        if (containsNumber)
            total++;

        return total;

    }
}
