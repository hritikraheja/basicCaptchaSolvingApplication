package com.example.capsolver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static int points = 0;

    public void goToThirdScreen(View view) {
        Intent intent = new Intent(this,MainActivity3.class);
        startActivity(intent);
    }

    public boolean checkCaptcha(String c1, String c2) {
        return (c1.equals(c2));
    }

    public void goToFirstScreen(View view) {
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
    }

    public void refreshCaptcha(View view) {
        if (MainActivity2.lengthOfCaptcha > 5) {
            decrementPointsForRefresh();
            TextView cc = findViewById(R.id.captchaText);
            EditText sol = findViewById(R.id.solution);
            TextView points = findViewById(R.id.pointsText);
            cc.setText(generateCaptcha(MainActivity2.lengthOfCaptcha));
            sol.setText("");
            Toast.makeText(this, "REFRESHED : 5 Points Deducted.", Toast.LENGTH_LONG).show();
            String pointsText = MainActivity.points + " POINTS";
            points.setText(pointsText);
        } else {
            Toast.makeText(this,"CANNOT REFRESH BECAUSE OF LESS POINTS", Toast.LENGTH_LONG).show();
        }
    }

    public void submitCaptcha(View view) {
        EditText sol = findViewById(R.id.solution);
        TextView cc = findViewById(R.id.captchaText);
        TextView points = findViewById(R.id.pointsText);
        String captchaCode = cc.getText().toString();
        String captchaSolution = sol.getText().toString();
        String toastText;
        if (checkCaptcha(captchaSolution, captchaCode)){
            incrementPointsForCorrectAnswer();
            toastText = "CORRECT : 10 Points Added.";
        } else {
            decrementPointsForWrongAnswer();
            toastText = "INCORRECT : 10 Points Subtracted.";
        }
        Toast.makeText(this,toastText, Toast.LENGTH_LONG).show();
        cc.setText(generateCaptcha(MainActivity2.lengthOfCaptcha));
        sol.setText("");
        String pointsText = MainActivity.points + " POINTS";
        points.setText(pointsText);
    }

    public void incrementPointsForCorrectAnswer() {
        MainActivity.points += 10;
    }

    public void decrementPointsForWrongAnswer() {
        MainActivity.points -= 10;
    }

    public void decrementPointsForRefresh() {
        MainActivity.points -= 5;
    }

    public static String generateCaptcha(int captchaLength) {
        String captcha = "";
        char[] allCharacters = new char[62];
        for (int characterAscii = 65; characterAscii < 91; characterAscii++) {
            allCharacters[characterAscii - 65] = (char) characterAscii;
        }
        for (int characterAscii = 97; characterAscii < 123; characterAscii++) {
            allCharacters[26 + characterAscii - 97] = (char) characterAscii;
        }
        for (int number = 0; number < 10; number++) {
            allCharacters[52 + number] = String.valueOf(number).charAt(0);
        }
        do {
            int randomCharacterIndex = (int) (Math.random() * 100);
            if (randomCharacterIndex < 62) {
                captcha += allCharacters[randomCharacterIndex];
            }
        }while (captcha.length() < captchaLength);
        return (captcha);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView cc = findViewById(R.id.captchaText);
        cc.setText(generateCaptcha(MainActivity2.lengthOfCaptcha));
    }
}