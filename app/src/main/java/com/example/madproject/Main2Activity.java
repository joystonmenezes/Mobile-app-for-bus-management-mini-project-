package com.example.madproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //textView=(TextView)findViewById(R.id.textView2);
        Intent intent=getIntent();
        String ph=intent.getStringExtra("phone");
        //textView.setText("Welcome\n "+ph);
    }
}
