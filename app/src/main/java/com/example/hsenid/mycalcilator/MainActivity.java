package com.example.hsenid.mycalcilator;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hsenid.mycalcilator.CalculatorOperations;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private String input = " ";
    private TextView display;
    private String currentOperator= "";
    private String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = (TextView) findViewById(R.id.txtDisplay);
        display.setText(input);
    }

    private void updateScreen(){
        display.setText(input);
    }

    public void onClickNumber(View v){
        if(result != ""){
            clear();
            updateScreen();
            result = "";
        }
        Button b = (Button) v;
        input   += b.getText();
        updateScreen();
    }

    private boolean isOperator(char op){
        switch (op) {
            case '+':
            case '-':
            case '*':
            case '/':return  true;
            default: return false;
        }
    }
    public void onClickOperator(View v){
        Button b=(Button) v;

        if(result != ""){
            input = result;
            //result = "";
        }
        if(currentOperator != ""){
            if (isOperator(input.charAt(input.length()-1))){

                input.replace(input.charAt(input.length()-1),currentOperator.charAt(0));

            }else {
                getResult();
                input =result;
                result = "";
            }
            currentOperator =b.getText().toString();
        }
        input   += b.getText();
        currentOperator = b.getText().toString();
        updateScreen();
    }

    public void clear(){
        input = "";
        currentOperator="";
        updateScreen();
    }

    public void onClickClear(View v){
        clear();
        updateScreen();
    }

    private double operate(String operand1, String operand2, String operator){
        switch (operator){
            case "+": return Double.valueOf(operand1) + Double.valueOf(operand2);
            case "-": return Double.valueOf(operand1) - Double.valueOf(operand2);
            case "*": return Double.valueOf(operand1) * Double.valueOf(operand2);
            case "/": try {

                return Double.valueOf(operand1) / Double.valueOf(operand2);
            }catch (Exception e){

                System.out.print("Exception in division");
            }
            default: return  -1;
        }
    }

    private boolean getResult(){
        String[] operation = input.split(Pattern.quote(currentOperator));
        if (operation.length < 2) return false;

        result=String.valueOf(operate(operation[0],operation[1], currentOperator)) ;
        return true;
    }

    public void onClickEqual(View v){

        if(result != ""){
            String[] operation = input.split(Pattern.quote(currentOperator));
            input =result + currentOperator+ operation[1];
            if(!getResult()) return;
            display.setText(input + "\n" + result);

        }else {
            if(!getResult()) return;
            display.setText(input + "\n" + result);
        }


    }



}
