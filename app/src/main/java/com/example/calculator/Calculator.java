package com.example.calculator;

public class Calculator {

    public String text; //Храним в виде строки выражение вводимое пользователем
    public String firstNumber;
    public String secondNumber;
    public char operation;
    public double result;

    Calculator() {
        this.firstNumber = "";
        this.secondNumber = "";
        this.operation = ' ';
        this.text = "";
    }

    public double add() {
        result = Double.valueOf(firstNumber) + Double.valueOf(secondNumber);
        return result;
    }

    public double sub() {
        result = Double.valueOf(firstNumber) - Double.valueOf(secondNumber);
        return result;
    }

    public double mult() {
        result = Double.valueOf(firstNumber) * Double.valueOf(secondNumber);
        return result;
    }

    public double div() throws ArithmeticException {
        if (Double.valueOf(this.secondNumber) == 0) throw new ArithmeticException("Деление на ноль");
        else {
            result = Double.valueOf(firstNumber) / Double.valueOf(secondNumber);
            return result;
        }
    }
}
