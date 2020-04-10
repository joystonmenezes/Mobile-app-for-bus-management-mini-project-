package com.example.madproject;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class bussearch extends AppCompatActivity implements AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {
Spinner spinner1,spinner2;
    String[] stops = { "--select a city--","Mangalore", "Moodbidre", "Udupi", "Belthangady", "Sringeri","Hebri"};
    Button b;
    Switch s;
    TextView from,to;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bussearch);
        spinner1=(Spinner)findViewById(R.id.spinner);
        spinner2=(Spinner)findViewById(R.id.spinner7);
        b=(Button)findViewById(R.id.button4);
        s=(Switch)findViewById(R.id.switch1);
        to=(TextView)findViewById(R.id.textView10);
        to.setVisibility(View.INVISIBLE);
        findViewById(R.id.textView11).setVisibility(View.INVISIBLE);
        s.setOnCheckedChangeListener(this);
       spinner2.setVisibility(View.INVISIBLE);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ArrayAdapter<CharSequence> arrayAdapter = new ArrayAdapter<CharSequence>(this,R.layout.support_simple_spinner_dropdown_item,stops);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setOnItemSelectedListener(this);
        spinner1.setAdapter(arrayAdapter);
        spinner2.setOnItemSelectedListener(this);
        spinner2.setAdapter(arrayAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
      //  Toast.makeText(this,"you selected "+parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this,"Please select a city",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked)
        {
            to.setVisibility(View.VISIBLE);
            findViewById(R.id.textView11).setVisibility(View.VISIBLE);
            findViewById(R.id.textView7).setVisibility(View.INVISIBLE);
            findViewById(R.id.textView9).setVisibility(View.INVISIBLE);
            spinner2.setVisibility(View.VISIBLE);
            spinner1.setVisibility(View.INVISIBLE);
        }
        else
        {
            to.setVisibility(View.INVISIBLE);
            findViewById(R.id.textView11).setVisibility(View.INVISIBLE);
            findViewById(R.id.textView7).setVisibility(View.VISIBLE);
            findViewById(R.id.textView9).setVisibility(View.VISIBLE);
            spinner2.setVisibility(View.INVISIBLE);
            spinner1.setVisibility(View.VISIBLE);
        }

    }
}
