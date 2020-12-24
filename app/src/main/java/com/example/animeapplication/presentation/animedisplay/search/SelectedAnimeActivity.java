package com.example.animeapplication.presentation.animedisplay.search;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.animeapplication.R;
import com.example.animeapplication.presentation.animedisplay.search.adapter.AnimeItemViewModel;

public class SelectedAnimeActivity extends AppCompatActivity {

    TextView anime_title;
    TextView anime_description;
    ImageView anime_image;
    TextView anime_launch;
    TextView anime_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_anime);


        anime_title = findViewById(R.id.anime_title);
        anime_description = findViewById(R.id.synopsis);
        anime_image = findViewById(R.id.imageView2);
        anime_launch = findViewById(R.id.launching_date);
        anime_end = findViewById(R.id.ending_date);

        Intent intent = getIntent();

        if (intent.getExtras()!= null){
            AnimeItemViewModel animeItemViewModel = (AnimeItemViewModel) intent.getSerializableExtra("data");
            anime_title.setText(animeItemViewModel.getTitle());
            Glide.with(anime_image)
                    .load(animeItemViewModel.getIconUrl())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(anime_image);
            anime_launch.setText("Launching date : \n"+animeItemViewModel.getLaunchingDate().substring(0,10));
            anime_end.setText("Ending date : \n"+animeItemViewModel.getEndingDate().substring(0,10));
            anime_description.setText("Synopsis : \n"+animeItemViewModel.getSynopsis());
        }
    }
}