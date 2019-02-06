package com.diegomalone.movielist.network;

import com.diegomalone.movielist.model.MovieResult;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieRestClient {

    @GET("3/movie/popular")
    Observable<MovieResult> getMovieList(@Query("api_key") String apiKey,
                                         @Query("page") Integer page);
}
