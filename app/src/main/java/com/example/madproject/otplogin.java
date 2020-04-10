package com.example.madproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class otplogin extends AppCompatActivity {


    private EditText editTextMobile,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otplogin);
        name=findViewById(R.id.editname);
        editTextMobile = findViewById(R.id.editTextMobile);
        final Intent intent2 = new Intent(otplogin.this, VerifyPhoneActivity.class);
        findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mobile = editTextMobile.getText().toString().trim();

                if(mobile.isEmpty() || mobile.length() < 10){
                    editTextMobile.setError("Enter a valid mobile");
                    editTextMobile.requestFocus();
                    return;
                }


                intent2.putExtra("mobile", mobile);
                intent2.putExtra("name",name.getText().toString());
                startActivity(intent2);
            }
        });
    }

}