package com.example.vistacareai;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AiActivity extends AppCompatActivity {

    EditText edtQuestion;
    Button btnAsk;
    LinearLayout responseCard;
    TextView txtResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai);

        edtQuestion  = findViewById(R.id.edtQuestion);
        btnAsk       = findViewById(R.id.btnAsk);
        responseCard = findViewById(R.id.responseCard);
        txtResponse  = findViewById(R.id.txtResponse);

        btnAsk.setOnClickListener(v -> handleQuestion());
    }

    private void handleQuestion() {
        String question = edtQuestion.getText().toString().trim();

        if (question.isEmpty()) {
            Toast.makeText(this, "Please enter a question.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmergency(question)) {
            txtResponse.setText("⚠ This sounds like an emergency. Please call 000 (Australia) or your local emergency number immediately. Do not delay seeking help.");
            responseCard.setVisibility(View.VISIBLE);
            return;
        }

        String response = generateResponse(question);
        txtResponse.setText(response);
        responseCard.setVisibility(View.VISIBLE);
    }

    private boolean isEmergency(String text) {
        String q = text.toLowerCase();
        return q.contains("chest pain") || q.contains("can't breathe")
            || q.contains("suicide") || q.contains("overdose")
            || q.contains("unconscious") || q.contains("emergency")
            || q.contains("heart attack");
    }

    private String generateResponse(String question) {
        String q = question.toLowerCase();

        // Sleep
        if (q.contains("sleep") || q.contains("insomnia") || q.contains("tired")) {
            return "Getting quality sleep is one of the most important things you can do for your health. " +
                "Try to stick to a consistent sleep schedule, even on weekends. " +
                "Avoid screens for at least 30 minutes before bed, and keep your room cool and dark. " +
                "If you regularly struggle to sleep, speaking with a doctor is a good step.\n\n" +
                "⚠ This is general wellness information only — not medical advice.";
        }

        // Water / hydration
        if (q.contains("water") || q.contains("hydrat") || q.contains("drink")) {
            return "Staying well hydrated supports energy levels, skin health, and digestion. " +
                "Most adults benefit from around 8 glasses (2 litres) of water per day, " +
                "though this varies depending on your activity level and climate. " +
                "A simple check: your urine should be pale yellow — darker means drink more.\n\n" +
                "⚠ This is general wellness information only — not medical advice.";
        }

        // Period / cycle
        if (q.contains("period") || q.contains("cycle") || q.contains("menstrual")) {
            return "Menstrual cycles typically range from 21 to 35 days. " +
                "Tracking your cycle regularly helps you notice patterns and spot any changes early. " +
                "It is normal for cycles to vary slightly each month. " +
                "If you experience very heavy bleeding, severe pain, or irregular cycles, " +
                "it is worth discussing with a healthcare provider.\n\n" +
                "⚠ This is general wellness information only — not medical advice.";
        }

        // BMI / weight
        if (q.contains("bmi") || q.contains("weight") || q.contains("diet")) {
            return "BMI is a useful general indicator of healthy weight range, " +
                "but it does not account for muscle mass, bone density, or body composition. " +
                "A balanced approach to health includes a varied diet, regular movement, " +
                "adequate sleep, and managing stress — rather than focusing only on the number on the scale.\n\n" +
                "⚠ This is general wellness information only — not medical advice.";
        }

        // Stress
        if (q.contains("stress") || q.contains("anxious") || q.contains("anxiety") || q.contains("mental")) {
            return "Feeling stressed or anxious is very common, and there are practical things that can help. " +
                "Regular physical activity, mindfulness or deep breathing exercises, " +
                "and talking to someone you trust can all make a real difference. " +
                "If stress is significantly affecting your daily life, please consider speaking with a mental health professional.\n\n" +
                "⚠ This is general wellness information only — not medical advice.";
        }

        // Exercise
        if (q.contains("exercise") || q.contains("workout") || q.contains("fitness") || q.contains("active")) {
            return "Regular physical activity has wide-ranging benefits including improved mood, " +
                "better sleep, and reduced risk of chronic disease. " +
                "Aim for at least 150 minutes of moderate exercise per week — " +
                "this can be broken into shorter sessions. " +
                "Even a 20-minute walk each day is a great starting point.\n\n" +
                "⚠ This is general wellness information only — not medical advice.";
        }

        // Default
        return "Thank you for your question. For the most accurate and personalised health guidance, " +
            "it is always best to consult a qualified healthcare professional. " +
            "In the meantime, focusing on regular sleep, good hydration, balanced nutrition, " +
            "and daily movement are the foundations of good health for most people.\n\n" +
            "⚠ This is general wellness information only — not medical advice.";
    }
}
