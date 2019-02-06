package com.diegomalone.movielist.ui.list;

import android.os.Bundle;
import android.widget.ViewFlipper;
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

    public static final int LOADING = 0;
    public static final int CONTENT = 1;
    public static final int ERROR = 2;

    private MovieListViewModel viewModel;

    private ViewFlipper viewFlipper;
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
        viewFlipper = findViewById(R.id.viewFlipper);
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

        viewModel.activeChildLiveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer child) {
                viewFlipper.setDisplayedChild(child);
            }
        });
    }

    private void loadMovies(List<Movie> movies) {
        movieAdapter.addMovies(movies);
    }
}
