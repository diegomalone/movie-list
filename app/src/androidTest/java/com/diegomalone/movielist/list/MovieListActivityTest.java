package com.diegomalone.movielist.list;

import android.content.Intent;
import androidx.test.rule.ActivityTestRule;
import com.diegomalone.movielist.R;
import com.diegomalone.movielist.model.Movie;
import com.diegomalone.movielist.model.MovieResult;
import com.diegomalone.movielist.network.NetworkServer;
import com.diegomalone.movielist.ui.list.MovieListActivity;
import com.google.gson.Gson;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.diegomalone.movielist.matcher.RecyclerViewMatcher.hasItemCount;

public class MovieListActivityTest {

    private final String TITLE = "Movie Title";

    @Rule
    public ActivityTestRule activityRule = new ActivityTestRule<>(MovieListActivity.class, false, false);

    private NetworkServer networkServer = NetworkServer.getInstance();

    @Test
    public void shouldShowMovieList_WhenGetMovieListFromApi() {
        int totalMovies = 10;

        networkServer.enqueue(new MockResponse()
                .setBody(new Gson().toJson(getMovieResult(totalMovies)))
        );

        activityRule.launchActivity(new Intent());

        onView(withId(R.id.recyclerViewMovieList))
                .check(matches(hasItemCount(totalMovies)));
    }

    @Test
    public void shouldShowLoading_WhenWaitMovieResponse() {
        MockResponse response = new MockResponse()
                .setBody(new Gson().toJson(getMovieResult(10)));
        response.setBodyDelay(5, TimeUnit.SECONDS);

        networkServer.enqueue(response);

        activityRule.launchActivity(new Intent());

        onView(withId(R.id.progressMovieList))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldShowError_WhenGetMovieError() {
        networkServer.enqueue(new MockResponse()
        .setHttp2ErrorCode(500));

        activityRule.launchActivity(new Intent());

        onView(withText(R.string.error_default))
                .check(matches(isDisplayed()));
    }

    private MovieResult getMovieResult(int totalMovies) {
        List<Movie> movieList = new ArrayList<>();

        for (int i = 0; i < totalMovies; i++) {
            Movie movie = new Movie();
            movie.setId(i);
            movie.setTitle(TITLE);

            movieList.add(movie);
        }

        MovieResult movieResult = new MovieResult();
        movieResult.setResults(movieList);

        return movieResult;
    }
}
