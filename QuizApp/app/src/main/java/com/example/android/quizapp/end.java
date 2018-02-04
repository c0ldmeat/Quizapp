package com.example.android.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class end extends AppCompatActivity {
    //define global variables;
    int playerScore = MainActivity.getPoints();
    String playerName = MainActivity.getName();

    public void onBackPressed() {
    }

    //display main activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        TextView textView = (TextView) findViewById(R.id.endscore);
        String msg = "You got " + playerScore + " points, " + playerName;
        textView.setText(msg);
    }

    public void quizExit(View v) {
        this.finishAffinity();
    }

    public void quizShare(View v) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        String msg = "I got " + playerScore + " points at Quiz app";
        sendIntent.putExtra(Intent.EXTRA_TEXT, msg);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}