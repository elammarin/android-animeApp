package com.example.animeapplication.data.api.model;

public class Anime {
    private String id;
    private AnimeInfo animeInfo;
    private boolean isFavorite;

    public String getId() {
        return id;
    }

    public AnimeInfo getAnimeInfo() {
        return animeInfo;
    }

    public void setFavorite() {
        isFavorite = true;
    }

    public boolean isFavorite() {
        return isFavorite;
    }
}
