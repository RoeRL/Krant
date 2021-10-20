package com.example.krant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.krant.Adapter.NewsAdapter;
import com.example.krant.Model.NewsModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private ArrayList<NewsModel> dataNews;

    private Button btn_note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        config();

        recyclerView = findViewById(R.id.id_recyclerview);
        btn_note = findViewById(R.id.btn_note);

        getData();

        adapter = new NewsAdapter(dataNews, new NewsAdapter.Callback() {
            @Override
            public void onClick(int position) {

                Toast.makeText(getApplicationContext(), "position "+ dataNews.get(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("title", dataNews.get(position).getTitle());
                intent.putExtra("description", dataNews.get(position).getDescription());
                intent.putExtra("image", dataNews.get(position).getImage());
                startActivity(intent);
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

    }

    private void config() {
        setExitSharedElementCallback(new MaterialContainerTransformSharedElementCallback());
        getWindow().setSharedElementsUseOverlay(false);
    }

    public void noteClick(View view){
        Intent intent = new Intent(getApplicationContext(), NoteActivity.class);
        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this, btn_note, "note_transition").toBundle();
        startActivity(intent, bundle);
    }

    public void profilClick(View view){
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this, btn_note, "profile_transition").toBundle();
        startActivity(intent, bundle);
    }

    private void getData(){
        dataNews = new ArrayList<>();
        dataNews.add(new NewsModel("title", "description", "image"));
        dataNews.add(new NewsModel("title", "description", "image"));
        dataNews.add(new NewsModel("title", "description", "image"));
        dataNews.add(new NewsModel("title", "description", "image"));
        dataNews.add(new NewsModel("title", "description", "image"));
        dataNews.add(new NewsModel("title", "description", "image"));
        dataNews.add(new NewsModel("title", "description", "image"));
        dataNews.add(new NewsModel("title", "description", "image"));
        dataNews.add(new NewsModel("title", "description", "image"));
        dataNews.add(new NewsModel("title", "description", "image"));
        dataNews.add(new NewsModel("title", "description", "image"));
        dataNews.add(new NewsModel("title", "description", "image"));
        dataNews.add(new NewsModel("title", "description", "image"));
    }
}