package com.diegomalone.movielist.ui.list;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.diegomalone.movielist.BuildConfig;
import com.diegomalone.movielist.R;
import com.diegomalone.movielist.model.Movie;

import java.text.DecimalFormat;

public class MovieCard extends FrameLayout {

    private TextView movieTitle, movieSynopsis, movieUserRating;
    private ImageView moviePoster;

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
        movieSynopsis = view.findViewById(R.id.textSynopsis);
        movieUserRating = view.findViewById(R.id.textUserRating);
        moviePoster = view.findViewById(R.id.imageMoviePoster);
    }

    public void setMovie(Movie movie) {
        this.movie = movie;

        DecimalFormat decimalFormat = new DecimalFormat("0.0");

        movieTitle.setText(movie.getTitle());
        movieSynopsis.setText(movie.getSynopsis());
        movieUserRating.setText(decimalFormat.format(movie.getUserRating()));

        Glide.with(this)
                .load(BuildConfig.IMAGE_URL + movie.getPoster())
                .into(moviePoster);
    }

}
