package com.example.animeapplication.presentation.animedisplay.search.adapter;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AnimeItemViewModel implements Serializable {
    private String animeId;

    private String iconUrl;

    private boolean isFavorite;

    private String launchingDate;

    private String EndingDate;

    private String title;

    private int nbEpisodes;

    private String synopsis;

    public String getAnimeId() {
        return animeId;
    }

    public void setAnimeId(String animeId) {
        this.animeId = animeId;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNbEpisodes() {
        return nbEpisodes;
    }

    public void setNbEpisodes(int nbEpisodes) {
        this.nbEpisodes = nbEpisodes;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getLaunchingDate() {
        return launchingDate;
    }

    public void setLaunchingDate(String launchingDate) {
        this.launchingDate = launchingDate;
    }

    public String getEndingDate() {
        return EndingDate;
    }

    public void setEndingDate(String endingDate) {
        EndingDate = endingDate;
    }
}
