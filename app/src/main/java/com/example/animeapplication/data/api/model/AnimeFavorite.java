package com.example.animeapplication.data.api.model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class AnimeFavorite {

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

    @SerializedName("aired")
    private Map<String, String> aired;


    public String getTitle() {
        Log.v("marche", title);
        return title;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getDescription() {
        return synopsis;
    }

    public Map<String, String> getAired() {
        return aired;
    }

    public String getId() {
        return id;
    }
}
