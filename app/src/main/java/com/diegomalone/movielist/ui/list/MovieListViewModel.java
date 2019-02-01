package com.diegomalone.movielist.ui.list;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.diegomalone.movielist.R;
import com.diegomalone.movielist.model.Movie;
import com.diegomalone.movielist.model.MovieResult;
import com.diegomalone.movielist.network.MovieRestClient;
import com.diegomalone.movielist.network.ServiceFactory;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.util.List;

public class MovieListViewModel extends AndroidViewModel {

    private MovieRestClient service = ServiceFactory.getService();
    private CompositeDisposable disposables = new CompositeDisposable();

    MutableLiveData<List<Movie>> movieListLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> errorLiveData = new MutableLiveData<>();

    public MovieListViewModel(@NonNull Application application) {
        super(application);
    }

    void getMovies() {
        String apiKey = getApplication().getString(R.string.tmdb_api_key);

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
                                errorLiveData.postValue(true);
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
