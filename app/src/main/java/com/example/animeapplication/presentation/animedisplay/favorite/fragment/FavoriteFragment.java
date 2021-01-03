package com.example.animeapplication.presentation.animedisplay.favorite.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animeapplication.R;
import com.example.animeapplication.data.di.MockDependencyInjection;
import com.example.animeapplication.presentation.animedisplay.favorite.adapter.AnimeDetailActionInterface;
import com.example.animeapplication.presentation.animedisplay.favorite.adapter.AnimeDetailAdapter;
import com.example.animeapplication.presentation.animedisplay.search.adapter.AnimeItemViewModel;
import com.example.animeapplication.presentation.viewmodel.AnimeFavoriteViewModel;
import com.example.animeapplication.presentation.viewmodel.Event;

import java.util.List;

public class FavoriteFragment extends Fragment implements AnimeDetailActionInterface {

    public static final String TAB_NAME = "Favorites";
    private View rootView;
    private RecyclerView recyclerView;
    private AnimeDetailAdapter animeAdapter;
    private AnimeFavoriteViewModel animeFavoriteViewModel;

    private FavoriteFragment() {
    }

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_favorites, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();
        registerViewModels();
    }

    private void registerViewModels() {
        animeFavoriteViewModel = new ViewModelProvider(requireActivity(), MockDependencyInjection.getViewModelFactory()).get(AnimeFavoriteViewModel.class);
        System.out.println("FVVM is " + animeFavoriteViewModel);

        animeFavoriteViewModel.getFavorites().observe(getViewLifecycleOwner(), new Observer<List<AnimeItemViewModel>>() {
            @Override
            public void onChanged(List<AnimeItemViewModel> animeDetailViewModelList) {
                animeAdapter.bindViewModels(animeDetailViewModelList);
            }
        });

        animeFavoriteViewModel.getAnimeAddedEvent().observe(getViewLifecycleOwner(), new Observer<Event<String>>() {
            @Override
            public void onChanged(Event<String> stringEvent) {
                //Do nothing
            }
        });

        animeFavoriteViewModel.getAnimeDeletedEvent().observe(getViewLifecycleOwner(), new Observer<Event<String>>() {
            @Override
            public void onChanged(Event<String> stringEvent) {
                //Do nothing
            }
        });
    }

    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        animeAdapter = new AnimeDetailAdapter(this);
        recyclerView.setAdapter(animeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onRemoveFavorite(String animeId) {
        animeFavoriteViewModel.removeAnimeFromFavorites(animeId);
        System.out.println("Remove anime " + animeId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //animeFavoritePresenter.detachView();
    }
}
