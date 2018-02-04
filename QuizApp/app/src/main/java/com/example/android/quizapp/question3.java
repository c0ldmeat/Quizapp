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
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class question3 extends AppCompatActivity {
    int count = 0;
    String buttonText = "Submit answer";

    protected void onSaveInstanceState(Bundle outState) {
        CheckBox checkbox1 = (CheckBox) findViewById(R.id.checkbox1);
        CheckBox checkbox2 = (CheckBox) findViewById(R.id.checkbox2);
        CheckBox checkbox3 = (CheckBox) findViewById(R.id.checkbox3);
        CheckBox checkbox4 = (CheckBox) findViewById(R.id.checkbox4);
        outState.putBoolean("checkbox1", checkbox1.isChecked());
        outState.putBoolean("checkbox2", checkbox2.isChecked());
        outState.putBoolean("checkbox3", checkbox3.isChecked());
        outState.putBoolean("checkbox4", checkbox4.isChecked());
        Button button = (Button) findViewById(R.id.button);
        buttonText = button.getText().toString();
        outState.putString("buttontext", buttonText);
        TextView answer = (TextView) findViewById(R.id.answer);
        outState.putString("answer", answer.getText().toString());
        super.onSaveInstanceState(outState);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        CheckBox checkbox1 = (CheckBox) findViewById(R.id.checkbox1);
        CheckBox checkbox2 = (CheckBox) findViewById(R.id.checkbox2);
        CheckBox checkbox3 = (CheckBox) findViewById(R.id.checkbox3);
        CheckBox checkbox4 = (CheckBox) findViewById(R.id.checkbox4);
        checkbox1.setChecked(savedInstanceState.getBoolean("checkbox1"));
        checkbox2.setChecked(savedInstanceState.getBoolean("checkbox2"));
        checkbox3.setChecked(savedInstanceState.getBoolean("checkbox3"));
        checkbox4.setChecked(savedInstanceState.getBoolean("checkbox4"));
        buttonText = savedInstanceState.getString("buttontext");
        Button button = (Button) findViewById(R.id.button);
        button.setText(buttonText);
        TextView answer = (TextView) findViewById(R.id.answer);
        answer.setText(savedInstanceState.getString("answer"));
        LinearLayout checkboxes = (LinearLayout) findViewById(R.id.checkboxes);
        if (buttonText.equals(this.getString(R.string.nextPage)) && checkbox1.isChecked() && checkbox4.isChecked() && !checkbox2.isChecked() && !checkbox3.isChecked()) {
            checkbox1.setBackgroundColor(0xff00843b);
            checkbox4.setBackgroundColor(0xff00843b);
        } else if (buttonText.equals(this.getString(R.string.nextPage))) {
            checkboxes.setBackgroundColor(0xffea0303);
            checkbox1.setBackgroundColor(0xff00843b);
            checkbox4.setBackgroundColor(0xff00843b);

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
        setContentView(R.layout.activity_question3);
        Button button = (Button) findViewById(R.id.button);
        button.setText(buttonText);
    }

    public void submit(View view) {
        Button button = (Button) findViewById(R.id.button);
        CheckBox checkbox1 = (CheckBox) findViewById(R.id.checkbox1);
        CheckBox checkbox2 = (CheckBox) findViewById(R.id.checkbox2);
        CheckBox checkbox3 = (CheckBox) findViewById(R.id.checkbox3);
        CheckBox checkbox4 = (CheckBox) findViewById(R.id.checkbox4);
        if (checkbox1.isChecked()) count++;
        if (checkbox2.isChecked()) count++;
        if (checkbox3.isChecked()) count++;
        if (checkbox4.isChecked()) count++;
        LinearLayout checkboxes = (LinearLayout) findViewById(R.id.checkboxes);
        if (count < 2) {// no radio buttons are checked
            backgroundAnimation(checkboxes);
            Toast.makeText(this, this.getString(R.string.selecttwo),
                    Toast.LENGTH_SHORT).show();
        } else if (button.getText().toString().equals("Submit answer")) {
            checkAnswers();
            button.setText(this.getString(R.string.nextPage));
        } else if (button.getText().toString().equals(this.getString(R.string.nextPage))) {
            Intent intent = new Intent(this, question4.class);
            startActivity(intent);
        }
    }

    private void checkAnswers() {
        CheckBox checkbox1 = (CheckBox) findViewById(R.id.checkbox1);
        CheckBox checkbox2 = (CheckBox) findViewById(R.id.checkbox2);
        CheckBox checkbox3 = (CheckBox) findViewById(R.id.checkbox3);
        CheckBox checkbox4 = (CheckBox) findViewById(R.id.checkbox4);
        TextView answerField = (TextView) findViewById(R.id.answer);
        LinearLayout checkboxes = (LinearLayout) findViewById(R.id.checkboxes);
        if (checkbox1.isChecked() && checkbox4.isChecked() && !checkbox2.isChecked() && !checkbox3.isChecked()) {
            checkbox1.setBackgroundColor(0xff00843b);
            checkbox4.setBackgroundColor(0xff00843b);
            MainActivity.pointsPlus();
            String msg = "Great job, " + MainActivity.getName() + ". Your score is: " + MainActivity.getPoints();
            answerField.setText(msg);
        } else {
            checkboxes.setBackgroundColor(0xffea0303);
            String msg = "Wrong answer. Your score is: " + MainActivity.getPoints();
            answerField.setText(msg);
            checkbox1.setBackgroundColor(0xff00843b);
            checkbox4.setBackgroundColor(0xff00843b);

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