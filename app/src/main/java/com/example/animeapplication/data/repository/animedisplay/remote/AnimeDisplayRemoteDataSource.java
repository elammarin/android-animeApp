package com.example.animeapplication.data.repository.animedisplay.remote;

import io.reactivex.Single;
import com.example.animeapplication.AnimeApplication;
import com.example.animeapplication.data.api.AnimeDisplayService;
import com.example.animeapplication.data.api.model.Anime;
import com.example.animeapplication.data.api.model.AnimeSearchResponse;

public class AnimeDisplayRemoteDataSource {
    private AnimeDisplayService animeDisplayService;

    public AnimeDisplayRemoteDataSource(AnimeDisplayService animeDisplayService) {
        this.animeDisplayService = animeDisplayService;
    }

    public Single<AnimeSearchResponse> getAnimeSearchResponse(String keywords) {
        return animeDisplayService.searchAnime(keywords);
    }

    public Single<Anime> getAnime(String animeId) {
        return animeDisplayService.getAnime(animeId);
    }
}
