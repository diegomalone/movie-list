package com.diegomalone.movielist.ui.list;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import com.diegomalone.movielist.model.Movie;
import com.diegomalone.movielist.network.MovieRestClient;
import io.reactivex.disposables.CompositeDisposable;

public class MovieDataSourceFactory extends DataSource.Factory<Integer, Movie> {

    private MutableLiveData<MovieDataSource> mutableLiveData = new MutableLiveData<>();
    private MovieDataSource movieDataSource;

    private MovieRestClient service;
    private CompositeDisposable disposables;

    public MovieDataSourceFactory(MovieRestClient service, CompositeDisposable disposables) {
        this.service = service;
        this.disposables = disposables;
    }

    @Override
    public DataSource<Integer, Movie> create() {
        movieDataSource = new MovieDataSource(service, disposables);
        mutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    public MutableLiveData<MovieDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
