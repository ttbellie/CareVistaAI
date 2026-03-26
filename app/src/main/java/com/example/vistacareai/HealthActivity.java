package com.example.vistacareai;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class HealthActivity extends AppCompatActivity {

    EditText edtWater, edtSleep, edtMood;
    Button btnSave;
    LinearLayout summaryCard;
    TextView txtSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        edtWater    = (EditText)      findViewById(R.id.edtWater);
        edtSleep    = (EditText)      findViewById(R.id.edtSleep);
        edtMood     = (EditText)      findViewById(R.id.edtMood);
        btnSave     = (Button)        findViewById(R.id.btnSave);
        summaryCard = (LinearLayout)  findViewById(R.id.summaryCard);
        txtSummary  = (TextView)      findViewById(R.id.txtSummary);

        btnSave.setOnClickListener(v -> saveLog());
    }

    private void saveLog() {
        String water = edtWater.getText().toString().trim();
        String sleep = edtSleep.getText().toString().trim();
        String mood  = edtMood.getText().toString().trim();

        if (water.isEmpty() || sleep.isEmpty() || mood.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        String insight = generateInsight(water, sleep, mood);

        txtSummary.setText(
            "💧 Water: " + water + " glasses\n" +
                "😴 Sleep: " + sleep + " hours\n" +
                "😊 Mood: " + mood  + "\n\n" +
                "💡 AI Insight:\n" + insight
        );

        summaryCard.setVisibility(View.VISIBLE);
    }

    private String generateInsight(String water, String sleep, String mood) {
        int w = 0;
        double s = 0;
        try { w = Integer.parseInt(water); } catch (Exception ignored) {}
        try { s = Double.parseDouble(sleep); } catch (Exception ignored) {}

        StringBuilder sb = new StringBuilder();

        if (w < 6) {
            sb.append("You are below the recommended water intake. Try to drink at least 8 glasses of water today to stay hydrated.\n\n");
        } else {
            sb.append("Great hydration! Keeping up your water intake supports energy and focus throughout the day.\n\n");
        }

        if (s < 7) {
            sb.append("You got less than 7 hours of sleep. Try to go to bed 30 minutes earlier tonight.\n\n");
        } else {
            sb.append("You are getting a healthy amount of sleep. Consistent sleep helps regulate mood and overall wellbeing.\n\n");
        }

        String moodLower = mood.toLowerCase();
        if (moodLower.contains("stress") || moodLower.contains("anxious") || moodLower.contains("tired")) {
            sb.append("You seem to be feeling a bit low today. Try a short walk or some deep breathing.");
        } else if (moodLower.contains("happy") || moodLower.contains("great") || moodLower.contains("good")) {
            sb.append("You are in a great mood today! Keep doing what is working for you.");
        } else {
            sb.append("Thank you for logging your mood. Tracking how you feel each day helps you spot patterns.");
        }

        sb.append("\n\n⚠ This is general wellness information only — not medical advice.");
        return sb.toString();
    }
}
