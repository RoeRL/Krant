package com.example.krant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private Bundle bundle;
    private ImageView img_image;
    private TextView txt_title;
    private TextView txt_description;

    private String title;
    private String description;
    private String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txt_title = findViewById(R.id.txt_title_detail);
        txt_description = findViewById(R.id.txt_description_detail);
        img_image = findViewById(R.id.img_image_detail);

        bundle = getIntent().getExtras();
        if (bundle != null){
            title = bundle.getString("title");
            description = bundle.getString("description");
            image = bundle.getString("image");
        }

        txt_title.setText(title);
        txt_description.setText(description);
        Picasso.get()
                .load(image)
                .into(img_image);
    }
}
