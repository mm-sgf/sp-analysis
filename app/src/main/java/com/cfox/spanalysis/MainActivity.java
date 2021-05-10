package com.cfox.spanalysis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onPause() {
        SharedPreferences sp1 = getSharedPreferences("test-mode1", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sp1.edit();
        editor1.putString("ed-key-onPause", "onPause-value-");
        editor1.apply();

        super.onPause();

        SharedPreferences sp2 = getSharedPreferences("test-mode1", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sp2.edit();
        editor2.putString("ed-key-onPause", "onPause-value-");
        editor2.apply();
    }

    @Override
    protected void onStop() {
        SharedPreferences sp1 = getSharedPreferences("test-mode1", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sp1.edit();
        editor1.putString("ed-key-onStop", "onStop-value-");
        editor1.apply();

        super.onStop();

        SharedPreferences sp2 = getSharedPreferences("test-mode1", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sp2.edit();
        editor2.putString("ed-key-onStop", "onStop-value-");
        editor2.apply();

    }


    @Override
    protected void onDestroy() {
        SharedPreferences sp1 = getSharedPreferences("test-mode1", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sp1.edit();
        editor1.putString("ed-key-onDestroy", "onDestroy-value-");
        editor1.apply();

        super.onDestroy();

        SharedPreferences sp2 = getSharedPreferences("test-mode1", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sp2.edit();
        editor2.putString("ed-key-onDestroy", "onDestroy-value-");
        editor2.apply();

    }
}