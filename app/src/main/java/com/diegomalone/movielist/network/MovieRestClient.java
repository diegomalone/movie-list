package com.diegomalone.movielist.network;

import com.diegomalone.movielist.model.Movie;
import io.reactivex.Observable;

import java.util.List;

public interface MovieRestClient {

    Observable<List<Movie>> getMovieList();
}
