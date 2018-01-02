package com.billionss.weather.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.billionss.weather.R;

/**
 * Created by leo on 2017/12/14.
 */

public class SimpleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println("leo SimpleActivity onCreate");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_simple);

    }
}
