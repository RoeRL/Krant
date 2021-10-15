package com.example.krant.Adapter;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.krant.Model.NewsModel;
import com.example.krant.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    ArrayList<NewsModel> listData;

    public NewsAdapter(ArrayList<NewsModel> dataArrayList) {
        listData = dataArrayList;
    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder holder, int position) {

        holder.txt_name.setText(listData.get(position).getName());
        holder.txt_description.setText(listData.get(position).getDescription());
        Picasso.get()
                .load(listData.get(position).getImage())
                .into(holder.img_image);
    }

    @Override
    public int getItemCount() {
        return (listData != null) ? listData.size() : 0;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{

        private TextView txt_name;
        private TextView txt_description;
        private ImageView img_image;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_name = itemView.findViewById(R.id.txt_name_news);
            txt_description = itemView.findViewById(R.id.txt_description_news);
            img_image = itemView.findViewById(R.id.img_image_news);
        }
    }
}
