package com.example.calculator.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.calculator.R;
import com.example.calculator.domain.Calculator;

public class SettingsActivity extends AppCompatActivity {

    private ThemeStorage storage;

    private View.OnClickListener btn_LightThemeClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            storage.setTheme(AppTheme.CUSTOM);
            Intent MainActivity = new Intent(SettingsActivity.this, MainActivity.class);
            startActivity(MainActivity);
        }
    };
    private View.OnClickListener btn_DarkThemeClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            storage.setTheme(AppTheme.DEFAULT);
            Intent MainActivity = new Intent(SettingsActivity.this, MainActivity.class);
            startActivity(MainActivity);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        storage = new ThemeStorage(this);
        setTheme(storage.getTheme().getResource());
        setContentView(R.layout.activity_settings);
        initViews();
    }

    public void initViews() {
        Button btn_lightTheme = findViewById(R.id.lightTheme);
        Button btn_darkTheme = findViewById(R.id.darkTheme);
        btn_lightTheme.setOnClickListener(btn_LightThemeClick);
        btn_darkTheme.setOnClickListener(btn_DarkThemeClick);
    }
}