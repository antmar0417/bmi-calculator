package com.example.andriodtestapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
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
    private EditText metersText;
    private EditText centimetersText;
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
        metersText = findViewById(R.id.edit_text_feet);
        centimetersText = findViewById(R.id.edit_text_inches);
        weightText = findViewById(R.id.edit_text_weight);
        calculateButton = findViewById(R.id.button_calculate);
        resultText = findViewById(R.id.text_view_result);
    }

    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userInfoText = checkIfFieldIsEmpty();
                if (userInfoText == null) return;

                int age = Integer.parseInt(userInfoText);

                if (age >= 18) {
                    displayBmiResult(calculateBmi());
                } else {
                    displayGuidance(calculateBmi());
                }
            }
        });
    }

    @Nullable
    private String checkIfFieldIsEmpty() {
        String userAgeText = ageText.getText().toString();
        String userMeterText = metersText.getText().toString();
        String userCentimeterText = centimetersText.getText().toString();
        String userWeightText = weightText.getText().toString();

        if (TextUtils.isEmpty(userAgeText)) {
            Toast.makeText(MainActivity.this, "Please fill out your age", Toast.LENGTH_LONG).show();
            return null;
        }
        if (TextUtils.isEmpty(userMeterText)) {
            Toast.makeText(MainActivity.this, "Please fill out the meter field", Toast.LENGTH_LONG).show();
            return null;
        }
        if (TextUtils.isEmpty(userCentimeterText)) {
            Toast.makeText(MainActivity.this, "Please fill out the centimeter field", Toast.LENGTH_LONG).show();
            return null;
        }
        if (TextUtils.isEmpty(userWeightText)) {
            Toast.makeText(MainActivity.this, "Please fill out your weight", Toast.LENGTH_LONG).show();
            return null;
        }

        int age = Integer.parseInt(userAgeText);
        int meter = Integer.parseInt(userMeterText);
        int centimeter = Integer.parseInt(userCentimeterText);
        int weight = Integer.parseInt(userWeightText);
        int metersAndCentimeters = meter * 100 + centimeter;

        if (age > 120){
            Toast.makeText(MainActivity.this, "Are you really" + age + " old?", Toast.LENGTH_LONG).show();
            return null;
        }
        if (centimeter > 99){
            Toast.makeText(MainActivity.this, "Centimeters cannot be bigger than 99!", Toast.LENGTH_LONG).show();
            return null;
        }
        if (metersAndCentimeters > 210 && metersAndCentimeters <=240){
            resultText.setText("As you're " + meter + "," + centimeter + "m long, please consult with your doctor for the healthy range.");
            return null;
        }
        if (metersAndCentimeters > 240){
            Toast.makeText(MainActivity.this, "Are you really " + meter + "," + centimeter + "m high, long?", Toast.LENGTH_LONG).show();
            return null;
        }
        if (weight >= 200 && weight < 300){
            resultText.setText("As you're " + weight + "kg, please consult with your doctor for the healthy range.");
            return null;
        }
        if (weight >= 300){
            Toast.makeText(MainActivity.this, "Are you really " + weight + "kg?", Toast.LENGTH_LONG).show();
            return null;
        }
        return userAgeText;
    }

    private double calculateBmi() {
        String userMeterText = metersText.getText().toString();
        String userCentimeterText = centimetersText.getText().toString();
        String userWeightText = weightText.getText().toString();

        double meter = Double.parseDouble(userMeterText);
        double centimeter = Double.parseDouble(userCentimeterText);
        int weight = Integer.parseInt(userWeightText);

        double heightInMeters = (meter + (centimeter / 100));
        return weight / (heightInMeters * heightInMeters);
    }

    private void displayBmiResult(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("00.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String result;

        if (bmi < 18.5) {
            result = "BMI: " + bmiTextResult + "\nYou are underweight";
        } else if (bmi > 25) {
            result = "BMI: " + bmiTextResult + "\nYou are overweight";
        } else {
            result = "BMI: " + bmiTextResult + "\nYou are a healthy weight";
        }
        resultText.setText(result);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("00.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String result;

        if (maleButton.isChecked()) {
            result = "BMI: " + bmiTextResult + "\nAs you are under 18, please consult with your doctor for the healthy range for boys";
        } else if (femaleButton.isChecked()) {
            result = "BMI: " + bmiTextResult + "\nAs you are under 18, please consult with your doctor for the healthy range for girls";
        } else {
            result = "BMI: " + bmiTextResult + "\nAs you are under 18, please consult with your doctor for the healthy range";
        }
        resultText.setText(result);
    }
}