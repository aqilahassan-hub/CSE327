package com.example.demologin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Home_page extends AppCompatActivity {
    Button btnFeedback,btnProfile,btnAppoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        btnFeedback = (Button) findViewById(R.id.btnFeedback);
        btnProfile = (Button) findViewById(R.id.btnProfile);
        btnAppoint = (Button) findViewById(R.id.btnAppoint);
        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_page.this, PatientFeedback.class);
                startActivity(intent);
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_page.this, patient_profile.class);
                startActivity(intent);
            }
        });

        btnAppoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_page.this, PatientAppointment.class);
                startActivity(intent);
            }
        });
    }
}
