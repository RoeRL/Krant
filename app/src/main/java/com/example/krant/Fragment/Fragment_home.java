package com.example.krant.Fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.krant.Adapter.NewsAdapter;
import com.example.krant.Model.NewsModel;
import com.example.krant.R;

import java.util.ArrayList;

public class Fragment_home extends Fragment {

    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private ArrayList<NewsModel> dataArrayList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.id_recyclerview);

        getData();

        adapter = new NewsAdapter(dataArrayList);

        RecyclerView.LayoutManager  layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    private void getData(){
        dataArrayList = new ArrayList<>();
        dataArrayList.add(new NewsModel("this is a title", "this is a description", String.valueOf(R.drawable.krant_logo)));
        dataArrayList.add(new NewsModel("this is a title", "this is a description", String.valueOf(R.drawable.krant_logo)));
        dataArrayList.add(new NewsModel("this is a title", "this is a description", String.valueOf(R.drawable.krant_logo)));
        dataArrayList.add(new NewsModel("this is a title", "this is a description", String.valueOf(R.drawable.krant_logo)));
        dataArrayList.add(new NewsModel("this is a title", "this is a description", String.valueOf(R.drawable.krant_logo)));
        dataArrayList.add(new NewsModel("this is a title", "this is a description", String.valueOf(R.drawable.krant_logo)));
        dataArrayList.add(new NewsModel("this is a title", "this is a description", String.valueOf(R.drawable.krant_logo)));
        dataArrayList.add(new NewsModel("this is a title", "this is a description", String.valueOf(R.drawable.krant_logo)));
        dataArrayList.add(new NewsModel("this is a title", "this is a description", String.valueOf(R.drawable.krant_logo)));
        dataArrayList.add(new NewsModel("this is a title", "this is a description", String.valueOf(R.drawable.krant_logo)));
        dataArrayList.add(new NewsModel("this is a title", "this is a description", String.valueOf(R.drawable.krant_logo)));
        dataArrayList.add(new NewsModel("this is a title", "this is a description", String.valueOf(R.drawable.krant_logo)));
        dataArrayList.add(new NewsModel("this is a title", "this is a description", String.valueOf(R.drawable.krant_logo)));
        dataArrayList.add(new NewsModel("this is a title", "this is a description", String.valueOf(R.drawable.krant_logo)));
        dataArrayList.add(new NewsModel("this is a title", "this is a description", String.valueOf(R.drawable.krant_logo)));
    }
}
