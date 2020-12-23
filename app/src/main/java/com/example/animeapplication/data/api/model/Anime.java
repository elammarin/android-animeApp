package com.example.animeapplication.data.api.model;

import com.google.gson.annotations.SerializedName;

public class Anime {

    @SerializedName("mal_id")
    private String id;


    @SerializedName("title")
    private String title;

    @SerializedName("image_url")
    private String image_url;

    @SerializedName("synopsis")
    private String synopsis;

    @SerializedName("episodes")
    private int episodes;

    @SerializedName("start_date")
    private String start_date;

    @SerializedName("end_date")
    private String end_date;

    private boolean isFavorite;

    public String getTitle() {
        return title;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getDescription() {
        return synopsis;
    }

    public int nbEpisodes() {
        return episodes;
    }
    public String getId() {
        return id;
    }

    public void setFavorite() {
        isFavorite = true;
    }

    public boolean isFavorite() {
        return isFavorite;
    }
}
