package com.example.andriodtestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private RadioButton maleButton;
    private RadioButton femaleButton;
    private EditText ageText;
    private EditText feetText;
    private EditText inchesText;
    private EditText weightText;
    private Button calculateButton;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
//        getSupportActionBar().setTitle("Test");
        setupButtonClickListener();
    }

    private void findViews() {
        maleButton = findViewById(R.id.radio_button_male);
        femaleButton = findViewById(R.id.radio_button_female);
        ageText = findViewById(R.id.edit_text_age);
        feetText = findViewById(R.id.edit_text_feet);
        inchesText = findViewById(R.id.edit_text_inches);
        weightText = findViewById(R.id.edit_text_weight);
        calculateButton = findViewById(R.id.button_calculate);
        resultText = findViewById(R.id.text_view_result);

//        resultText.setText("Changed text");

//        String alertText = "Hello there";
//        Toast.makeText(this, alertText, Toast.LENGTH_LONG).show();
    }

    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String alertText = "You clicked calculate";
//                Toast.makeText(MainActivity.this, alertText, Toast.LENGTH_LONG).show();

                String userAgeText = ageText.getText().toString();
                int age = Integer.parseInt(userAgeText);

                if (age >= 18) {
                displayBmiResult(calculateBmi());
                }else {
                    displayGuidance(calculateBmi());
                }
            }
        });
    }

    private double calculateBmi() {
        String userFeetText = feetText.getText().toString();
        String userInchesText = inchesText.getText().toString();
        String userWeightText = weightText.getText().toString();

        int feet = Integer.parseInt(userFeetText);
        int inches = Integer.parseInt(userInchesText);
        int weight = Integer.parseInt(userWeightText);

        int totalInches = (feet * 12) + inches;
        double heightInMeters = totalInches * 0.0254;
        return weight / (heightInMeters * heightInMeters);
    }

    private void displayBmiResult(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("00.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String result;

        if (bmi < 18.5) {
            result = bmiTextResult + " - You are underweight";
        }else if(bmi > 25) {
            result = bmiTextResult + " - You are overweight";
        }else {
            result = bmiTextResult + " - You are a healthy weight";
        }
        resultText.setText(result);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("00.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String result;

        if (maleButton.isChecked()) {
            result = bmiTextResult + " - As you are under 18, please consult with your doctor for the healthy range for boys";
        }else if(femaleButton.isChecked()) {
            result = bmiTextResult + " - As you are under 18, please consult with your doctor for the healthy range for girls";
        }else {
            result = bmiTextResult + " - As you are under 18, please consult with your doctor for the healthy range";
        }
        resultText.setText(result);
    }
}