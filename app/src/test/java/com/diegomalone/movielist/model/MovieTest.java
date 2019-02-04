package com.diegomalone.movielist.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MovieTest {

    @Test
    public void testFormattedUserRatingZero() {
        Movie movie = new Movie();
        movie.setUserRating(0.0);

        assertEquals("0.0", movie.getFormattedUserRating());
    }

    @Test
    public void testFormattedUserRatingNonZero() {
        Movie movie = new Movie();
        movie.setUserRating(7.5);

        assertEquals("7.5", movie.getFormattedUserRating());
    }

    @Test
    public void testFormattedUserRatingNotRoundingUp() {
        Movie movie = new Movie();
        movie.setUserRating(7.5817);

        assertEquals("7.5", movie.getFormattedUserRating());
    }

    @Test
    public void testFormattedUserRatingTen() {
        Movie movie = new Movie();
        movie.setUserRating(10.0);

        assertEquals("10.0", movie.getFormattedUserRating());
    }
}
