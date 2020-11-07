package com.example.demologin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UserRegister extends AppCompatActivity {
    EditText Name,Email,Password,Age,Contact,Address,BloodGoup;
    TextView btnLogin;
    Button btnRegister;
    //DatabaseReference reff;
    User user;

    //RadioGroup & RadioButton
    private RadioGroup Gender;

    //Firebase Auth
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    //Database Reference
    private DatabaseReference mUserDetails = FirebaseDatabase.getInstance().getReference();

   ProgressDialog mRegProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        Name = (EditText) findViewById(R.id.Name);
       Email = (EditText) findViewById(R.id.Email);
        Password = (EditText) findViewById(R.id.Password);
        Age = (EditText) findViewById(R.id.Age);
        BloodGoup = (EditText) findViewById(R.id.BloodGoup);

        Contact = (EditText) findViewById(R.id.Contact);
        Address = (EditText) findViewById(R.id.Address);
        btnRegister = (Button) findViewById(R.id.btnRegister);
       // btnLogin = (TextView) findViewById(R.id.btnLogin);
        user=new User();
        //reff=FirebaseDatabase.getInstance().getReference().child("User");

        mRegProgress = new ProgressDialog(this);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name = Name.getText().toString();
                final String age = Age.getText().toString();
                final String bloodgroup = BloodGoup.getText().toString();
                final String contact = Contact.getText().toString();
                final String address = Address.getText().toString();
                final String email = Email.getText().toString();
                final String password = Password.getText().toString();
                String gender = "";

                user.setName(name.getBytes().toString().trim());
                user.setEmail(email);
                user.setPassword(password);
                user.setPhone(contact);
               // reff.push().setValue(user);
                Toast.makeText(UserRegister.this,"data Inserted",Toast.LENGTH_LONG).show();


                //RadioGroup
                Gender = (RadioGroup) findViewById(R.id.reg_gender_radiogroup);
                int checkedId = Gender.getCheckedRadioButtonId();

                if(checkedId == R.id.dr1_radiobtn){
                    gender = "Male";
                }
                else if(checkedId == R.id.dr2_radiobtn){
                    gender = "Female";
                }
                else if(checkedId == R.id.reg_other_radiobtn){
                    gender = "Other";
                }
                else {
                    Toast.makeText(getBaseContext(),"Select Gender",Toast.LENGTH_LONG).show();
                }

                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(name)&& !TextUtils.isEmpty(age)&& !TextUtils.isEmpty(contact)&&
                        !TextUtils.isEmpty(address)&&!TextUtils.isEmpty(bloodgroup)) {

                    mRegProgress.setTitle("Creating Account");
                    mRegProgress.setMessage("Please Wait! We are Processing");
                    mRegProgress.setCanceledOnTouchOutside(false);
                    mRegProgress.show();

                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(UserRegister.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {

                                        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                        String uid = currentUser.getUid();
                                        mUserDetails.child("User_Type").child(uid).child("Type").setValue("Patient");
                                        HashMap<String, String> userDetails = new HashMap<>();
                                        userDetails.put("Name", name);
                                        userDetails.put("Age", age);

                                        userDetails.put("Blood_Group", bloodgroup);
                                        userDetails.put("Contact_N0", contact);
                                        userDetails.put("Address", address);
                                        userDetails.put("Email", email);
                                        userDetails.put("Password", password);

                                        mUserDetails.child("Patient_Details").child(uid).setValue(userDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                mRegProgress.dismiss();
                                                //if (task.isSuccessful()) {
                                                Toast.makeText(UserRegister.this, "Account Successfully Created", Toast.LENGTH_SHORT).show();
                                                Intent main_Intent = new Intent(UserRegister.this, MainActivity.class);
                                                main_Intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                startActivity(main_Intent);
                                            }
                                        });
                                    }
                                            else

                                            {
                                                mRegProgress.hide();
                                                Toast.makeText(UserRegister.this, "Creating Account Failed", Toast.LENGTH_LONG).show();
                                            }


                                }
                            });
                    }
                else{

                    Toast.makeText(UserRegister.this,"Please fill all field",Toast.LENGTH_LONG).show();

                }

                //check all input given for creating account/register

                }
                // we can also varify email here
        });
    }
}