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

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.krant.Adapter.NewsAdapter;
import com.example.krant.Model.NewsModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private ArrayList<NewsModel> dataNews;

    private Button btn_note;
    private Button btn_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        config();

        recyclerView = findViewById(R.id.id_recyclerview);
        btn_note = findViewById(R.id.btn_note);
        btn_profile = findViewById(R.id.btn_profile);

        getData();

        btn_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NoteActivity.class);
                startActivity(intent);
            }
        });

        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void getData(){
        AndroidNetworking.get("https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=be09ca37822e492480ded90ae3697682")
                .addPathParameter("pageNumber", "0")
                .addQueryParameter("limit", "3")
                .addHeaders("token", "1234")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            dataNews = new ArrayList<>();

                            JSONArray jsonArray = response.getJSONArray("articles");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String title = object.getString("title");
                                String content = object.getString("content");
                                String url = object.getString("url");
                                String image = object.getString("urlToImage");

                                dataNews.add(new NewsModel(title, content, url, image));
                            }

                            adapter = new NewsAdapter(dataNews, new NewsAdapter.Callback() {
                                @Override
                                public void onClick(int position) {

                                    Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                                    intent.putExtra("title", dataNews.get(position).getTitle());
                                    intent.putExtra("description", dataNews.get(position).getDescription());
                                    intent.putExtra("url", dataNews.get(position).getUrl());
                                    intent.putExtra("image", dataNews.get(position).getImage());
                                    startActivity(intent);
                                }
                            });

                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(layoutManager);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
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
}