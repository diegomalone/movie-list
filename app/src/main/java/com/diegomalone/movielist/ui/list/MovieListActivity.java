package com.diegomalone.movielist.ui.list;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.diegomalone.movielist.R;
import com.diegomalone.movielist.model.Movie;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MovieListActivity extends AppCompatActivity {

    private MovieListViewModel viewModel;

    private ConstraintLayout rootContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        viewModel = ViewModelProviders.of(this, new MovieListViewModelFactory())
                .get(MovieListViewModel.class);

        initViews();
        initObservers();
    }

    @Override
    protected void onResume() {
        super.onResume();

        viewModel.getMovies();
    }

    private void initViews() {
        rootContainer = findViewById(R.id.constraintLayoutRoot);
    }

    private void initObservers() {
        viewModel.movieListLiveData.observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                loadMovies(movies);
            }
        });

        viewModel.errorLiveData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                showError();
            }
        });
    }

    private void loadMovies(List<Movie> movies) {
        // TODO Show movies
        Snackbar.make(rootContainer, "Loaded " + movies.size() + " movies", Snackbar.LENGTH_LONG).show();
    }

    private void showError() {
        Snackbar.make(rootContainer, R.string.error_default, Snackbar.LENGTH_LONG).show();
    }
}
