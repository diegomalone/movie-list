package com.diegomalone.movielist.ui.list;

import android.os.Bundle;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.diegomalone.movielist.R;
import com.diegomalone.movielist.model.Movie;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MovieListActivity extends AppCompatActivity {

    private MovieListViewModel viewModel;

    private ViewGroup rootContainer;
    private RecyclerView movieListRecyclerView;

    private MovieAdapter movieAdapter = new MovieAdapter();

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
        movieListRecyclerView = findViewById(R.id.recyclerViewMovieList);

        movieListRecyclerView.setAdapter(movieAdapter);
        movieListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initObservers() {
        viewModel.movieListLiveData.observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                loadMovies(movies);
            }
        });

        viewModel.errorLiveData.observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(Throwable throwable) {
                showError();
            }
        });
    }

    private void loadMovies(List<Movie> movies) {
        movieAdapter.addMovies(movies);
    }

    private void showError() {
        Snackbar.make(rootContainer, R.string.error_default, Snackbar.LENGTH_LONG).show();
    }
}
