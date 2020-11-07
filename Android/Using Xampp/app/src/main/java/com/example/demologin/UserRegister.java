package com.example.demologin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



public class UserRegister extends AppCompatActivity {
    EditText name, Email, Password, age, contact, address;
    TextView btnLogin;
    Button btnRegister;

    ProgressDialog progressDialog;
    ConnectionClass connectionClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        name = (EditText) findViewById(R.id.name);
        Email = (EditText) findViewById(R.id.Email);
        Password = (EditText) findViewById(R.id.Password);
        age = (EditText) findViewById(R.id.age);

        contact = (EditText) findViewById(R.id.contact);
        address = (EditText) findViewById(R.id.address);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLogin = (TextView) findViewById(R.id.btnRegister);


        connectionClass = new ConnectionClass();

        progressDialog = new ProgressDialog(this);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Doregister doregister = new Doregister();
                doregister.execute("");
            }
        });


       // btnLogin.setOnClickListener(new View.OnClickListener() {
         //   @Override
         //   public void onClick(View v) {
           //     Dologin dologin = new Dologin();
             //   dologin.execute();
           // }
       // });


    }

    public class Doregister extends AsyncTask<String, String, String> {


        String namestr = name.getText().toString();
        String emailstr = Email.getText().toString();
        String passstr = Password.getText().toString();
        String z = "";
        boolean isSuccess = false;

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            if (namestr.trim().equals("") || emailstr.trim().equals("") || passstr.trim().equals(""))
                z = "Please enter all fields....";
            else {
                try {
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        z = "Please check your internet connection";
                    } else {

                        String query = "insert into pat values('" + namestr + "','" + emailstr + "','" + passstr + "')";

                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(query);

                        z = "Register successfull";
                        isSuccess = true;


                    }
                } catch (Exception ex) {
                    isSuccess = false;
                    z = "Exceptions" + ex;
                }
            }
            return z;
        }

        @Override
        protected void onPostExecute(String s) {

            Toast.makeText(getBaseContext(), "" + z, Toast.LENGTH_LONG).show();


            if (isSuccess) {
                startActivity(new Intent(UserRegister.this, Home_page.class));
            }

            progressDialog.hide();
        }
    }
}