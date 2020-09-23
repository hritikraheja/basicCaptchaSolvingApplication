package com.example.capsolver;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    public static int lengthOfCaptcha = 0;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void goToSecondScreen (View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Spinner dropDown = findViewById(R.id.spinner);
        String[] levels = {"EASY", "INTERMEDIATE", "HARD", "EXTREME"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, levels);
        dropDown.setAdapter(adapter);
        dropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0 :
                        MainActivity2.lengthOfCaptcha = 4;
                        break;
                    case 1 :
                        MainActivity2.lengthOfCaptcha = 6;
                        break;
                    case 2 :
                        MainActivity2.lengthOfCaptcha = 8;
                        break;
                    case 3 :
                        MainActivity2.lengthOfCaptcha = 10;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}