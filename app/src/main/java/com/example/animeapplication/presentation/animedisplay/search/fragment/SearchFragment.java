package com.example.animeapplication.presentation.animedisplay.search.fragment;

import com.example.animeapplication.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animeapplication.data.di.MockDependencyInjection;
import com.example.animeapplication.presentation.animedisplay.search.SelectedAnimeActivity;
import com.example.animeapplication.presentation.animedisplay.search.adapter.AnimeActionInterface;
import com.example.animeapplication.presentation.animedisplay.search.adapter.AnimeAdapter;
import com.example.animeapplication.presentation.animedisplay.search.adapter.AnimeItemViewModel;
import com.example.animeapplication.presentation.viewmodel.AnimeFavoriteViewModel;
import com.example.animeapplication.presentation.viewmodel.AnimeSearchViewModel;


import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * le fragment dédié à la recherche
 */
public class SearchFragment extends Fragment implements AnimeActionInterface, AnimeAdapter.SelectedAnime {
    public static final String TAB_NAME = "Search";
    private View rootView;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private AnimeAdapter animeAdapter;
    private ProgressBar progressBar;
    private AnimeSearchViewModel animeSearchViewModel;
    private AnimeFavoriteViewModel animeFavoriteViewModel;
    public int position = 0;

    /**
     * Constructeur de SearchFragment
     */
    private SearchFragment() {
    }

    /**
     * créer une nouvelle instance de SearchFragment
     * @return un SearchFragment
     */
    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    /**
     * Création de la vue
     * @param inflater un inflater
     * @param container un container
     * @param savedInstanceState savedInstanceState
     * @return une vue
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_search, container, false);
        return rootView;
    }

    /**
     * Actions lorsque l'activité est créée
     * @param savedInstanceState savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupSearchView();
        setupRecyclerView();
        progressBar = rootView.findViewById(R.id.progress_bar);
        registerViewModels();
        Button b = rootView.findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position ==0){
                    recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
                    position=1;
                }
                else{
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    position=0;
                }}
        });
    }

    /**
     * inscription des viewModels
     */
    private void registerViewModels() {
        animeSearchViewModel = new ViewModelProvider(requireActivity(), MockDependencyInjection.getViewModelFactory()).get(AnimeSearchViewModel.class);
        animeFavoriteViewModel = new ViewModelProvider(requireActivity(), MockDependencyInjection.getViewModelFactory()).get(AnimeFavoriteViewModel.class);


        animeSearchViewModel.getAnimes().observe(getViewLifecycleOwner(), new Observer<List<AnimeItemViewModel>>() {
            @Override
            public void onChanged(List<AnimeItemViewModel> animeItemViewModelList) {
                animeAdapter.bindViewModels(animeItemViewModelList);
            }
        });

        animeSearchViewModel.getIsDataLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isDataLoading) {
                progressBar.setVisibility(isDataLoading ? View.VISIBLE : View.GONE);
            }
        });
    }

    /**
     * mise en place de la SearchView
     */
    private void setupSearchView() {
        searchView = rootView.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            private Timer timer = new Timer();

            /**
             * action lors de la soumission du texte
             * @param query le texte rentré par l'utilisateur dans le champs qui sert à rechercher un animé
             * @return un booléen
             */
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            /**
             * action lorsque le texte du champs dédié à la recherche change
             * @param s le texte dans le champs dédié à la recherche
             * @return un booléen
             */
            @Override
            public boolean onQueryTextChange(final String s) {
                if (s.length() == 0) {
                    animeSearchViewModel.cancelSubscription();
                } else {
                    timer.cancel();
                    timer = new Timer();
                    int sleep = 350;
                    if (s.length() == 1)
                        sleep = 5000;
                    else if (s.length() <= 3)
                        sleep = 300;
                    else if (s.length() <= 5)
                        sleep = 200;
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            animeSearchViewModel.searchAnimes(s);
                        }
                    }, sleep);
                }
                return true;
            }
        });
    }

    /**
     * mise en place du recyclerview
     */
    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        animeAdapter = new AnimeAdapter(this, this);
        recyclerView.setAdapter(animeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    /**
     * une nouvelle activité est lancé pour affiché les informations détaillé d'un animé choisi par l'utilisateur
     * @param animeItemViewModel ViewModel d'un animé
     */
    @Override
    public void selectedAnime(AnimeItemViewModel animeItemViewModel) {
        startActivity(new Intent(getActivity(), SelectedAnimeActivity.class).putExtra("data", animeItemViewModel));
    }

    /**
     * Ajout ou suppression d'un animé des favoris selon la position du switch avant l'appui sur celui-ci de l"utilisateur
     * @param animeId
     * @param isFavorite
     */
    @Override
    public void onFavoriteToggle(String animeId, boolean isFavorite) {
        if (isFavorite) {
            animeFavoriteViewModel.addAnimeToFavorite(animeId);
        } else {
            animeFavoriteViewModel.removeAnimeFromFavorites(animeId);
        }
    }
}
