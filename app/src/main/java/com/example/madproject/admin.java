package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class admin extends AppCompatActivity {
EditText username,password;
Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorAccent)));
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        submit=(Button)findViewById(R.id.buttonsub);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("faraz")&&password.getText().toString().equals("1234"))
                {
                    startActivity(new Intent(admin.this, adminpage.class));

                }
                else
                    Toast.makeText(getApplicationContext(),"Invalid credentials",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
