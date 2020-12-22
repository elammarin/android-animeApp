package com.example.animeapplication.data.repository.animedisplay;

import com.example.animeapplication.data.repository.animedisplay.remote.AnimeDisplayRemoteDataSource;

public class AnimeDisplayDataRepository {

    private AnimeDisplayRemoteDataSource bookDisplayRemoteDataSource;

    public AnimeDisplayDataRepository(AnimeDisplayRemoteDataSource bookDisplayRemoteDataSource) {
        this.bookDisplayRemoteDataSource = bookDisplayRemoteDataSource;
    }
}
