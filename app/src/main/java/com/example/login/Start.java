package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Start extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth firebaseAuth;
    private DrawerLayout drawer;
    private Button addButton;
    private Button savedButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        firebaseAuth = FirebaseAuth.getInstance();
        setupUIViews();

        savedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Start.this, Saved.class));
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendataentry();

            }
        });





        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();




    }

    private void opendataentry(){
        Intent intent = new Intent(Start.this, dataentry.class);
        startActivity(intent);
    }

    private void setupUIViews(){
        addButton = (Button)findViewById(R.id.btnAdd);
        savedButton = (Button)findViewById(R.id.btnSaved);
    }

    private void Logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(Start.this, MainActivity.class));

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.notes:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MessageFragment()).commit();
                break;
            case R.id.address:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AddressFragment()).commit();
                break;
            case R.id.cards:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PaymentFragment()).commit();
                break;
            case R.id.contact:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ContactFragment()).commit();
                break;


        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuleft, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logoutMenu:
                Logout();

        }
        return super.onOptionsItemSelected(item);
    }
}
