package com.diegomalone.movielist.ui.list;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import com.diegomalone.movielist.model.Movie;
import com.diegomalone.movielist.network.MovieRestClient;
import io.reactivex.disposables.CompositeDisposable;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MovieListViewModel extends ViewModel {

    LiveData<Integer> activeChildLiveData;
    LiveData<PagedList<Movie>> movieListLiveData;

    private CompositeDisposable disposables = new CompositeDisposable();

    @SuppressWarnings("unchecked")
    public MovieListViewModel(MovieRestClient service) {
        Executor executor = Executors.newFixedThreadPool(5);

        MovieDataSourceFactory factory = new MovieDataSourceFactory(service, disposables);

        activeChildLiveData = Transformations.switchMap(factory.getMutableLiveData(), new Function<MovieDataSource, LiveData<Integer>>() {
            @Override
            public LiveData<Integer> apply(MovieDataSource input) {
                return input.activeChildLiveData;
            }
        });

        PagedList.Config pagedListConfig = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(1)
                .setPageSize(20)
                .build();

        movieListLiveData = new LivePagedListBuilder(factory, pagedListConfig)
                .setFetchExecutor(executor)
                .build();
    }

    @Override
    protected void onCleared() {
        disposables.clear();
        super.onCleared();
    }
}
