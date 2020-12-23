package com.example.animeapplication.presentation.animedisplay.search.mapper;

import android.util.Log;

import com.example.animeapplication.data.api.model.Anime;
import com.example.animeapplication.presentation.animedisplay.search.adapter.AnimeItemViewModel;
//import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class AnimeToViewModelMapper {
    private AnimeItemViewModel map(Anime anime) {
        AnimeItemViewModel animeItemViewModel = new AnimeItemViewModel();
        animeItemViewModel.setTitle(anime.getTitle());
        animeItemViewModel.setAnimeId(anime.getId());
        if (anime.getImage_url() != null) {
            animeItemViewModel.setIconUrl(anime.getImage_url());
        }
        animeItemViewModel.setFavorite(anime.isFavorite());
        animeItemViewModel.setNbEpisodes(anime.nbEpisodes());

        return animeItemViewModel;
    }

    public List<AnimeItemViewModel> map(List<Anime> animeList) {
        List<AnimeItemViewModel> animeItemViewModelList = new ArrayList<>();
        Log.i("truc", "taille : "+animeList.size());
        for (Anime anime : animeList) {
            animeItemViewModelList.add(map(anime));
        }
        return animeItemViewModelList;
    }
}
