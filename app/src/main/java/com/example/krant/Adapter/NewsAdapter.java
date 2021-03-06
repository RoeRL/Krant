package com.example.krant.Adapter;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.krant.Model.NewsModel;
import com.example.krant.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import javax.security.auth.callback.Callback;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    ArrayList<NewsModel> listData;
    Callback callback;

    public interface Callback{
        void onClick(int position);
    }

    public NewsAdapter(ArrayList<NewsModel> dataArrayList, Callback callback) {
        this.listData = dataArrayList;
        this.callback = callback;
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

        holder.txt_title.setText(listData.get(position).getTitle());
        Picasso.get()
                .load(listData.get(position).getImage())
                .into(holder.img_image);
    }

    @Override
    public int getItemCount() {
        return (listData != null) ? listData.size() : 0;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{

        private TextView txt_title;
        private ImageView img_image;
        private CardView cardView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_title = itemView.findViewById(R.id.txt_title_news);
            img_image = itemView.findViewById(R.id.img_image_news);
            cardView = itemView.findViewById(R.id.id_cardview);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onClick(getLayoutPosition());
                }
            });
        }
    }
}
