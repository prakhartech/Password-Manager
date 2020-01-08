package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class dataentry extends AppCompatActivity {
    EditText userTitle, userWebsite, userLink, userUserId, userPass;
    Button saveButton;
    DatabaseReference reff;
    FirebaseAuth firebaseAuth;
    UserData userdata;
    long maxid=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataentry);
        userTitle = (EditText) findViewById(R.id.etTitle);
        userWebsite = (EditText) findViewById(R.id.etWebsite);
        userLink = (EditText) findViewById(R.id.etLink);
        userUserId = (EditText) findViewById(R.id.etUserid);
        userPass = (EditText) findViewById(R.id.etSitepass);
        saveButton = (Button) findViewById(R.id.btnSave);
        userdata = new UserData();
        reff = FirebaseDatabase.getInstance().getReference().child("UserData");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxid=(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userdata.setUserTitle(userTitle.getText().toString().trim());
                userdata.setUserLink(userLink.getText().toString().trim());
                userdata.setUserWebsite(userWebsite.getText().toString().trim());
                userdata.setUserUserId(userUserId.getText().toString().trim());
                userdata.setUserpass(userPass.getText().toString().trim());
                reff.child(String.valueOf(maxid+1)).setValue(userdata);

                Toast.makeText(dataentry.this, "Saved Successfully", Toast.LENGTH_LONG).show();
                startActivity(new Intent(dataentry.this, Start.class));
            }
        });
    }
}
