package com.example.animeapplication.presentation.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.animeapplication.data.repository.animedisplay.AnimeDisplayRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final AnimeDisplayRepository animeDisplayRepository;

    public ViewModelFactory(AnimeDisplayRepository animeDisplayRepository) {
        this.animeDisplayRepository = animeDisplayRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AnimeSearchViewModel.class)) {
            return (T) new AnimeSearchViewModel(animeDisplayRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
