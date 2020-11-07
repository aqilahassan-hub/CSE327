package com.example.demologin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText Email, Password;
    Button btnLogin, btnForgot;
    Button btnRegister;

    //Firebase Auth
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    //Database Reference
    // private DatabaseReference mUserDetails = FirebaseDatabase.getInstance().getReference();

    ProgressDialog mRegProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email = (EditText) findViewById(R.id.Email);
        Password = (EditText) findViewById(R.id.Password);

        btnLogin = findViewById(R.id.btnUser);
        btnRegister = findViewById(R.id.btnRegister);
        btnForgot = findViewById(R.id.btnForgot);

        mRegProgress = new ProgressDialog(this);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email.getText().toString();
                String password = Password.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Email.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Email.setError("Password is Required");
                    return;
                }
                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {

                    mRegProgress.setTitle("LOGIN...");
                    mRegProgress.setMessage("Please Wait! We are Processing");
                    mRegProgress.setCanceledOnTouchOutside(false);
                    mRegProgress.show();

                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                        // String uid = currentUser.getUid();
                                        //mUserDetails.child("User_Type").child(uid).child("Type").setValue("Patient");
                                        //  HashMap<String,String> userDetails = new HashMap<>();
                                        //  userDetails.put("Name",name);
                                        //  userDetails.put("Age",age);
                                        // userDetails.put("Gender",gender);
                                        //  userDetails.put("Blood_Group",bloodgroup);
                                        //  userDetails.put("Contact_N0",contact);
                                        //  userDetails.put("Address",address);
                                        //  userDetails.put("Email",email);
                                        //   userDetails.put("Password",password);

                                        //   mUserDetails.child("Patient_Details").child(uid).setValue(userDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        //  @Override
                                        // public void onComplete (@NonNull Task < AuthResult > task) {
                                        //mRegProgress.dismiss();
                                        //if (task.isSuccessful()) {
                                        Toast.makeText(MainActivity.this, "Successfully Logged in", Toast.LENGTH_SHORT).show();
                                        Intent main_Intent = new Intent(MainActivity.this, Home_page.class);
                                        main_Intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(main_Intent);
                                    } else {
                                        mRegProgress.hide();
                                        Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserType.class);
                startActivity(intent);
            }
        });
        btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Forget_password.class);
                startActivity(intent);
            }
        });
    }

}

