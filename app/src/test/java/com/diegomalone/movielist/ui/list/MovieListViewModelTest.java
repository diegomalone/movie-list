package com.diegomalone.movielist.ui.list;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import com.diegomalone.movielist.RxImmediateSchedulerRule;
import com.diegomalone.movielist.model.Movie;
import com.diegomalone.movielist.model.MovieResult;
import com.diegomalone.movielist.network.MovieRestClient;
import io.reactivex.Observable;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static com.diegomalone.movielist.ui.list.MovieListActivity.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieListViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Rule
    public RxImmediateSchedulerRule rxRule = new RxImmediateSchedulerRule();

    @Mock
    private Observer<Integer> activeChildObserver;

    @Mock
    private MovieRestClient service;

    private MovieListViewModel viewModel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        viewModel = new MovieListViewModel(service);
    }

    @Test
    public void shouldPostMovies_WhenMoviesReceived() {
        when(service.getMovieList(anyString(), anyString()))
                .thenReturn(Observable.just(getMovieResult()));

        viewModel.getMovies();

        assertEquals(getMovieResult().getResults(), viewModel.movieListLiveData.getValue());
    }

    @Test
    public void shouldPostError_WhenApiError() {
        Throwable throwable = new Throwable();

        when(service.getMovieList(anyString(), anyString()))
                .thenReturn(Observable.<MovieResult>error(throwable));

        viewModel.getMovies();

        assertEquals(throwable, viewModel.errorLiveData.getValue());
    }

    @Test
    public void shouldPostLoadingState_WhenGetMovies() {
        when(service.getMovieList(anyString(), anyString()))
                .thenReturn(Observable.just(getMovieResult()));

        viewModel.activeChildLiveData.observeForever(activeChildObserver);

        viewModel.getMovies();

        verify(activeChildObserver).onChanged(LOADING);
    }

    @Test
    public void shouldPostContentState_WhenGetMovies() {
        when(service.getMovieList(anyString(), anyString()))
                .thenReturn(Observable.just(getMovieResult()));

        viewModel.activeChildLiveData.observeForever(activeChildObserver);

        viewModel.getMovies();

        verify(activeChildObserver).onChanged(CONTENT);
    }

    @Test
    public void shouldPostErrorState_WhenApiError() {
        Throwable throwable = new Throwable();

        when(service.getMovieList(anyString(), anyString()))
                .thenReturn(Observable.<MovieResult>error(throwable));

        viewModel.activeChildLiveData.observeForever(activeChildObserver);

        viewModel.getMovies();

        verify(activeChildObserver).onChanged(ERROR);
    }

    private MovieResult getMovieResult() {
        Movie movie = new Movie();
        movie.setId(1);

        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);

        MovieResult movieResult = new MovieResult();
        movieResult.setResults(movieList);

        return movieResult;
    }
}
