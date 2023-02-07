package com.example.andriodtestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

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
        String userMeterText = feetText.getText().toString();
        String userCentimeterText = inchesText.getText().toString();
        String userWeightText = weightText.getText().toString();

        double meter = Integer.parseInt(userMeterText);
        double centimeter = Integer.parseInt(userCentimeterText);
        int weight = Integer.parseInt(userWeightText);

        double heightInMeters = (meter + (centimeter/100));
//        Toast.makeText(this, String.valueOf(heightInMeters), Toast.LENGTH_LONG).show();
        return weight / (heightInMeters * heightInMeters);
    }

    private void displayBmiResult(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("00.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String result;

        if (bmi < 18.5) {
            result = "BMI: " + bmiTextResult + "\nYou are underweight";
        }else if(bmi > 25) {
            result = "BMI: " + bmiTextResult + "\nYou are overweight";
        }else {
            result = "BMI: " + bmiTextResult + "\nYou are a healthy weight";
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