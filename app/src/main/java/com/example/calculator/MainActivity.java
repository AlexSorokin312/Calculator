package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final static String CALC_KEY = "calc_key";
    Calculator calc;
    AppCompatTextView textPrint;
    private View.OnClickListener btnZeroClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            handleSymbol("0");
        }
    };
    private View.OnClickListener btnOneClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            handleSymbol("1");
        }
    };
    private View.OnClickListener btnTwoClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            handleSymbol("2");
        }
    };
    private View.OnClickListener btnThreeClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            handleSymbol("3");
        }
    };
    private View.OnClickListener btnTFourClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            handleSymbol("4");
        }
    };
    private View.OnClickListener btnFiveClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            handleSymbol("5");
        }
    };
    private View.OnClickListener btnSixClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            handleSymbol("6");
        }
    };
    private View.OnClickListener btnSevenClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            handleSymbol("7");
        }
    };
    private View.OnClickListener btnEightClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            handleSymbol("8");
        }
    };
    private View.OnClickListener btnNineClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            handleSymbol("9");
        }
    };

    private View.OnClickListener btnPointClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            handleSymbol(".");
        }
    };

    private View.OnClickListener btnEqualsClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            calcResult();
        }
    };

    private View.OnClickListener btnClearClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            calc.firstNumber = "";
            calc.secondNumber = "";
            calc.text = "";
            calc.operation = ' ';
            textPrint.setText(calc.text);
        }
    };

    private View.OnClickListener btnAddClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            handleOperation('+');
        }
    };
    private View.OnClickListener btnSubClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            handleOperation('-');
        }
    };

    private View.OnClickListener btnMultClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            handleOperation('*');
        }
    };
    private View.OnClickListener btnDivClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            handleOperation('/');
        }
    };


    public void handleSymbol(String symbol) {

        if ((calc.text.contains(".")) && (symbol == ".") && calc.operation == ' ') return;
        if ((calc.operation != ' ') && (symbol == ".")) {
            int ind = calc.text.indexOf(calc.operation);
            String text = calc.text.substring(ind);
            if (text.contains(".")) return;
        }
        if ((symbol == ".") && (calc.text != "")) {
            if (calc.text.charAt(calc.text.length() - 1) == '.') return;
        }
        if ((symbol == ".") && (calc.text.length() == 0)) {
            calc.text = "0";
            calc.firstNumber += "0.";
        }
        if (calc.operation != ' ') {
            if ((calc.text.charAt(calc.text.length() - 1) == calc.operation) && (symbol == ".")) {
                calc.text += "0";
            }
            calc.secondNumber += symbol;
        }

        Log.d("SECOND_NUMBER", calc.secondNumber);
        calc.text += symbol;
        textPrint.setText(calc.text);
    }

    public void handleOperation(char oper) {
        if (calc.text == "") {
            calc.firstNumber = "0";
            calc.text = "0";
        }
        char[] operations = new char[]{'+', '-', '*', '/'};
        if (calc.operation == ' ') {
            String text = calc.text;
            int textLength = text.length();
            char last_symbol = text.charAt(textLength - 1);
            if (last_symbol == '.') calc.text += "0";
            calc.firstNumber = calc.text;
            calc.operation = oper;
            calc.text += calc.operation;
            Log.d("FIRST.NUMBER", calc.firstNumber);
            textPrint.setText(calc.text);
        } else {
            if ((calc.firstNumber != "") && ((calc.secondNumber != ""))) {
                calcResult();
                calc.firstNumber = calc.text;
                calc.text += oper;
                calc.operation = oper;
                calc.secondNumber = "";
                textPrint.setText(calc.text);
                Log.d("FIRST_NUMBER", calc.firstNumber);
            } else {
                for (char o : operations) {
                    if (calc.text.charAt(calc.text.length() - 1) == o) {
                        calc.firstNumber = calc.text.substring(0, calc.text.length() - 1);
                        calc.text = calc.text.replace(calc.text.charAt(calc.text.length() - 1), oper);
                        textPrint.setText(calc.text);
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
                    textPrint.setText("");
                    calc.text="0";
                    return;
                }
                afrerCalculation(result);
                break;
        }
    }

    public void afrerCalculation(Double result) {
        calc.text = String.format("%.1f", result);
        ;
        calc.firstNumber = calc.text;
        calc.secondNumber = "";
        calc.operation = ' ';
        textPrint.setText(calc.text);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(CALC_KEY, calc);
    }

    protected void onRestoreInstanceState(Bundle outState) {
        super.onRestoreInstanceState(outState);
        calc = outState.getParcelable(CALC_KEY);
        setCalculatorState();
    }

    public void setCalculatorState() {
        textPrint.setText(calc.getText());
    }

    public void initView() {
        textPrint = findViewById(R.id._textPrint);
        calc = new Calculator();
        Button btn_0 = findViewById(R.id._0);
        Button btn_1 = findViewById(R.id._1);
        Button btn_2 = findViewById(R.id._2);
        Button btn_3 = findViewById(R.id._3);
        Button btn_4 = findViewById(R.id._4);
        Button btn_5 = findViewById(R.id._5);
        Button btn_6 = findViewById(R.id._6);
        Button btn_7 = findViewById(R.id._7);
        Button btn_8 = findViewById(R.id._8);
        Button btn_9 = findViewById(R.id._9);
        Button btn_clear = findViewById(R.id._clear);
        Button btn_point = findViewById(R.id._point);
        Button btn_add = findViewById(R.id._add);
        Button btn_sub = findViewById(R.id._sub);
        Button btn_mult = findViewById(R.id._mult);
        Button btn_div = findViewById(R.id._div);
        Button btn_equal = findViewById(R.id._equal);

        btn_0.setOnClickListener(btnZeroClick);
        btn_1.setOnClickListener(btnOneClick);
        btn_2.setOnClickListener(btnTwoClick);
        btn_3.setOnClickListener(btnThreeClick);
        btn_4.setOnClickListener(btnTFourClick);
        btn_5.setOnClickListener(btnFiveClick);
        btn_6.setOnClickListener(btnSixClick);
        btn_7.setOnClickListener(btnSevenClick);
        btn_8.setOnClickListener(btnEightClick);
        btn_9.setOnClickListener(btnNineClick);

        btn_clear.setOnClickListener(btnClearClick);
        btn_add.setOnClickListener(btnAddClick);
        btn_sub.setOnClickListener(btnSubClick);
        btn_mult.setOnClickListener(btnMultClick);
        btn_div.setOnClickListener(btnDivClick);
        btn_point.setOnClickListener(btnPointClick);
        btn_equal.setOnClickListener(btnEqualsClick);
    }
}