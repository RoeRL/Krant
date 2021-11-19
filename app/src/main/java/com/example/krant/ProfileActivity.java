package com.example.krant;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.krant.Class.CheckLogin;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.transition.platform.MaterialContainerTransform;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private TextView txt_username;
    private TextView txt_email;
    private Button btn_edit_profile;
    private Button btn_logout;

    private FirebaseAuth auth;
    private FirebaseFirestore firestore;
    private String user_id;
    private GoogleSignInAccount signInAccount;

    private String username;
    private String email;

    private CheckLogin checkLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        config();

        txt_username = findViewById(R.id.txt_username_profile);
        txt_email = findViewById(R.id.txt_email_profile);
        btn_edit_profile = findViewById(R.id.btn_edit_profile);
        btn_logout = findViewById(R.id.btn_logout);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        signInAccount = GoogleSignIn.getLastSignedInAccount(getApplicationContext());


//        if (!CheckLogin.getResult()) {
//            txt_username.setText(signInAccount.getDisplayName());
//            txt_email.setText(signInAccount.getEmail());
//        }
//        if(CheckLogin.getResult().equals(true)){
            user_id = auth.getCurrentUser().getUid();
            DocumentReference documentReference = firestore.collection("data_users").document(user_id);
            documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                    username = value.getString("username");
                    email = value.getString("email");

                    txt_username.setText(username);
                    txt_email.setText(email);
                }
            });
//        }

        Log.d("ppppp", "onCreate: " + username +" "+ email);

        btn_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!CheckLogin.getResult()){
                    Toast.makeText(getApplicationContext(), "Tidak bisa edit profle, Karena anda login dengan google!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), EditProfileActivity.class);
                    intent.putExtra("username", username);
                    intent.putExtra("email", email);
                    startActivity(intent);
                }

            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firestore.disableNetwork();
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    private void config(){
        findViewById(android.R.id.content).setTransitionName("profile_transition");
        setEnterSharedElementCallback(new MaterialContainerTransformSharedElementCallback());

        MaterialContainerTransform transform = new MaterialContainerTransform();
        transform.addTarget(android.R.id.content);
        transform.setDuration(500);

        getWindow().setSharedElementEnterTransition(transform);
        getWindow().setSharedElementReturnTransition(transform);
    }
}
