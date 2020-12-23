package com.example.animeapplication.data.repository.animedisplay;

import com.example.animeapplication.data.api.model.AnimeSearchResponse;

import io.reactivex.Single;

public interface AnimeDisplayRepository {

    Single<AnimeSearchResponse> getAnimeSearchResponse(String keywords);
}
