package com.example.animeapplication.data.repository.animedisplay;

import com.example.animeapplication.data.api.model.AnimeSearchResponse;
import com.example.animeapplication.data.entity.AnimeEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface AnimeDisplayRepository {

    Single<AnimeSearchResponse> getAnimeSearchResponse(String keywords);

    Flowable<List<AnimeEntity>> getFavoriteAnimes();

    Completable addAnimeToFavorites(String bookId);

    Completable removeAnimeFromFavorites(String bookId);
}
