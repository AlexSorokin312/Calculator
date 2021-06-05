package com.example.calculator.ui;

import com.example.calculator.R;

public enum AppTheme {


    CUSTOM(R.style.MyLightTheme, "CUSTOM"),
    DEFAULT(R.style.MyDarkTheme, "DEFAULT");

    private String key;
    private int resource;

    public int getResource() {
        return resource;
    }

    AppTheme(int resource, String key) {
        this.resource = resource;
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
