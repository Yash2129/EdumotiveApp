package com.android.edumotive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText EmailId,Password;
    Button SignIn;

    FirebaseAuth firebaseAuth;
    ProgressBar LoginProgressBar;
    TextView CreateAcc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        EmailId = findViewById(R.id.emailBox);
        Password = findViewById(R.id.passwordBox);
        SignIn = findViewById(R.id.signInBtn);

        LoginProgressBar = findViewById(R.id.loginProgressBar);
        firebaseAuth = FirebaseAuth.getInstance();
        CreateAcc = findViewById(R.id.createHere);
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = EmailId.getText().toString().trim();
                String password = Password.getText().toString().trim();


                if (TextUtils.isEmpty(email)){
                    EmailId.setError("Email id is Required");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Password.setError("Password is Required");
                    return;
                }
                if (password.length() < 6){
                    Password.setError("Password Must be > 6 Characters ");
                    return;
                }

                LoginProgressBar.setVisibility(View.VISIBLE);
                SignIn.setVisibility(View.GONE);

                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"Logged In Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this,CourseActivity.class));
                            finish();
                        }
                        else {
                            Toast.makeText(LoginActivity.this,"Error"+ task.getException(),Toast.LENGTH_SHORT).show();
                            LoginProgressBar.setVisibility(View.GONE);
                            SignIn.setVisibility(View.VISIBLE);
                        }

                    }
                });
            }
        });

        CreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
                finish();
            }
        });
    }
}