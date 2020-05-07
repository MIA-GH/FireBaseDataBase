package com.musahibrahimali.firebasedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // list view instance
    TextView myListOfFacts;

    // The retrieve data button
    Button retDataBtn;

    // Creating a database reference to write data to
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("MyFActs");

    // The list of data to add to the database
    String value;
    ArrayList<String> myData = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myListOfFacts = findViewById(R.id.listItemView);
        retDataBtn = findViewById(R.id.retrieveDataBtn);

        String factOne = "hello this is fact number one";
        // write to database
        myRef.setValue(factOne);
        // display a toast message to show the data was successfully written to database
        Toast.makeText(MainActivity.this, "Data has been added successfully", Toast.LENGTH_SHORT).show();


        // retrieving data from  the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                value = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        retDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListOfFacts.setText(value);
            }
        });

    }

}

