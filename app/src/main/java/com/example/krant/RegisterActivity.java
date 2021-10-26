package com.example.krant;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText input_username_register;
    private EditText input_email_register;
    private EditText input_password_register;
    private Button btn_register;
    private TextView btn_login;

    private FirebaseAuth auth;
    private FirebaseFirestore firestore;
    private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        input_username_register = findViewById(R.id.input_username_register);
        input_email_register = findViewById(R.id.input_email_register);
        input_password_register = findViewById(R.id.input_password_register);
        btn_register = findViewById(R.id.btn_register);
        btn_login = findViewById(R.id.txt_buttonLogin);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = input_username_register.getText().toString();
                String email = input_email_register.getText().toString();
                String password = input_password_register.getText().toString().trim();

                if (username.isEmpty() && email.isEmpty() && password.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Masukkan username email dan password anda!", Toast.LENGTH_SHORT).show();
                }
                else if (username.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Masukkan username anda!", Toast.LENGTH_SHORT).show();
                }
                else if (email.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Masukkan username anda!", Toast.LENGTH_SHORT).show();
                }
                else if (password.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Masukkan password anda!", Toast.LENGTH_SHORT).show();
                }
                else if (password.length() < 8){
                    Toast.makeText(getApplicationContext(), "passoword harus 8 karakter atau lebih!", Toast.LENGTH_SHORT).show();
                }
                else {
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Register berhasil!", Toast.LENGTH_SHORT).show();

                                user_id = auth.getCurrentUser().getUid();
                                DocumentReference reference = firestore.collection("data_users").document(user_id);
                                Map<String, Object> user = new HashMap<>();
                                user.put("username", username);
                                user.put("email", email);
                                reference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Log.d("ppppppppppppp", "onSuccess: insert successfully");
                                    }
                                });


                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(getApplicationContext(), "Register gagal!", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}