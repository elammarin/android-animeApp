package com.example.animeapplication;

import android.app.Application;

import com.example.animeapplication.data.di.MockDependencyInjection;
import com.facebook.stetho.Stetho;

public class AnimeApplication extends Application {

    @Override
    public void onCreate(){
    super.onCreate();
    Stetho.initializeWithDefaults(this);
    MockDependencyInjection.setContext(this);
    }
}
