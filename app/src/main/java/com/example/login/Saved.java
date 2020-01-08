package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Saved extends AppCompatActivity {

   DatabaseReference ref;
   ArrayList<Deal> list;
   RecyclerView recyclerView;
   SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);
        ref = FirebaseDatabase.getInstance().getReference().child("UserData").child("1");
        recyclerView = findViewById(R.id.rv);
        searchView = findViewById(R.id.searchView);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(ref!=null){
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        list = new ArrayList<>();
                        for(DataSnapshot ds : dataSnapshot.getChildren())
                        {
                            list.add(ds.getValue(Deal.class));
                        }
                        AdapterClass adapterClass = new AdapterClass(list);
                        recyclerView.setAdapter(adapterClass);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(Saved.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }
        if(searchView != null)
        {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return true;
                }
            });
        }
    }

    private void search(String str)
    {
        ArrayList<Deal> myList = new ArrayList<>();
        for (Deal object : list)
        {
            if(object.getUserTitle().toLowerCase().contains(str.toLowerCase()))
            {
                myList.add(object);

            }
        }
        AdapterClass adapterClass = new AdapterClass(myList);
        recyclerView.setAdapter(adapterClass);
    }
}
