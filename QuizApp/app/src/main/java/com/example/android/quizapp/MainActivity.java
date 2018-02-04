package com.example.android.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //define global variables;
    private static int playerScore = 0;
    private static String playerName;

    public static String getName() {
        return playerName;
    }

    public static int getPoints() {
        return playerScore;
    }


    public static void pointsPlus() {
        playerScore += 10;
    }

    //save variables when screen rotates;
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("playerName", playerName);
        outState.putInt("playerScore", playerScore);
    }

    //restore variables when screen rotates
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        playerName = savedInstanceState.getString("playerName");
        playerScore = savedInstanceState.getInt("playerScore");

    }

    //display main activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //on button click
    public void submitName(View view) {
        Intent intent = new Intent(this, question1.class);
        //get player name from textview
        EditText mMessageEditText = (EditText) findViewById(R.id.userName);
        playerName = mMessageEditText.getText().toString();
        if (playerName.length() == 0) {
            Toast.makeText(this, R.string.checkname,
                    Toast.LENGTH_LONG).show();
        } else {
            startActivity(intent);
        }

    }

}