package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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

import java.util.Calendar;
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
    private LocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataresult);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorAccent)));
        intent = getIntent();
        linearLayout = (LinearLayout) findViewById(R.id.reslay);
        loading = (ProgressBar) findViewById(R.id.progressBar2);
        loading.setVisibility(View.VISIBLE);
        // Toast.makeText(getApplicationContext(),intent.getStringExtra("place")+intent.getStringExtra("karkala"),Toast.LENGTH_SHORT).show();

        ReadSingleContact();


    }
    private void ReadSingleContact() {
        final int[] i = {0};
        CollectionReference user = db.collection("/Buses/"+intent.getStringExtra("place")+"/"+intent.getStringExtra("karkala"));
        user.orderBy("dep", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener < QuerySnapshot > () {

            @Override
            public void onComplete(@NonNull Task< QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    for (final QueryDocumentSnapshot doc : task.getResult()) {
                      LinearLayout v = (LinearLayout)linearLayout.getChildAt(i[0]);
                      v.setBackgroundResource(R.drawable.laydes);


v.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(dataresult.this);

        builder.setMessage("Do you want to book ticket ?");

        builder.setTitle("Alert !");

        builder.setCancelable(false);

        builder
                .setPositiveButton(
                        "Yes",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {
                                Map<String, Object> city = new HashMap<>();
                                city.put("username", intent.getStringExtra("user"));
                                city.put("contact", intent.getStringExtra("contact"));
                                city.put("time", Calendar.getInstance().getTime().toString());
                                city.put("bus",doc.get("name"));
                                if(intent.getStringExtra("karkala").toString().equals("from")) {
                                    city.put("dest", intent.getStringExtra("place"));
                                    city.put("src","karkala");

                                }
                                else
                                {
                                    city.put("dest","karkala" );
                                    city.put("src",intent.getStringExtra("place"));
                                }



                                db.collection("/Bookings/").document().set(city).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getApplicationContext(),"Successfully booked",Toast.LENGTH_SHORT).show();
                                    }
                                })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(getApplicationContext(),"Error booking"+e,Toast.LENGTH_SHORT).show();
                                            }
                                        });

                            }
                        });

        builder
                .setNegativeButton(
                        "No",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {

                                // If user click no
                                // then dialog box is canceled.
                                dialog.cancel();
                            }
                        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();

        // Show the Alert Dialog box
        alertDialog.show();

    }
});
                      i[0]++;
                      TextView from=(TextView)v.getChildAt(0);

                        TextView bus=(TextView)v.getChildAt(1);
                        bus.setText(doc.get("name")+"\nTo");
                        TextView to=(TextView)v.getChildAt(2);

                       // v.setText(fields);
if(intent.getStringExtra("karkala").toString().equals("from"))

{
from.setText(doc.get("dep").toString()+"\nKarkala");
to.setText(doc.get("arr").toString()+"\n"+intent.getStringExtra("place"));
}
else {
    from.setText(doc.get("dep")+"\n"+intent.getStringExtra("place")+"");
    to.setText(doc.get("arr")+"\nKarkala");
}
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

