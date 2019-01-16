package com.diegomalone.movielist.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.diegomalone.movielist.R;
import com.diegomalone.movielist.model.Movie;
import com.diegomalone.movielist.network.MovieRestClient;
import com.diegomalone.movielist.network.ServiceFactory;
import com.google.android.material.snackbar.Snackbar;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.util.List;

public class MovieListActivity extends AppCompatActivity {

    private MovieRestClient service = ServiceFactory.getService();

    private ConstraintLayout rootContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMovies();
    }

    private void initViews() {
        rootContainer = findViewById(R.id.constraintLayoutRoot);
    }

    private void getMovies() {
        String apiKey = getString(R.string.tmdb_api_key);

        service.getMovieList(apiKey, "1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) {
                        loadMovies(movies);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        showError();
                    }
                });
    }

    private void loadMovies(List<Movie> movies) {
        // TODO Show movies
    }

    private void showError() {
        Snackbar.make(rootContainer, R.string.error_default, Snackbar.LENGTH_LONG).show();
    }
}
