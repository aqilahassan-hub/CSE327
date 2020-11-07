package com.example.demologin;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Calender extends AppCompatActivity {
    private EditText mFeedbackText;
    private Button mSubmitFeedback;
    private String currnetUID;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        currnetUID = mAuth.getCurrentUser().getUid().toString();
        mFeedbackText = (EditText) findViewById(R.id.Date);
        mSubmitFeedback = (Button) findViewById(R.id.btnconfirm);
        mSubmitFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String appoint = mFeedbackText.getText().toString();
                mDatabase.child("Appoitment").child(mAuth.getCurrentUser().getUid()).push().child("Appointment").setValue(appoint);

                startActivity(new Intent(Calender.this,Home_page.class));
            }
        });
    }
}