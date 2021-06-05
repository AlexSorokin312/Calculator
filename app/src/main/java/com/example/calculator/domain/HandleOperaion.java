package com.example.calculator.domain;

import android.util.Log;

import com.example.calculator.ui.MainActivity;

public class HandleOperaion {

    public Calculator calc;

    public HandleOperaion() {
        this.calc = new Calculator();
    }

    public void handleSymbol(String symbol) {
        if ((calc.text.contains(".")) && (symbol.equals(".") && calc.operation == ' ')) return;
        if ((calc.operation != ' ') && (symbol.equals("."))) {
            int ind = calc.text.indexOf(calc.operation);
            String text = calc.text.substring(ind);
            if (text.contains(".")) return;
        }
        if ((symbol.equals(".")) && (!calc.text.equals(""))) {
            if (calc.text.charAt(calc.text.length() - 1) == '.') return;
        }
        if ((symbol.equals(".")) && (calc.text.length() == 0)) {
            calc.text = "0";
            calc.firstNumber += "0.";
        }
        if (calc.operation != ' ') {
            if ((calc.text.charAt(calc.text.length() - 1) == calc.operation) && (symbol.equals("."))) {
                calc.text += "0";
            }
            calc.secondNumber += symbol;
        }

        Log.d("SECOND_NUMBER", calc.secondNumber);
        calc.text += symbol;
        String text = calc.text;
        MainActivity.textPrint(text);
    }

    public void handleOperation(char oper) {
        char[] operations = new char[]{'+', '-', '*', '/'};
        if (calc.text.equals("")) {
            calc.firstNumber = "0";
            calc.text = "0";
        }
        if (calc.operation == ' ') {
            String text = calc.text;
            int textLength = text.length();
            char last_symbol = text.charAt(textLength - 1);
            if (last_symbol == '.') calc.text += "0";
            calc.firstNumber = calc.text;
            calc.operation = oper;
            calc.text += calc.operation;
            MainActivity.textPrint(calc.text);
        } else {
            if ((!calc.firstNumber.equals(".") && (calc.secondNumber != ""))) {
                calcResult();
                calc.firstNumber = calc.text;
                calc.text += oper;
                calc.operation = oper;
                calc.secondNumber = "";
                MainActivity.textPrint(calc.text);
                Log.d("FIRST_NUMBER", calc.firstNumber);
            } else {
                for (char o : operations) {
                    if (calc.text.charAt(calc.text.length() - 1) == o) {
                        calc.firstNumber = calc.text.substring(0, calc.text.length() - 1);
                        calc.text = calc.text.replace(calc.text.charAt(calc.text.length() - 1), oper);
                        MainActivity.textPrint(calc.text);
                        calc.operation = oper;
                    }
                }
            }
        }
    }

    public void calcResult() throws ArithmeticException {
        double result;
        switch (calc.operation) {
            case '+':
                result = calc.add();
                afrerCalculation(result);
                break;
            case '-':
                result = calc.sub();
                afrerCalculation(result);
                break;
            case '*':
                result = calc.mult();
                afrerCalculation(result);
                break;
            case '/':
                try {
                    result = calc.div();
                } catch (ArithmeticException E) {
                    calc.firstNumber = "0";
                    calc.secondNumber = "";
                    calc.operation = ' ';
                    MainActivity.textPrint("");
                    calc.text = "0";
                    return;
                }
                afrerCalculation(result);
                break;
        }
    }

    private void afrerCalculation(Double result) {
        calc.text = String.format("%.1f", result);
        calc.firstNumber = calc.text;
        calc.secondNumber = "";
        calc.operation = ' ';
        MainActivity.textPrint(calc.text);
    }
}
