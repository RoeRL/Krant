package com.example.krant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private com.google.android.material.textfield.TextInputLayout input_username;
    private com.google.android.material.textfield.TextInputLayout input_password;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        input_username = findViewById(R.id.input_username);
        input_password = findViewById(R.id.input_password);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        String username = input_username.getEditText().getText().toString();
        String password = input_password.getEditText().getText().toString();

        if (view.equals(btn_login)){
            if (username.equals("admin") && password.equals("admin123")){
                Toast.makeText(getApplicationContext(), "Login berhasil", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
            else if (username.isEmpty() && password.isEmpty()){
                Toast.makeText(getApplicationContext(), "Masukkan username dan password anda!", Toast.LENGTH_SHORT).show();
            }
            else if (username.isEmpty()){
                Toast.makeText(getApplicationContext(), "Masukkan username anda!", Toast.LENGTH_SHORT).show();
            }
            else if (password.isEmpty()){
                Toast.makeText(getApplicationContext(), "Masukkan password anda!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Username atau password anda salah!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}