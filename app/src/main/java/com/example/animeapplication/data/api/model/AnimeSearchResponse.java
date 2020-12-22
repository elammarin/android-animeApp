package com.example.animeapplication.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AnimeSearchResponse {

    @SerializedName("items")
    List<Anime> animeList;

    int totalItems;

    public List<Anime> getBookList() {
        return animeList;
    }

    public int getTotalItems() {
        return totalItems;
    }

}
