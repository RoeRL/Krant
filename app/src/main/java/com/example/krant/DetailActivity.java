package com.example.krant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private Bundle bundle;
    private ImageView img_image;
    private TextView txt_title;
    private TextView txt_description;
    private Button btn_more_detail;

    private String title;
    private String description;
    private String url;
    private String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txt_title = findViewById(R.id.txt_title_detail);
        txt_description = findViewById(R.id.txt_description_detail);
        img_image = findViewById(R.id.img_image_detail);
        btn_more_detail = findViewById(R.id.btn_more_detail);

        bundle = getIntent().getExtras();
        if (bundle != null){
            title = bundle.getString("title");
            description = bundle.getString("description");
            url = bundle.getString("url");
            image = bundle.getString("image");
        }

        txt_title.setText(title);
        txt_description.setText(description);
        Picasso.get()
                .load(image)
                .into(img_image);

        btn_more_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
    }
}
