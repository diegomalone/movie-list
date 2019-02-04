package com.diegomalone.movielist.ui.list;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.diegomalone.movielist.R;
import com.diegomalone.movielist.model.Movie;

public class MovieCard extends FrameLayout {

    private TextView movieTitle;

    private Movie movie;

    public MovieCard(Context context) {
        this(context, null);
    }

    public MovieCard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MovieCard(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public MovieCard(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        View view = inflate(context, R.layout.view_movie_card, this);

        movieTitle = view.findViewById(R.id.textMovieTitle);
    }

    public void setMovie(Movie movie) {
        this.movie = movie;

        movieTitle.setText(movie.getTitle());
    }

}
