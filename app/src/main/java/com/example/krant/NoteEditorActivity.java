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

public class NoteEditorActivity extends AppCompatActivity {

    private EditText input_title;
    private EditText input_content;
    private Button btn_save_note;

    private Realm realm;
    private RealmHelper realmHelper;
    private DataNoteModel dataNoteModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        input_title = findViewById(R.id.input_title_note);
        input_content = findViewById(R.id.input_content_note);
        btn_save_note = findViewById(R.id.btn_save_note);

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(realmConfiguration);

        btn_save_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = input_title.getText().toString();
                String content = input_content.getText().toString();

                dataNoteModel = new DataNoteModel();
                dataNoteModel.setTitle(title);
                dataNoteModel.setContent(content);

                realmHelper = new RealmHelper(realm);
                realmHelper.Save(dataNoteModel);

                Toast.makeText(getApplicationContext(), "berhasil dibuat!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}