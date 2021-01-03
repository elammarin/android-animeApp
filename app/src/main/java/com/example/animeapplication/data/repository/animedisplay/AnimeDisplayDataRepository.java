package com.example.animeapplication.data.repository.animedisplay;

import com.example.animeapplication.data.api.model.Anime;
import com.example.animeapplication.data.api.model.AnimeSearchResponse;
import com.example.animeapplication.data.entity.AnimeEntity;
import com.example.animeapplication.data.repository.animedisplay.local.AnimeDisplayLocalDataSource;
import com.example.animeapplication.data.repository.animedisplay.mapper.AnimeToAnimeEntityMapper;
import com.example.animeapplication.data.repository.animedisplay.remote.AnimeDisplayRemoteDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

public class AnimeDisplayDataRepository implements AnimeDisplayRepository {

    private AnimeDisplayRemoteDataSource animeDisplayRemoteDataSource;
    private  AnimeToAnimeEntityMapper animeToAnimeEntityMapper;
    private AnimeDisplayLocalDataSource animeDisplayLocalDataSource;

    public AnimeDisplayDataRepository(AnimeDisplayLocalDataSource animeDisplayLocalDataSource,
                                      AnimeDisplayRemoteDataSource animeDisplayRemoteDataSource,
                                      AnimeToAnimeEntityMapper animeToAnimeEntityMapper) {
        this.animeDisplayRemoteDataSource = animeDisplayRemoteDataSource;
        this.animeDisplayLocalDataSource = animeDisplayLocalDataSource;
        this.animeToAnimeEntityMapper = animeToAnimeEntityMapper;
    }

    @Override
    public Single<AnimeSearchResponse> getAnimeSearchResponse(String keywords) {
        return animeDisplayRemoteDataSource.getAnimeSearchResponse(keywords)
                .zipWith(animeDisplayLocalDataSource.getFavoriteIdList(), new BiFunction<AnimeSearchResponse, List<String>, AnimeSearchResponse>() {
                    @Override
                    public AnimeSearchResponse apply(AnimeSearchResponse animeSearchResponse, List<String> idList) throws Exception {
                        for (Anime anime : animeSearchResponse.getAnimeList()) {
                            if (idList.contains(anime.getId())) {
                                anime.setFavorite();
                            }
                        }
                        return animeSearchResponse;
                    }
                });
    }

    @Override
    public Flowable<List<AnimeEntity>> getFavoriteAnimes() {
        return animeDisplayLocalDataSource.loadFavorites();
    }

    @Override
    public Completable addAnimeToFavorites(String animeId) {
        return animeDisplayRemoteDataSource.getAnime(animeId)
                .map(new Function<Anime, AnimeEntity>() {
                    @Override
                    public AnimeEntity apply(Anime anime) throws Exception {
                        return animeToAnimeEntityMapper.map(anime);
                    }
                })
                .flatMapCompletable(new Function<AnimeEntity, CompletableSource>() {
                    @Override
                    public CompletableSource apply(AnimeEntity animeEntity) throws Exception {
                        return animeDisplayLocalDataSource.addAnimeToFavorites(animeEntity);
                    }
                });
    }

    @Override
    public Completable removeAnimeFromFavorites(String animeId) {
        return animeDisplayLocalDataSource.deleteAnimeFromFavorites(animeId);
    }


}
