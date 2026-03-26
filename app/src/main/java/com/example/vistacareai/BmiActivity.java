package com.example.vistacareai;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class BmiActivity extends AppCompatActivity {

    EditText edtHeight, edtWeight;
    Button btnCalculate;
    LinearLayout resultCard;
    TextView txtBmiValue, txtCategory, txtAdvice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        edtHeight    = findViewById(R.id.edtHeight);
        edtWeight    = findViewById(R.id.edtWeight);
        btnCalculate = findViewById(R.id.btnCalculate);
        resultCard   = findViewById(R.id.resultCard);
        txtBmiValue  = findViewById(R.id.txtBmiValue);
        txtCategory  = findViewById(R.id.txtCategory);
        txtAdvice    = findViewById(R.id.txtAdvice);

        btnCalculate.setOnClickListener(v -> calculateBMI());
    }

    private void calculateBMI() {
        String heightStr = edtHeight.getText().toString().trim();
        String weightStr = edtWeight.getText().toString().trim();

        if (heightStr.isEmpty() || weightStr.isEmpty()) {
            Toast.makeText(this, "Please enter height and weight.", Toast.LENGTH_SHORT).show();
            return;
        }

        double h = Double.parseDouble(heightStr);
        double w = Double.parseDouble(weightStr);

        if (h <= 0 || w <= 0) {
            Toast.makeText(this, "Values must be greater than 0.", Toast.LENGTH_SHORT).show();
            return;
        }

        double bmi = w / (h * h);
        String category = getCategory(bmi);
        String advice   = getAdvice(category);

        txtBmiValue.setText(String.format("%.1f", bmi));
        txtCategory.setText(category);
        txtAdvice.setText(advice);
        resultCard.setVisibility(View.VISIBLE);
    }

    private String getCategory(double bmi) {
        if (bmi < 18.5) return "Underweight";
        if (bmi < 25)   return "Normal weight";
        if (bmi < 30)   return "Overweight";
        return "Obese";
    }

    private String getAdvice(String category) {
        switch (category) {
            case "Underweight":
                return "Your BMI suggests you may be underweight. Try to include more nutrient-rich foods in your diet such as proteins, healthy fats, and complex carbohydrates. Regular light exercise can also help build muscle mass.\n\n⚠ This is general wellness information only — please consult a doctor for personalised advice.";
            case "Normal weight":
                return "Great news! Your BMI is in a healthy range. Keep maintaining your balanced diet and regular physical activity. Staying hydrated and getting enough sleep will help you maintain this.\n\n⚠ This is general wellness information only — please consult a doctor for personalised advice.";
            case "Overweight":
                return "Your BMI is slightly above the healthy range. Consider adding more vegetables and whole foods to your meals and aim for at least 30 minutes of moderate exercise most days. Small consistent changes make a big difference.\n\n⚠ This is general wellness information only — please consult a doctor for personalised advice.";
            default:
                return "Your BMI is in the obese range. It is recommended to speak with a healthcare professional for a personalised health plan. Focus on gradual, sustainable lifestyle changes rather than quick fixes.\n\n⚠ This is general wellness information only — please consult a doctor for personalised advice.";
        }
    }
}
