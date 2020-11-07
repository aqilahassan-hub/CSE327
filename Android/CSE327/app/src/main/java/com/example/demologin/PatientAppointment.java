package com.example.demologin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PatientAppointment extends AppCompatActivity {
    Button Gynocologist,CardioSpecialist,eye,onto,skin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_appointment);
        Gynocologist = (Button) findViewById(R.id.Gynocologist);
        CardioSpecialist = (Button) findViewById(R.id.CardioSpecialist);
        eye = (Button) findViewById(R.id.eye);
        onto = (Button) findViewById(R.id.onto);
        skin = (Button) findViewById(R.id.skin);

        Gynocologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientAppointment.this, gyno.class);
                startActivity(intent);
            }
        });

        CardioSpecialist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientAppointment.this,cardio.class);
                startActivity(intent);
            }
        });
        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientAppointment.this, gyno.class);
                startActivity(intent);
            }
        });

        onto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientAppointment.this,cardio.class);
                startActivity(intent);
            }
        });
        skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientAppointment.this,cardio.class);
                startActivity(intent);
            }
        });
    }
}