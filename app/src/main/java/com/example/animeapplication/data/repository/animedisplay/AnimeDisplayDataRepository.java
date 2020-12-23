package com.example.animeapplication.data.repository.animedisplay;

import com.example.animeapplication.data.api.model.AnimeSearchResponse;
import com.example.animeapplication.data.repository.animedisplay.remote.AnimeDisplayRemoteDataSource;

import io.reactivex.Single;

public class AnimeDisplayDataRepository implements AnimeDisplayRepository {

    private AnimeDisplayRemoteDataSource bookDisplayRemoteDataSource;

    public AnimeDisplayDataRepository(AnimeDisplayRemoteDataSource bookDisplayRemoteDataSource) {
        this.bookDisplayRemoteDataSource = bookDisplayRemoteDataSource;
    }

    @Override
    public Single<AnimeSearchResponse> getAnimeSearchResponse(String keywords) {
        return bookDisplayRemoteDataSource.getAnimeSearchResponse(keywords);

    }
}
