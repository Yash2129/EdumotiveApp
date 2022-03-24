package com.android.edumotive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class CourseActivity extends AppCompatActivity {

    Button Logout;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        Logout= findViewById(R.id.logoutBtn);
        firebaseAuth = FirebaseAuth.getInstance();

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                startActivity(new Intent(CourseActivity.this,LoginActivity.class));
                finish();
            }
        });
    }
}