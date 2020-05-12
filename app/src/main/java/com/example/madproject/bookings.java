package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class bookings extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorAccent)));
        setContentView(R.layout.activity_bookings);
        t=(TextView)findViewById(R.id.bookres);
        CollectionReference user = db.collection("/Bookings");
        user.orderBy("time", Query.Direction.DESCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                StringBuffer ans = new StringBuffer(2000);
                ans.append("User          bus          from       to       booking time \n\n");
                if (task.isSuccessful()) {
                    for (final QueryDocumentSnapshot doc : task.getResult()) {
                        ans.append(doc.get("username")+"   "+doc.get("bus")+"   "+doc.get("src")+"   "+doc.get("dest")+"   "+doc.get("time").toString().substring(4,19)+"\n\n");
                    }
                    t.setText(ans);
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Error occured"+e,Toast.LENGTH_SHORT).show();
                    }
                });
    }
}