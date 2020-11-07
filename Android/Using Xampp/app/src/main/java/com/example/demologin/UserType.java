package com.example.demologin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class UserType extends AppCompatActivity {
    Button btnUser;
    Button btnDoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type);

        btnUser=findViewById(R.id.btnUser);
        btnDoctor=findViewById(R.id.btnDoctor);
        btnUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                OpenUserReg();
            }
        });
        btnDoctor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                OpenDocReg();
            }
        });
    }
    public void OpenUserReg(){
        Intent intent=new Intent(this,UserRegister.class);
        startActivity(intent);
    }
    public void OpenDocReg(){
        Intent intent=new Intent(this,DoctorRegister.class);
        startActivity(intent);
    }


}
