package com.example.android.quizapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.nfc.Tag;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class question1 extends AppCompatActivity {
    String buttonText = "Submit answer";

    protected void onSaveInstanceState(Bundle outState) {

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        Button button = (Button) findViewById(R.id.button);
        buttonText = button.getText().toString();
        int selectedId = radioGroup.getCheckedRadioButtonId();
        TextView answer = (TextView) findViewById(R.id.answer);
        outState.putInt("selectedId", selectedId);
        outState.putString("buttontext", buttonText);
        outState.putString("answer", answer.getText().toString());
        super.onSaveInstanceState(outState);
    }


    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        int selectedId = savedInstanceState.getInt("selectedId");
        buttonText = savedInstanceState.getString("buttontext");
        RadioButton radiobutton = (RadioButton) findViewById(selectedId);
        if (selectedId != -1) {
            radiobutton.setChecked(true);
        }
        Button button = (Button) findViewById(R.id.button);
        button.setText(buttonText);
        TextView answer = (TextView) findViewById(R.id.answer);
        answer.setText(savedInstanceState.getString("answer"));
        if (buttonText.equals(this.getString(R.string.nextPage)) && selectedId == R.id.q1a3) {
            radiobutton.setBackgroundColor(0xff00843b);
        } else if (buttonText.equals(this.getString(R.string.nextPage))) {
            radiobutton.setBackgroundColor(0xffea0303);
            RadioButton radiobuttoncorrect = (RadioButton) findViewById(R.id.q1a3);
            radiobuttoncorrect.setBackgroundColor(0xff00843b);
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
        setContentView(R.layout.question1);
        Button button = (Button) findViewById(R.id.button);
        button.setText(buttonText);
    }

    public void submit(View view) {
        Button button = (Button) findViewById(R.id.button);
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        if (radiogroup.getCheckedRadioButtonId() == -1) {// no radio buttons are checked
            backgroundAnimation(radiogroup);
            Toast.makeText(this, this.getString(R.string.selectone),
                    Toast.LENGTH_SHORT).show();
        } else if (button.getText().toString().equals(this.getString(R.string.submit)) &&
                radiogroup.getCheckedRadioButtonId() != -1) {
            checkAnswers();
            button.setText(this.getString(R.string.nextPage));
        } else if (button.getText().toString().equals(this.getString(R.string.nextPage))) {
            Intent intent = new Intent(this, question2.class);
            startActivity(intent);
        }
    }

    private void checkAnswers() {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        // find the radiobutton by returned id
        RadioButton radiobutton = (RadioButton) findViewById(selectedId);
        TextView answerField = (TextView) findViewById(R.id.answer);

        if (selectedId == R.id.q1a3) {
            radiobutton.setBackgroundColor(0xff00843b);
            MainActivity.pointsPlus();
            String msg = "Great job, " + MainActivity.getName() + ". Your score is: " + MainActivity.getPoints();
            answerField.setText(msg);
        } else {
            radiobutton.setBackgroundColor(0xffea0303);
            String msg = "Wrong answer. Your score is: " + MainActivity.getPoints();
            answerField.setText(msg);
            RadioButton radiobuttoncorrect = (RadioButton) findViewById(R.id.q1a3);
            radiobuttoncorrect.setBackgroundColor(0xff00843b);

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