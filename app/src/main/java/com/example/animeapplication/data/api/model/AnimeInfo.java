package com.example.animeapplication.data.api.model;

import java.util.List;

public class AnimeInfo {
    private String title;
    private String imageLinks;
    private String description;
    private int nbEpisodes;
    private String launchingDate;
    private String endingDate;



    public String getTitle() {
        return title;
    }

    public String getImageLinks() {
        return imageLinks;
    }

    public String getDescription() {
        return description;
    }

    public int nbEpisodes() {
        return nbEpisodes;
    }

    public String getLaunchingDate() {
        return launchingDate;
    }

    public String getEndingDate() {
        return endingDate;
    }


}
