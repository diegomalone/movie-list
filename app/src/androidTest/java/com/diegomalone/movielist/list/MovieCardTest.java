package com.diegomalone.movielist.list;

import androidx.test.rule.ActivityTestRule;
import com.diegomalone.movielist.R;
import com.diegomalone.movielist.TestActivity;
import com.diegomalone.movielist.model.Movie;
import com.diegomalone.movielist.ui.list.MovieCard;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class MovieCardTest {

    private final String TITLE = "Movie Title";
    private final String SYNOPSIS = "This is an amazing movie. You should watch it!";
    private final double USER_RATING = 4.5;
    private final String FORMATTED_USER_RATING = "4.5";

    private Movie movie;

    @Rule
    public ActivityTestRule activityRule = new ActivityTestRule<>(TestActivity.class, true, true);

    @Before
    public void setup() {
        movie = new Movie();

        movie.setTitle(TITLE);
        movie.setSynopsis(SYNOPSIS);
        movie.setUserRating(USER_RATING);
    }

    @Test
    public void shouldShowMovieTitle_WhenMovieSet() {
        showMovie(movie);

        onView(withId(R.id.textTitle))
                .check(matches(withText(TITLE)));

        onView(withId(R.id.textSynopsis))
                .check(matches(withText(SYNOPSIS)));

        onView(withId(R.id.textUserRating))
                .check(matches(withText(FORMATTED_USER_RATING)));
    }

    private void showMovie(final Movie movie) {
        try {
            activityRule.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    MovieCard movieCard = new MovieCard(activityRule.getActivity());
                    activityRule.getActivity().setContentView(movieCard);
                    movieCard.setMovie(movie);
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
