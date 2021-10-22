package com.example.krant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {

    private Bundle bundle;
    private EditText input_username;
    private EditText input_email;
    private EditText input_password;
    private Button btn_save;

    private FirebaseAuth auth;
    private FirebaseFirestore firestore;
    private FirebaseUser user;

    private String user_id;
    private String username;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        input_username = findViewById(R.id.input_edit_username);
        input_email = findViewById(R.id.input_edit_email);
        input_password = findViewById(R.id.input_edit_password);
        btn_save = findViewById(R.id.btn_save_edit_profile);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        user = auth.getCurrentUser();

        bundle = getIntent().getExtras();
        if (bundle != null){
            username = bundle.getString("username");
            email = bundle.getString("email");
        }
        input_username.setText(username);
        input_email.setText(email);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = input_username.getText().toString();
                String email = input_email.getText().toString();
                String password = input_password.getText().toString();

                user_id = auth.getCurrentUser().getUid();
                DocumentReference reference = firestore.collection("data_users").document(user_id);
                Map<String, Object> objec_user = new HashMap<>();
                objec_user.put("username", username);
                objec_user.put("email", email);
                reference.update(objec_user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d("username", "onComplete: update username is successfully!");
                    }
                });

                if (!email.isEmpty() && !password.isEmpty()){
                    user.updatePassword(password);
                    user.updateEmail(email);
                }
                else{
                    Toast.makeText(getApplicationContext(), "masukkan email dan password!", Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(getApplicationContext(), "Update profile berhasil!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}