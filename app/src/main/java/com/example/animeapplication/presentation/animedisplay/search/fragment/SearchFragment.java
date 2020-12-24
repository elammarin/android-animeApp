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
import com.example.animeapplication.presentation.viewmodel.AnimeSearchViewModel;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SearchFragment extends Fragment implements AnimeActionInterface, AnimeAdapter.SelectedAnime {
    public static final String TAB_NAME = "Search";
    private View rootView;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private AnimeAdapter animeAdapter;
    private ProgressBar progressBar;
    private AnimeSearchViewModel animeSearchViewModel;
    public int position = 0;

    private SearchFragment() {
    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_search, container, false);
        return rootView;
    }

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

    private void registerViewModels() {
        animeSearchViewModel = new ViewModelProvider(requireActivity(), MockDependencyInjection.getViewModelFactory()).get(AnimeSearchViewModel.class);


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

    private void setupSearchView() {
        searchView = rootView.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            private Timer timer = new Timer();

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

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

    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        animeAdapter = new AnimeAdapter(this, this);
        recyclerView.setAdapter(animeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void selectedAnime(AnimeItemViewModel animeItemViewModel) {
        startActivity(new Intent(getActivity(), SelectedAnimeActivity.class).putExtra("data", animeItemViewModel));
    }

    @Override
    public void onFavoriteToggle(String bookId, boolean isFavorite) {
        System.out.println("do nothing for now!");
    }
}
