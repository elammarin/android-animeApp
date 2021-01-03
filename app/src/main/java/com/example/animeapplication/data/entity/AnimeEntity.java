package com.example.animeapplication.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AnimeEntity {

    @NonNull
    @PrimaryKey
    public String id;

    public String title;
    public String episodes;
    private String synopsis;
    //private String launching_date;
    //private String ending_date;
    private String Image_url;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNb_episodes() {
        return episodes;
    }

    public void setNb_episodes(String episodes) {
        this.episodes = episodes;
    }

    public String getSynopsis() {
        return this.synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
/*
    public String getLaunching_date() {
        return launching_date;
    }

    public void setLaunching_date(String launching_date) {
        this.launching_date = launching_date;
    }

    public String getEnding_date() {
        return ending_date;
    }

    public void setEnding_date(String ending_date) {
        this.ending_date = ending_date;
    }
*/
    public String getImage_url() {
        return Image_url;
    }

    public void setImage_url(String image_url) {
        Image_url = image_url;
    }
}
