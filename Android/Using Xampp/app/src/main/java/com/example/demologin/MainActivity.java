package com.example.demologin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText Email,Password;
    Button btnLogin,btnForgot;
    Button btnRegister;

    ProgressDialog progressDialog;
    ConnectionClass connectionClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Email = (EditText) findViewById(R.id.Email);
        Password = (EditText) findViewById(R.id.Password);

        btnLogin=findViewById(R.id.btnUser);
        btnRegister=findViewById(R.id.btnRegister);
        btnForgot=findViewById(R.id.btnForgot);
        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                validate(Email.getText().toString(),Password.getText().toString());
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dologin dologin=new Dologin();
                dologin.execute();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                OpenReg();
            }
        });
        btnForgot.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                OpenForgot();
            }
        });

        connectionClass = new ConnectionClass();

        progressDialog=new ProgressDialog(this);
    }
    private class Dologin extends AsyncTask<String,String,String>{

        String emailstr=Email.getText().toString();
        String passstr=Password.getText().toString();
        String z="";
        boolean isSuccess=false;
        String nm,em,password;


        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Loading...");
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            if(emailstr.trim().equals("") ||passstr.trim().equals(""))
                z = "Please enter all fields....";
            else
            {
                try {
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        z = "Please check your internet connection";
                    } else {

                        String query=" select * from pat where Email='"+emailstr+"' and password = '"+passstr+"'";


                        Statement stmt = con.createStatement();
                        // stmt.executeUpdate(query);

                        ResultSet rs=stmt.executeQuery(query);

                        while (rs.next())

                        {
                            nm= rs.getString(1);
                            em=rs.getString(2);
                            password=rs.getString(3);

                            if( em.equals(emailstr)&&password.equals(passstr))
                            {

                                isSuccess=true;
                                z = "Login successfull";
                            }
                            else

                                isSuccess=false;
                        }

                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = "Exceptions"+ex;
                }
            }
            return z;        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getBaseContext(),""+z,Toast.LENGTH_LONG).show();


            if(isSuccess) {

                Intent intent=new Intent(MainActivity.this,Home_page.class);

                intent.putExtra("email",emailstr);

                startActivity(intent);
            }


            progressDialog.hide();

        }
    }

    public void OpenReg(){
        Intent intent=new Intent(this,UserRegister.class);
        startActivity(intent);
    }
    public void OpenForgot(){
        Intent intent=new Intent(this,Forget_password.class);
        startActivity(intent);
    }
    private void validate(String userName, String userPassword){
        if((userName.equals("Admin")) && (userPassword.equals("1234"))){
            Intent intent=new Intent(MainActivity.this, Home_page.class);
            startActivity(intent);
        }
}
}