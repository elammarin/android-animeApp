package com.example.animeapplication.data.repository.animedisplay.mapper;

import android.text.TextUtils;

import com.example.animeapplication.data.api.model.Anime;
import com.example.animeapplication.data.api.model.AnimeFavorite;
import com.example.animeapplication.data.entity.AnimeEntity;

public class AnimeToAnimeEntityMapper {
    public AnimeEntity map(Anime anime) {
        AnimeEntity animeEntity = new AnimeEntity();
        animeEntity.setTitle(anime.getTitle());
        animeEntity.setSynopsis(anime.getDescription());
        //animeEntity.setLaunching_date(anime.getStart_date());
        animeEntity.setId(anime.getId());
        //animeEntity.setEnding_date(anime.getEnd_date());
        animeEntity.setImage_url(anime.getImage_url());
        return animeEntity;
    }
}
