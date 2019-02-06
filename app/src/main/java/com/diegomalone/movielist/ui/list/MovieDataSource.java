package com.diegomalone.movielist.ui.list;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;
import com.diegomalone.movielist.BuildConfig;
import com.diegomalone.movielist.model.Movie;
import com.diegomalone.movielist.model.MovieResult;
import com.diegomalone.movielist.network.MovieRestClient;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.diegomalone.movielist.ui.list.MovieListActivity.*;

public class MovieDataSource extends PageKeyedDataSource<Integer, Movie> {

    private MovieRestClient service;

    MutableLiveData<Integer> activeChildLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> listLoading = new MutableLiveData<>();

    private CompositeDisposable disposables;

    public MovieDataSource(MovieRestClient service, CompositeDisposable disposables) {
        this.service = service;
        this.disposables = disposables;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, final @NonNull LoadInitialCallback<Integer, Movie> callback) {
        activeChildLiveData.postValue(LOADING);

        disposables.add(
                service.getMovieList(BuildConfig.API_KEY, 1)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Consumer<MovieResult>() {
                            @Override
                            public void accept(MovieResult movieResult) {
                                callback.onResult(movieResult.getResults(), null, 2);
                                activeChildLiveData.postValue(CONTENT);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                activeChildLiveData.postValue(ERROR);
                            }
                        })
        );
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {
        // Do Nothing
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, final @NonNull LoadCallback<Integer, Movie> callback) {
        listLoading.postValue(true);

        disposables.add(
                service.getMovieList(BuildConfig.API_KEY, params.key)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Consumer<MovieResult>() {
                            @Override
                            public void accept(MovieResult movieResult) {
                                Integer nextPage = movieResult.getPage() == movieResult.getTotalPages() ?
                                        null : movieResult.getPage() + 1;

                                callback.onResult(movieResult.getResults(), nextPage);
                                listLoading.postValue(false);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                listLoading.postValue(false);
                            }
                        })
        );
    }
}
