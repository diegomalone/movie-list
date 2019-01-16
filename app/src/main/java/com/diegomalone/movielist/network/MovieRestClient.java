package com.diegomalone.movielist.network;

import com.diegomalone.movielist.model.Movie;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface MovieRestClient {

    @GET("3/movie/popular")
    Observable<List<Movie>> getMovieList(@Query("api_key") String apiKey,
                                         @Query("page") String page);
}
