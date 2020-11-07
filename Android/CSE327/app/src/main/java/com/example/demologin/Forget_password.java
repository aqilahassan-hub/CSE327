package com.example.demologin;

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
import com.google.firebase.auth.FirebaseAuth;

public class Forget_password extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText Email;
    Button btnPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        mAuth = FirebaseAuth.getInstance();
        Email = (EditText) findViewById(R.id.Email);
        btnPassword = (Button) findViewById(R.id.btnPassword);


        btnPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailAddress = Email.getText().toString();

                if(!TextUtils.isEmpty(emailAddress)){
                    mAuth.sendPasswordResetEmail(emailAddress).addOnCompleteListener(Forget_password.this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Forget_password.this, "An Email Is Sent To Your Email", Toast.LENGTH_SHORT).show();
                                Intent main_Intent = new Intent(Forget_password.this, MainActivity.class);
                               // main_Intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(main_Intent);
                            }
                            else{
                                Toast.makeText(Forget_password.this,"Please Enter Correct Email To Reset Password",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
    }
}
