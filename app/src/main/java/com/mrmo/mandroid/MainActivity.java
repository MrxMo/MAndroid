package com.mrmo.mandroid;

import android.graphics.Color;
import android.os.Bundle;

import com.mrmo.mandroidlib.app.activity.MActivity;
import com.mrmo.mandroidlib.app.MManagerAble;

public class MainActivity extends MActivity {

    @Override
    protected MManagerAble getDevManager() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
