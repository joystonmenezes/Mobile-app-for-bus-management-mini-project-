package com.example.madproject;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
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
    RadioGroup rGroup;
    // This will get the radiobutton in the radiogroup that is checked
    RadioButton checkedRadioButton;
    TextView from,to;
    Intent intent,prev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bussearch);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorAccent)));
        spinner1=(Spinner)findViewById(R.id.spinner);
        spinner2=(Spinner)findViewById(R.id.spinner7);
        checkedRadioButton=(RadioButton)findViewById(R.id.radioButton);
        checkedRadioButton.setChecked(true);
        intent=new Intent(bussearch.this,dataresult.class);
        b=(Button)findViewById(R.id.button4);
        rGroup=(RadioGroup)findViewById(R.id.rg);
        to=(TextView)findViewById(R.id.textView10);
        to.setVisibility(View.INVISIBLE);
        findViewById(R.id.textView11).setVisibility(View.INVISIBLE);

       spinner2.setVisibility(View.INVISIBLE);

prev=getIntent();
intent.putExtra("user",prev.getStringExtra("user"));
intent.putExtra("contact",prev.getStringExtra("contact"));
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(intent);
            }
        });
        ArrayAdapter<CharSequence> arrayAdapter = new ArrayAdapter<CharSequence>(this,R.layout.support_simple_spinner_dropdown_item,stops);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = spinner1.getSelectedItem().toString();
                intent.putExtra("place",text);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(),"Please select a city",Toast.LENGTH_SHORT).show();
            }
        });
        spinner1.setAdapter(arrayAdapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = spinner2.getSelectedItem().toString();
                intent.putExtra("place",text);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(),"Please select a city",Toast.LENGTH_SHORT).show();
            }
        });
        spinner2.setAdapter(arrayAdapter);
        rGroup.setOnCheckedChangeListener(this);
        intent.putExtra("karkala","from");
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if(checkedId==R.id.radioButton2)
        {
            to.setVisibility(View.VISIBLE);
            findViewById(R.id.textView11).setVisibility(View.VISIBLE);
            findViewById(R.id.textView7).setVisibility(View.INVISIBLE);
            findViewById(R.id.textView9).setVisibility(View.INVISIBLE);
            spinner2.setVisibility(View.VISIBLE);
            spinner1.setVisibility(View.INVISIBLE);
            intent.putExtra("karkala","to");
        }
        else if(checkedId==R.id.radioButton)
        {
            intent.putExtra("karkala","from");
            to.setVisibility(View.INVISIBLE);
            findViewById(R.id.textView11).setVisibility(View.INVISIBLE);
            findViewById(R.id.textView7).setVisibility(View.VISIBLE);
            findViewById(R.id.textView9).setVisibility(View.VISIBLE);
            spinner2.setVisibility(View.INVISIBLE);
            spinner1.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
