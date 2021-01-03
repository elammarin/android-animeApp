package com.example.animeapplication.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.animeapplication.data.entity.AnimeEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface AnimeDao {

    @Query("SELECT * from animeentity")
    Flowable<List<AnimeEntity>> loadFavorites();

    @Insert
    public Completable addAnimeToFavorites(AnimeEntity animeEntity);

    @Query("DELETE FROM animeentity WHERE id = :id")
    public Completable deleteAnimeFromFavorites(String id);

    @Query("SELECT id from animeentity")
    Single<List<String>> getFavoriteIdList();
}
