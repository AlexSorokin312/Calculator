package com.example.calculator.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Calculator implements Parcelable {

    public String text; //Храним в виде строки выражение вводимое пользователем
    public String firstNumber;
    public String secondNumber;
    public char operation;
    public double result;

    public Calculator() {
        this.firstNumber = "";
        this.secondNumber = "";
        this.operation = ' ';
        this.text = "";
    }

    protected Calculator(Parcel in) {
        text = in.readString();
        firstNumber = in.readString();
        secondNumber = in.readString();
        operation = (char) in.readInt();
        result = in.readDouble();
    }

    public static final Creator<Calculator> CREATOR = new Creator<Calculator>() {
        @Override
        public Calculator createFromParcel(Parcel in) {
            return new Calculator(in);
        }

        @Override
        public Calculator[] newArray(int size) {
            return new Calculator[size];
        }
    };

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
        if (Double.valueOf(this.secondNumber) == 0)
            throw new ArithmeticException("Деление на ноль");
        else {
            result = Double.valueOf(firstNumber) / Double.valueOf(secondNumber);
            return result;
        }
    }

    public String getText() {
        return text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeString(firstNumber);
        dest.writeString(secondNumber);
        dest.writeInt((int) operation);
        dest.writeDouble(result);
    }
}
