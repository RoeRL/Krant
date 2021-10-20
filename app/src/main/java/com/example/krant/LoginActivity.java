package com.example.krant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity{

    private com.google.android.material.textfield.TextInputLayout input_email;
    private com.google.android.material.textfield.TextInputLayout input_password;
    private Button btn_login;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        input_email = findViewById(R.id.input_email_login);
        input_password = findViewById(R.id.input_password_login);
        btn_login = findViewById(R.id.btn_login);

        auth = FirebaseAuth.getInstance();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = input_email.getEditText().getText().toString();
                String password = input_password.getEditText().getText().toString();

                if (email.isEmpty() && password.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Masukkan username dan password anda!", Toast.LENGTH_SHORT).show();
                }
                else if (email.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Masukkan username anda!", Toast.LENGTH_SHORT).show();
                }
                else if (password.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Masukkan password anda!", Toast.LENGTH_SHORT).show();
                }
                else {
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Login berhasil!", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Email atau password salah!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
    }



}