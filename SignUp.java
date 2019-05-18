package com.example.aditya.firebase_login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    EditText email,pass;
    Button signup_btn;
    TextView signIn;

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        email=findViewById(R.id.emailsignup);
        pass=findViewById(R.id.passwordsignup);
        signup_btn=findViewById(R.id.signup_btn);
        signIn=findViewById(R.id.sininText);

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this,MainActivity.class));
                finish();
            }
        });
    }
    public void signUp(){
        String emailtext=email.getText().toString().trim();
        String passtext=pass.getText().toString().trim();
        firebaseAuth=FirebaseAuth.getInstance();

        if (TextUtils.isEmpty(emailtext)){
            Toast.makeText(this,"Email id field is empty",Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(passtext)){
            Toast.makeText(this,"Password field is empty",Toast.LENGTH_SHORT).show();
        }

        firebaseAuth.createUserWithEmailAndPassword(emailtext,passtext).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent=new Intent(SignUp.this,Profile.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Sign UP Sucessful",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
