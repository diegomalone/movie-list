package com.diegomalone.movielist.ui.list;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.diegomalone.movielist.BuildConfig;
import com.diegomalone.movielist.model.Movie;
import com.diegomalone.movielist.model.MovieResult;
import com.diegomalone.movielist.network.MovieRestClient;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.util.List;

public class MovieListViewModel extends ViewModel {

    private MovieRestClient service;

    private CompositeDisposable disposables = new CompositeDisposable();

    MutableLiveData<List<Movie>> movieListLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();

    public MovieListViewModel(MovieRestClient service) {
        this.service = service;
    }

    void getMovies() {
        String apiKey = BuildConfig.API_KEY;

        disposables.add(
                service.getMovieList(apiKey, "1")
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Consumer<MovieResult>() {
                            @Override
                            public void accept(MovieResult movieResult) {
                                movieListLiveData.postValue(movieResult.getResults());
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                errorLiveData.postValue(throwable);
                            }
                        })
        );
    }

    @Override
    protected void onCleared() {
        disposables.clear();
        super.onCleared();
    }
}
