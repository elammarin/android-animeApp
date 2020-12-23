package com.example.animeapplication.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AnimeSearchResponse {

    @SerializedName("results")
    List<Anime> animeList;

    public List<Anime> getAnimeList() {
        return animeList;
    }

}
