package com.example.android.quizapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class question2 extends AppCompatActivity {
    String buttonText = "Submit answer";

    protected void onSaveInstanceState(Bundle outState) {
        EditText edittext = (EditText) findViewById(R.id.userAnswer);
        String useranswer = edittext.getText().toString();
        outState.putString("useranswer", useranswer);
        Button button = (Button) findViewById(R.id.button);
        buttonText = button.getText().toString();
        TextView answer = (TextView) findViewById(R.id.answer);
        outState.putString("answer", answer.getText().toString());
        outState.putString("buttontext", buttonText);
        super.onSaveInstanceState(outState);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        buttonText = savedInstanceState.getString("buttontext");
        Button button = (Button) findViewById(R.id.button);
        button.setText(buttonText);
        String useranswer = savedInstanceState.getString("useranswer");
        TextView answer = (TextView) findViewById(R.id.answer);
        answer.setText(savedInstanceState.getString("answer"));
        EditText edittext = (EditText) findViewById(R.id.userAnswer);
        if (useranswer.equalsIgnoreCase("earth") && buttonText.equals(this.getString(R.string.nextPage))) {
            edittext.setBackgroundColor(0xff00843b);
        } else if (buttonText.equals(this.getString(R.string.nextPage))) {
            edittext.setBackgroundColor(0xffea0303);
        }
        super.onRestoreInstanceState(savedInstanceState);

    }

    //disable back button
    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);
        Button button = (Button) findViewById(R.id.button);
        button.setText(buttonText);
    }

    public void submit(View view) {
        Button button = (Button) findViewById(R.id.button);
        EditText edittext = (EditText) findViewById(R.id.userAnswer);
        String useranswer = edittext.getText().toString();
        if (useranswer != null && useranswer.length() == 0) {// no radio buttons are checked
            backgroundAnimation(edittext);
            Toast.makeText(this, this.getString(R.string.writeanswer),
                    Toast.LENGTH_SHORT).show();
        } else if (button.getText().toString().equals("Submit answer")) {
            checkAnswers();
            button.setText(this.getString(R.string.nextPage));
        } else if (button.getText().toString().equals(this.getString(R.string.nextPage))) {
            Intent intent = new Intent(this, question3.class);
            startActivity(intent);
        }
    }

    private void checkAnswers() {
        EditText edittext = (EditText) findViewById(R.id.userAnswer);
        String useranswer = edittext.getText().toString();
        TextView answerField = (TextView) findViewById(R.id.answer);
        if (useranswer.equalsIgnoreCase("earth")) {
            edittext.setBackgroundColor(0xff00843b);
            MainActivity.pointsPlus();
            String msg = "Great job, " + MainActivity.getName() + ". Your score is: " + MainActivity.getPoints();
            answerField.setText(msg);
        } else {
            edittext.setBackgroundColor(0xffea0303);
            String msg = "Wrong answer. The answer was Earth. Your score is: " + MainActivity.getPoints();
            answerField.setText(msg);


        }

    }

    private void backgroundAnimation(View animatedElement) {
        AnimationDrawable drawable = new AnimationDrawable();
        for (int i = 1; i < 5; i++) {
            drawable.addFrame(new ColorDrawable(0xffea0303), 130);
            drawable.addFrame(new ColorDrawable(0x00ffffff), 130);
        }
        drawable.setOneShot(true);
        animatedElement.setBackground(drawable);
        drawable.start();
    }


}