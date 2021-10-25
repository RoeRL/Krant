package com.example.krant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.krant.Class.RealmHelper;
import com.example.krant.Model.DataNoteModel;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailNoteEditorActivity extends AppCompatActivity {

    private Bundle bundle;
    private EditText edit_title;
    private EditText edit_content;
    private Button btn_save_note;
    private Button btn_delete_note;

    private Realm realm;
    private RealmHelper realmHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_note_editor);

        edit_title = findViewById(R.id.input_title_note_detil);
        edit_content = findViewById(R.id.input_content_note_detil);
        btn_save_note = findViewById(R.id.btn_save_note_detil);
        btn_delete_note = findViewById(R.id.btn_delete_note_detil);

        // Set up
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        bundle = getIntent().getExtras();
        if (bundle != null){

            String title = bundle.getString("title");
            String content = bundle.getString("content");

            edit_title.setText(title);
            edit_content.setText(content);
        }
        int id = bundle.getInt("id");

        btn_save_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edit_title.getText().toString();
                String content = edit_content.getText().toString();

                realmHelper.Update(id, title, content);
                Toast.makeText(getApplicationContext(), "Update successfully", Toast.LENGTH_SHORT).show();

            }
        });

        btn_delete_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realmHelper.Delete(id);
                Toast.makeText(getApplicationContext(), "Delete successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }
}