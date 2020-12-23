package com.example.animeapplication.data.di;

import android.content.Context;

import com.example.animeapplication.data.api.AnimeDisplayService;
import com.example.animeapplication.data.repository.animedisplay.AnimeDisplayDataRepository;
import com.example.animeapplication.data.repository.animedisplay.AnimeDisplayRepository;
import com.example.animeapplication.data.repository.animedisplay.remote.AnimeDisplayRemoteDataSource;
import com.example.animeapplication.presentation.viewmodel.ViewModelFactory;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MockDependencyInjection {
    private static AnimeDisplayService animeDisplayService;
    private static Retrofit retrofit;
    private static Gson gson;
    private static AnimeDisplayRepository animeDisplayRepository;
    private static Context applicationContext;
    private static ViewModelFactory viewModelFactory;

    public static ViewModelFactory getViewModelFactory() {
        if (viewModelFactory == null) {
            viewModelFactory = new ViewModelFactory(getAnimeDisplayRepository());
        }
        return viewModelFactory;
    }


    public static AnimeDisplayRepository getAnimeDisplayRepository() {
        if (animeDisplayRepository == null) {
            animeDisplayRepository = new AnimeDisplayDataRepository(
                    new AnimeDisplayRemoteDataSource(getAnimeDisplayService()));
        }
        return animeDisplayRepository;
    }

    public static AnimeDisplayService getAnimeDisplayService() {
        if (animeDisplayService == null) {
            animeDisplayService = getRetrofit().create(AnimeDisplayService.class);
        }
        return animeDisplayService;
    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.jikan.moe/v3/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }
        return retrofit;
    }

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    public static void setContext(Context context) {
        applicationContext = context;
    }
}
