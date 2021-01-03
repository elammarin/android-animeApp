package com.example.animeapplication.presentation.animedisplay.favorite.mapper;

import android.text.Html;

import com.example.animeapplication.data.entity.AnimeEntity;
import com.example.animeapplication.presentation.animedisplay.search.adapter.AnimeItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class AnimeEntityToDetailViewModelMapper {

    private AnimeItemViewModel map(AnimeEntity animeEntity) {
        AnimeItemViewModel animeItemViewModel = new AnimeItemViewModel();
        animeItemViewModel.setTitle(animeEntity.getTitle());
        animeItemViewModel.setAnimeId(animeEntity.getId());
        animeItemViewModel.setIconUrl(animeEntity.getImage_url());
        if (animeEntity.getSynopsis() != null) {
            animeItemViewModel.setSynopsis(Html.fromHtml(animeEntity.getSynopsis()).toString());
        }
       // animeItemViewModel.setLaunchingDate(animeEntity.getEnding_date());
       // animeItemViewModel.setEndingDate(animeEntity.getEnding_date());

        return animeItemViewModel;
    }

    public List<AnimeItemViewModel> map(List<AnimeEntity> animeList) {
        List<AnimeItemViewModel> animeItemViewModelList = new ArrayList<>();
        for (AnimeEntity anime : animeList) {
            animeItemViewModelList.add(map(anime));
        }
        return animeItemViewModelList;
    }

}
