package com.android.edumotive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    EditText UserName,EmailId,Password,ConfirmPassword;
    Button SignUp;
    ImageButton GoogleBtn;
    ProgressBar progressBar;
    TextView AlreadyAccount;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        UserName = findViewById(R.id.UserName);
        EmailId = findViewById(R.id.emailBox);
        Password = findViewById(R.id.passwordBox);
        ConfirmPassword = findViewById(R.id.confirmPasswordBox);
        SignUp = findViewById(R.id.signUpBtn);
        GoogleBtn = findViewById(R.id.googleBtn);
        progressBar = findViewById(R.id.signUp_progressBar);
        AlreadyAccount = findViewById(R.id.alreadyAccount);
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),CourseActivity.class));
            finish();
        }

        SignUp.setOnClickListener(new View.OnClickListener() {
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

                progressBar.setVisibility(View.VISIBLE);
                SignUp.setVisibility(View.GONE);

                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignUpActivity.this,"User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                            finish();

                        }
                        else {
                            Toast.makeText(SignUpActivity.this,"Error"+ task.getException(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            SignUp.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        });

        AlreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                finish();
            }
        });

    }
}