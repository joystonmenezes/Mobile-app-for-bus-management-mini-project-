package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class dataresult extends AppCompatActivity {


    // Access a Cloud Firestore instance from your Activity
    TextView textView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Intent intent;
    LinearLayout linearLayout;
    TextView rowTextView;
    ProgressBar loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataresult);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorAccent)));
        intent=getIntent();
       linearLayout=(LinearLayout)findViewById(R.id.reslay);
       loading=(ProgressBar)findViewById(R.id.progressBar2);
       loading.setVisibility(View.VISIBLE);
      // Toast.makeText(getApplicationContext(),intent.getStringExtra("place")+intent.getStringExtra("karkala"),Toast.LENGTH_SHORT).show();
       ReadSingleContact();

    }
    private void ReadSingleContact() {
        final int[] i = {0};
        CollectionReference user = db.collection(intent.getStringExtra("place")+"");
        user.whereEqualTo("dest", intent.getStringExtra("karkala")+"").get().addOnCompleteListener(new OnCompleteListener < QuerySnapshot > () {

            @Override
            public void onComplete(@NonNull Task< QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot doc : task.getResult()) {
/*
                        StringBuilder fields = new StringBuilder(120);
                        fields.append("\nName: ").append(doc.get("name"));
                        fields.append("\narrival: ").append(doc.get("arrival"));
                        fields.append("\ndeparture: ").append(doc.get("departure"));*/
// set some properties of rowTextView or something
                      LinearLayout v = (LinearLayout)linearLayout.getChildAt(i[0]);
                      v.setBackgroundResource(R.drawable.laydes);

                      i[0]++;
                      TextView from=(TextView)v.getChildAt(0);
                      from.setText("Mangalore\n"+doc.get("departure"));
                        TextView bus=(TextView)v.getChildAt(1);
                        bus.setText(doc.get("name")+"\n------->");
                        TextView to=(TextView)v.getChildAt(2);
                        to.setText("Karkala\n"+doc.get("arrival"));
                       // v.setText(fields);

                        // add the textview to the linearlayout
                       // linearLayout.addView(rowTextView);
                   /*     textView.setText(fields);
                        //Toast.makeText(getApplicationContext(),"TEST"+fields,Toast.LENGTH_SHORT).show();*/
                    }

                    linearLayout.removeViews(i[0],15-i[0]);
                    loading.setVisibility(View.INVISIBLE);

                    Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_SHORT).show();

                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Connection failed",Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.INVISIBLE);
                    }
                });
    }



}
