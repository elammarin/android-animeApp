package com.example.animeapplication.presentation.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.animeapplication.data.api.model.AnimeSearchResponse;
import com.example.animeapplication.data.repository.animedisplay.AnimeDisplayRepository;
import com.example.animeapplication.presentation.animedisplay.search.adapter.AnimeItemViewModel;
import com.example.animeapplication.presentation.animedisplay.search.mapper.AnimeToViewModelMapper;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class AnimeSearchViewModel extends ViewModel{
    private AnimeDisplayRepository animeDisplayRepository;
    private CompositeDisposable compositeDisposable;
    private AnimeToViewModelMapper animeToViewModelMapper;

    public AnimeSearchViewModel(AnimeDisplayRepository animeDisplayRepository) {
        this.animeDisplayRepository = animeDisplayRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.animeToViewModelMapper = new AnimeToViewModelMapper();
    }

    private MutableLiveData<List<AnimeItemViewModel>> animes = new MutableLiveData<List<AnimeItemViewModel>>();
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<Boolean>();

    public MutableLiveData<List<AnimeItemViewModel>> getAnimes() {
        return animes;
    }

    public MutableLiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }

    public void searchAnimes(String keywords) {
        isDataLoading.postValue(true);
        compositeDisposable.clear();
        compositeDisposable.add(animeDisplayRepository.getAnimeSearchResponse(keywords)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<AnimeSearchResponse>() {

                    @Override
                    public void onSuccess(AnimeSearchResponse animeSearchResponse) {
                        Log.v("test", "taille"+animeSearchResponse.getAnimeList().get(0).getDescription());
                        animes.setValue(animeToViewModelMapper.map(animeSearchResponse.getAnimeList()));
                        isDataLoading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle the error case
                        //Yet, do not do nothing in this app
                        System.out.println(e.toString());
                        isDataLoading.setValue(false);
                    }
                }));
    }

    public void cancelSubscription() {
        compositeDisposable.clear();
        isDataLoading.setValue(false);
    }
}
