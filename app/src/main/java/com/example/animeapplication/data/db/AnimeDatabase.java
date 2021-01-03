package com.example.animeapplication.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.animeapplication.data.entity.AnimeEntity;

@Database(entities = {AnimeEntity.class}, version = 1)
public abstract class AnimeDatabase extends RoomDatabase {
    public abstract AnimeDao animeDao();
}