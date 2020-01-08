package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Password;
    private TextView Signup;
    private Button Login;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Signup = (TextView)findViewById((R.id.tvSignup));
        Login = (Button)findViewById(R.id.btnLogin);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
            finish();
            startActivity(new Intent(MainActivity.this, Start.class));
        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Username.getText().toString(), Password.getText().toString());
            }
        });

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });


    }

    private void validate(String userUsername, String userPassword) {

        progressDialog.setMessage("Please Wait...");
        progressDialog.show();


        firebaseAuth.signInWithEmailAndPassword(userUsername, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()){
                   progressDialog.dismiss();
                   Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(MainActivity.this, Start.class));

               } else{
                   progressDialog.dismiss();
                   Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
               }
            }
        });
    }
}

