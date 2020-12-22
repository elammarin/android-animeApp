package com.example.animeapplication.data.api;

import com.example.animeapplication.data.api.model.AnimeSearchResponse;
import com.example.animeapplication.data.api.model.Anime;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface AnimeDisplayService {
    @GET("search/anime")
    Single<AnimeSearchResponse> searchAnime (@Query("q") String keywords);

    @GET("anime/{animeId}")
    Single<Anime> getAnime(@Path("animeId") String animeId);
}


