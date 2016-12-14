package com.example.michael.tddc73project;

import java.util.ArrayList;

/**
 * Interface to add a onSave function to the listener.
 */

public interface OnSaveListener{
    /**
     * Public function to override with the custom onSave function
     * @param formVals the form values
     */
    public void onSave(ArrayList<String> formVals);
}
