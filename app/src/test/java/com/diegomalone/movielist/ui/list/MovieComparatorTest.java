package com.diegomalone.movielist.ui.list;

import com.diegomalone.movielist.model.Movie;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MovieComparatorTest {

    private MovieComparator movieComparator;

    @Before
    public void setup() {
        movieComparator = new MovieComparator();
    }
    
    @Test
    public void testItemsTheSameEqualIds() {
        Movie oldMovie = new Movie();
        oldMovie.setId(1);
        Movie newMovie = new Movie();
        newMovie.setId(1);

        assertTrue(movieComparator.areItemsTheSame(oldMovie, newMovie));
    }

    @Test
    public void testItemsTheSameDifferentIds() {
        Movie oldMovie = new Movie();
        oldMovie.setId(1);
        Movie newMovie = new Movie();
        newMovie.setId(2);

        assertFalse(movieComparator.areItemsTheSame(oldMovie, newMovie));
    }

    @Test
    public void testContentsTheSameBothTitleNull() {
        Movie oldMovie = new Movie();
        Movie newMovie = new Movie();

        assertTrue(movieComparator.areContentsTheSame(oldMovie, newMovie));
    }

    @Test
    public void testContentsTheSameOldTitleNotNullNewTitleNull() {
        Movie oldMovie = new Movie();
        oldMovie.setTitle("Title");
        Movie newMovie = new Movie();

        assertFalse(movieComparator.areContentsTheSame(oldMovie, newMovie));
    }

    @Test
    public void testContentsTheSameOldTitleNullNewTitleNotNull() {
        Movie oldMovie = new Movie();
        Movie newMovie = new Movie();
        newMovie.setTitle("Title");

        assertFalse(movieComparator.areContentsTheSame(oldMovie, newMovie));
    }

    @Test
    public void testContentsTheSameDifferentTitles() {
        Movie oldMovie = new Movie();
        oldMovie.setTitle("Title");
        Movie newMovie = new Movie();
        newMovie.setTitle("Another Title");

        assertFalse(movieComparator.areContentsTheSame(oldMovie, newMovie));
    }

    @Test
    public void testContentsTheSameEqualTitles() {
        Movie oldMovie = new Movie();
        oldMovie.setTitle("Title");
        Movie newMovie = new Movie();
        newMovie.setTitle("Title");

        assertTrue(movieComparator.areContentsTheSame(oldMovie, newMovie));
    }

    @Test
    public void testContentsTheSameBothSynopsisNull() {
        Movie oldMovie = new Movie();
        Movie newMovie = new Movie();

        assertTrue(movieComparator.areContentsTheSame(oldMovie, newMovie));
    }

    @Test
    public void testContentsTheSameOldSynopsisNotNullNewSynopsisNull() {
        Movie oldMovie = new Movie();
        oldMovie.setSynopsis("Synopsis");
        Movie newMovie = new Movie();

        assertFalse(movieComparator.areContentsTheSame(oldMovie, newMovie));
    }

    @Test
    public void testContentsTheSameOldSynopsisNullNewSynopsisNotNull() {
        Movie oldMovie = new Movie();
        Movie newMovie = new Movie();
        newMovie.setSynopsis("Synopsis");

        assertFalse(movieComparator.areContentsTheSame(oldMovie, newMovie));
    }

    @Test
    public void testContentsTheSameDifferentSynopsis() {
        Movie oldMovie = new Movie();
        oldMovie.setSynopsis("Synopsis");
        Movie newMovie = new Movie();
        newMovie.setSynopsis("Another Synopsis");

        assertFalse(movieComparator.areContentsTheSame(oldMovie, newMovie));
    }

    @Test
    public void testContentsTheSameEqualSynopsis() {
        Movie oldMovie = new Movie();
        oldMovie.setSynopsis("Synopsis");
        Movie newMovie = new Movie();
        newMovie.setSynopsis("Synopsis");

        assertTrue(movieComparator.areContentsTheSame(oldMovie, newMovie));
    }

    @Test
    public void testContentsTheSameBothPosterNull() {
        Movie oldMovie = new Movie();
        Movie newMovie = new Movie();

        assertTrue(movieComparator.areContentsTheSame(oldMovie, newMovie));
    }

    @Test
    public void testContentsTheSameOldPosterNotNullNewPosterNull() {
        Movie oldMovie = new Movie();
        oldMovie.setPoster("poster.jpg");
        Movie newMovie = new Movie();

        assertFalse(movieComparator.areContentsTheSame(oldMovie, newMovie));
    }

    @Test
    public void testContentsTheSameOldPosterNullNewPosterNotNull() {
        Movie oldMovie = new Movie();
        Movie newMovie = new Movie();
        newMovie.setPoster("poster.jpg");

        assertFalse(movieComparator.areContentsTheSame(oldMovie, newMovie));
    }

    @Test
    public void testContentsTheSameDifferentPosters() {
        Movie oldMovie = new Movie();
        oldMovie.setPoster("poster.jpg");
        Movie newMovie = new Movie();
        newMovie.setPoster("another.jpg");

        assertFalse(movieComparator.areContentsTheSame(oldMovie, newMovie));
    }

    @Test
    public void testContentsTheSameEqualPosters() {
        Movie oldMovie = new Movie();
        oldMovie.setPoster("poster.jpg");
        Movie newMovie = new Movie();
        newMovie.setPoster("poster.jpg");

        assertTrue(movieComparator.areContentsTheSame(oldMovie, newMovie));
    }

    @Test
    public void testContentsTheSameEqualUserRatings() {
        Movie oldMovie = new Movie();
        oldMovie.setUserRating(5.0);
        Movie newMovie = new Movie();
        newMovie.setUserRating(5.0);

        assertTrue(movieComparator.areContentsTheSame(oldMovie, newMovie));
    }

    @Test
    public void testContentsTheSameDifferentUserRatings() {
        Movie oldMovie = new Movie();
        oldMovie.setUserRating(5.0);
        Movie newMovie = new Movie();

        assertFalse(movieComparator.areContentsTheSame(oldMovie, newMovie));
    }
}
