package com.example.hsenid.mycalcilator;

import android.widget.EditText;

/**
 * Created by hsenid on 8/23/17.
 */

public class CalculatorOperations {

    private String displayToString;

    public void setDisplay(String buttonClicked, EditText currentDisplay) {
        displayToString = currentDisplay.getText().toString();

        if (!displayToString.matches("")) {
            currentDisplay.setText(displayToString + buttonClicked);
        } else {
            currentDisplay.setText(buttonClicked);
        }
    }
}
