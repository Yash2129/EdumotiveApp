package com.android.edumotive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class CourseActivity extends AppCompatActivity {

    Button Logout;
    TextView textView;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        Logout= findViewById(R.id.logoutBtn);
        textView = findViewById(R.id.userNameDisplay);
        firebaseAuth = FirebaseAuth.getInstance();

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                startActivity(new Intent(CourseActivity.this,LoginActivity.class));
                finish();

                String name = firebaseAuth.getCurrentUser().getDisplayName();
                textView.setText(name);

            }
        });
    }
}