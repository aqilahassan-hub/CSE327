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

public class patient_profile extends AppCompatActivity {
    private TextView mName, mEmail,mAge,mBlood;


    private String currnetUID;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);

        currnetUID = mAuth.getCurrentUser().getUid().toString();

        mName = (TextView) findViewById(R.id.feedback_name);
        mEmail = (TextView) findViewById(R.id.feedback_email);
        mAge = (TextView) findViewById(R.id.feedback_age);
        mBlood = (TextView) findViewById(R.id.feedback_blood);

      //  mGender = (TextView) findViewById(R.id.feedback_gender);


    }

    @Override
    protected void onStart() {
        super.onStart();

        mDatabase.child("User_Type").child(currnetUID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String type = dataSnapshot.child("Type").getValue().toString();
                final String[] name = {""};
                final String[] email = {""};
                final String[] age = {""};
                final String[] blood = {""};
               // final String[] contact = {""};
               // final String[] gender = {""};

                if(type.equals("Patient")){

                    mDatabase.child("Patient_Details").child(currnetUID).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            name[0] = dataSnapshot.child("Name").getValue().toString();
                            email[0] = dataSnapshot.child("Email").getValue().toString();
                            age[0] = dataSnapshot.child("Age").getValue().toString();
                            blood[0] = dataSnapshot.child("Blood_Group").getValue().toString();
                           // contact[0] = dataSnapshot.child("Contact_NO").getValue().toString();
                          //  gender[0] = dataSnapshot.child("Gender").getValue().toString();

                            mName.setText(name[0]);
                            mEmail.setText(email[0]);
                            mAge.setText(age[0]);
                            mBlood.setText(blood[0]);
                          //  mContact.setText(contact[0]);
                          //  mGender.setText(gender[0]);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }else if(type.equals("Doctor")){

                    mDatabase.child("Doctor_Details").child(currnetUID).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            name[0] = dataSnapshot.child("Name").getValue().toString();
                            email[0] = dataSnapshot.child("Email").getValue().toString();

                            mName.setText(name[0]);
                            mEmail.setText(email[0]);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }else {

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
