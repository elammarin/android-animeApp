package com.example.animeapplication.presentation.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.animeapplication.data.entity.AnimeEntity;
import com.example.animeapplication.data.repository.animedisplay.AnimeDisplayRepository;
import com.example.animeapplication.presentation.animedisplay.favorite.mapper.AnimeEntityToDetailViewModelMapper;
import com.example.animeapplication.presentation.animedisplay.search.adapter.AnimeItemViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class AnimeFavoriteViewModel extends ViewModel {

    private AnimeDisplayRepository animeDisplayRepository;
    private CompositeDisposable compositeDisposable;
    private AnimeEntityToDetailViewModelMapper animeEntityToDetailViewModelMapper;

    public AnimeFavoriteViewModel(AnimeDisplayRepository animeDisplayRepository) {
        this.animeDisplayRepository = animeDisplayRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.animeEntityToDetailViewModelMapper = new AnimeEntityToDetailViewModelMapper();
    }

    private MutableLiveData<List<AnimeItemViewModel>> favorites;
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<Boolean>();
    final MutableLiveData<Event<String>> animeAddedEvent = new MutableLiveData<Event<String>>();
    final MutableLiveData<Event<String>> animeDeletedEvent = new MutableLiveData<Event<String>>();

    public MutableLiveData<Event<String>> getAnimeAddedEvent() {
        return animeAddedEvent;
    }

    public MutableLiveData<Event<String>> getAnimeDeletedEvent() {
        return animeDeletedEvent;
    }

    public MutableLiveData<List<AnimeItemViewModel>> getFavorites() {
        isDataLoading.setValue(true);
        if (favorites == null) {
            favorites = new MutableLiveData<List<AnimeItemViewModel>>();
            compositeDisposable.add(animeDisplayRepository.getFavoriteAnimes()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new ResourceSubscriber<List<AnimeEntity>>() {

                        @Override
                        public void onNext(List<AnimeEntity> animeEntityList) {
                            isDataLoading.setValue(false);
                            favorites.setValue(animeEntityToDetailViewModelMapper.map(animeEntityList));
                            System.out.println("BIND FAVORITES");
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            isDataLoading.setValue(false);
                        }

                        @Override
                        public void onComplete() {
                            isDataLoading.setValue(false);
                        }
                    }));

        }
        return favorites;
    }

    public MutableLiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }

    public void addAnimeToFavorite(final String animeId) {
        compositeDisposable.add(animeDisplayRepository.addAnimeToFavorites(animeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        animeAddedEvent.setValue(new Event<String>(animeId));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("error", "marche pas");
                        e.printStackTrace();
                    }
                }));
    }

    public void removeAnimeFromFavorites(final String animeId) {
        compositeDisposable.add(animeDisplayRepository.removeAnimeFromFavorites(animeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        animeDeletedEvent.setValue(new Event<>(animeId));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }
}
