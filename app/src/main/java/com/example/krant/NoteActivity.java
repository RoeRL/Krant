package com.example.krant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.krant.Adapter.NoteAdapter;
import com.example.krant.Class.RealmHelper;
import com.example.krant.Model.DataNoteModel;
import com.example.krant.Model.NoteModel;
import com.google.android.material.transition.platform.MaterialContainerTransform;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class NoteActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private List<DataNoteModel> note_list;

    private Realm realm;
    private RealmHelper realmHelper;

    private Button btn_add_note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        btn_add_note = findViewById(R.id.btn_add_note);
        recyclerView = findViewById(R.id.id_recyclerview_note);

        //setup realm
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(realmConfiguration);

        realmHelper = new RealmHelper(realm);
        note_list = new ArrayList<>();

        //Mengisi data
        note_list = realmHelper.getAllData();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        show();

        btn_add_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NoteEditorActivity.class);
                startActivity(intent);
            }
        });


        confic();

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        adapter.notifyDataSetChanged();
        show();
    }

    public void show(){
        adapter = new NoteAdapter(note_list, new NoteAdapter.Callback() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getApplicationContext(), DetailNoteEditorActivity.class);
                intent.putExtra("id", note_list.get(position).getId());
                intent.putExtra("title", note_list.get(position).getTitle());
                intent.putExtra("content", note_list.get(position).getContent());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }


    private void confic() {
        findViewById(android.R.id.content).setTransitionName("note_transition");
        setEnterSharedElementCallback(new MaterialContainerTransformSharedElementCallback());

        MaterialContainerTransform transform = new MaterialContainerTransform();
        transform.addTarget(android.R.id.content);
        transform.setDuration(500);

        getWindow().setSharedElementEnterTransition(transform);
        getWindow().setSharedElementReturnTransition(transform);
    }
}