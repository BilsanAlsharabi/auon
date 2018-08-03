package com.example.asus.placepicker;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VolunteerActivity extends AppCompatActivity {

    Button confirmButton;
    TextView textViewLocation;
    TextView textViewService;
    FirebaseDatabase database;
    DatabaseReference myRef;
    DatabaseReference serviceRef;
    DatabaseReference confirmRef;

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Location");
        serviceRef = database.getReference("Service");
        confirmRef = database.getReference("confirm");
        confirmRef.setValue("true");
        textViewLocation = findViewById(R.id.tV_location);
        textViewService = findViewById(R.id.tv_Service);
        confirmButton = findViewById(R.id.btn_confirm);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                textViewLocation.setText(value);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        serviceRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                textViewService.setText("نوع الخدمة: \n"+value);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmRef.setValue("true");
                Intent intent = new Intent(VolunteerActivity.this, MainActivity.class);
                startActivity(intent);
                Intent intentI = new Intent(VolunteerActivity.this, MyService.class);
                startService(intentI);





            }

        });

    }
}