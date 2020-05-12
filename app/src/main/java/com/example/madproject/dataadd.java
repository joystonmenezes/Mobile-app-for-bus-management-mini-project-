package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class dataadd extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText name,arr,dep;
    Intent intent;
    Button dadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataadd);
        name=(EditText)findViewById(R.id.bname);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorAccent)));
        arr=(EditText)findViewById(R.id.barrival);
        dep=(EditText)findViewById(R.id.bdep);
        intent=getIntent();
        dadd=(Button)findViewById(R.id.addbutton);
        dadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });

    }

    private void add() {
        CollectionReference user = db.collection("/Buses/"+intent.getStringExtra("place")+"/"+intent.getStringExtra("karkala"));
        Map<String, Object> city = new HashMap<>();
        city.put("name", name.getText().toString());
        city.put("arr", arr.getText().toString());
        city.put("dep", dep.getText().toString());
       user.document().set(city).addOnSuccessListener(new OnSuccessListener<Void>() {
           @Override
           public void onSuccess(Void aVoid) {
               Toast.makeText(getApplicationContext(),"Successfully added",Toast.LENGTH_SHORT).show();
           }
       })
               .addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       Toast.makeText(getApplicationContext(),"Error adding"+e,Toast.LENGTH_SHORT).show();
                   }
               });
    }

}
