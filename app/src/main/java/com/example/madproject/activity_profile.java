package com.example.madproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class activity_profile extends AppCompatActivity {


    TextView textView;
    FirebaseAuth mAuth;
    Button b,signout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();

      textView=(TextView)findViewById(R.id.textView3);
      b=(Button)findViewById(R.id.button3);
      signout=(Button)findViewById(R.id.button5);
      signout.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              mAuth.signOut();
              startActivity(new Intent(activity_profile.this,MainActivity.class));
          }
      });
      b.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(activity_profile.this,bussearch.class));
          }
      });

        FirebaseUser user = mAuth.getCurrentUser();



        textView.setText("Welcome\n"+user.getDisplayName());

    }

    @Override
    protected void onStart() {
        super.onStart();

        //if the user is not logged in
        //opening the login activity
        if (mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}