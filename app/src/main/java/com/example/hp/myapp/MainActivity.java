package com.example.hp.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int score = 0;
    boolean isSubmitted;
    boolean secondAnswer;
    boolean thirdAnswer;
    boolean fourthAnswer;


    EditText answer;
    RadioGroup radioGroupTwo;
    RadioGroup radioGroupThree;
    RadioButton old;
    RadioButton bootstrap;
    CheckBox edit;
    CheckBox label;
    CheckBox sys;
    CheckBox open;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        answer = (EditText) findViewById(R.id.answer);
        radioGroupTwo = (RadioGroup) findViewById(R.id.radio_group2);
        radioGroupThree = (RadioGroup) findViewById(R.id.radio_group3);
        old = (RadioButton) findViewById(R.id.radio_old);
        bootstrap = (RadioButton) findViewById(R.id.radio_bootstrap);
        edit = (CheckBox) findViewById(R.id.checkbox_edit);
        label = (CheckBox) findViewById(R.id.checkbox_label);
        sys = (CheckBox) findViewById(R.id.checkbox_sys);
        open = (CheckBox) findViewById(R.id.checkbox_open);

        isSubmitted = false;
        secondAnswer = false;
        thirdAnswer = false;
        fourthAnswer = false;

    }

    /**
     * Saves score data when screen is rotated.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("scoreData", score);
        outState.putBoolean("submit", isSubmitted);
        outState.putBoolean("secondAnswer", secondAnswer);
        outState.putBoolean("thirdAnswer", thirdAnswer);
        outState.putBoolean("fourthAnswer", fourthAnswer);
    }

    /*
     * Restores score data after screen rotation.
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        score = savedInstanceState.getInt("scoreData");
        isSubmitted = savedInstanceState.getBoolean("submit");
        secondAnswer = savedInstanceState.getBoolean("secondAnswer");
        thirdAnswer = savedInstanceState.getBoolean("thirdAnswer");
        fourthAnswer = savedInstanceState.getBoolean("fourthAnswer");

    }

    /**
     * Question 2: Radio Button
     * Correct answer: #3 (Bootstrap Program)
     */
    public void onSecondQuestionClicked(View view) {
        if (bootstrap.isChecked()) {
            secondAnswer = true;
        }
    }

    /**
     * Question 3: Radio Button
     * Correct answer: #2 (Old)
     */
    public void onThirdQuestionClicked(View view) {
        if (old.isChecked()) {
            thirdAnswer = true;
        }
    }


    /**
     * Question 4: CheckBox
     * Correct answers: #1, 2, and 3 (Edit, label, and sys)
     */
    public void onCheckBoxClicked(View view) {
        // Will only return a score of 1 if ALL correct answers are selected
        if (edit.isChecked() && label.isChecked() && sys.isChecked() && !open.isChecked()) {
            fourthAnswer = true;
        }
    }

    /**
     * Executes when Submit button is clicked. Score for question #1 is determined.
     */
    public void submitScore(View view) {
        // Check for isSubmitted value
        if (isSubmitted) {
            Toast.makeText(getApplicationContext(), "Already Submitted", Toast.LENGTH_SHORT).show();
        } else {

            if (answer.getText().toString().trim().equalsIgnoreCase(getString(R.string.edit_text_answer))) {
                score++;
            }
            // Calls answer booleans and increments score
            if (secondAnswer) {
                score++;
            }
            if (thirdAnswer) {
                score++;
            }
            if (fourthAnswer) {
                score++;
            }
            // Displays toast message with user's score and changes isSubmitted value
            Toast.makeText(getApplicationContext(), " The score is " + score, Toast.LENGTH_LONG).show();
            isSubmitted = true;
        }
    }

    public void resetScore(View view) {
        // Resets score and booleans for Submit and answers
        score = 0;
        isSubmitted = false;
        secondAnswer = false;
        thirdAnswer = false;
        fourthAnswer = false;
        // Resets EditText for question 1
        answer.getText().clear();

        // Resets RadioButtons for questions 2 - 3
        radioGroupTwo.clearCheck();
        radioGroupThree.clearCheck();


        // Resets CheckBoxes for question 4
        edit.setChecked(false);
        label.setChecked(false);
        sys.setChecked(false);
        open.setChecked(false);
    }
}
