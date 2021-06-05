package com.example.calculator.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.calculator.R;
import com.example.calculator.domain.Calculator;
import com.example.calculator.domain.HandleOperaion;

public class MainActivity extends AppCompatActivity {

    public HandleOperaion handler = new HandleOperaion();
    public final static String CALC_KEY = "CALC_KEY";
    public Calculator calc;
    public ThemeStorage storage;
    static AppCompatTextView textPrint;

    private final int[] numberButtonIds = new int[]{R.id._0, R.id._1, R.id._2, R.id._3,
            R.id._4, R.id._5, R.id._6, R.id._7, R.id._8, R.id._9};

    public static void textPrint(String text) {
        textPrint.setText(text);
    }

    private void setNumberButtonListeners() {
        for (int numberButtonId : numberButtonIds) {
            findViewById(numberButtonId).setOnClickListener(v -> {
                Button btn = (Button) v;
                handler.handleSymbol(btn.getText().toString());
            });
        }
    }

    private View.OnClickListener btnPointClick = v -> {
        handler.handleSymbol(".");
    };
    private View.OnClickListener btnEqualsClick = v -> handler.calcResult();
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

    private View.OnClickListener btnAddClick = v -> handler.handleOperation('+');
    private View.OnClickListener btnSubClick = v -> handler.handleOperation('-');
    private View.OnClickListener btnMultClick = v -> handler.handleOperation('*');
    private View.OnClickListener btnDivClick = v -> handler.handleOperation('/');
    private View.OnClickListener btnChangeThemeClick = v -> {
        Intent Settings = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(Settings);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        storage = new ThemeStorage(this);
        setTheme(storage.getTheme().getResource());
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView() {
        textPrint = findViewById(R.id._textPrint);
        calc = new Calculator();
        setNumberButtonListeners();
        Button btn_clear = findViewById(R.id._clear);
        Button btn_point = findViewById(R.id._point);
        Button btn_add = findViewById(R.id._add);
        Button btn_sub = findViewById(R.id._sub);
        Button btn_mult = findViewById(R.id._mult);
        Button btn_div = findViewById(R.id._div);
        Button btn_equal = findViewById(R.id._equal);
        Button btn_changeTheme = findViewById(R.id.changeTheme);
        btn_clear.setOnClickListener(btnClearClick);
        btn_add.setOnClickListener(btnAddClick);
        btn_sub.setOnClickListener(btnSubClick);
        btn_mult.setOnClickListener(btnMultClick);
        btn_div.setOnClickListener(btnDivClick);
        btn_point.setOnClickListener(btnPointClick);
        btn_equal.setOnClickListener(btnEqualsClick);
        btn_changeTheme.setOnClickListener(btnChangeThemeClick);
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(CALC_KEY, calc);
    }

    protected void onRestoreInstanceState(Bundle outState) {
        super.onRestoreInstanceState(outState);
        calc = outState.getParcelable(CALC_KEY);
        textPrint.setText(calc.getText());
    }
}