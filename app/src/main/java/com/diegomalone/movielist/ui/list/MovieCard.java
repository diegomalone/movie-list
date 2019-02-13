package com.diegomalone.movielist.ui.list;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.diegomalone.movielist.BuildConfig;
import com.diegomalone.movielist.R;
import com.diegomalone.movielist.image.ImageLoader;
import com.diegomalone.movielist.injection.Injection;
import com.diegomalone.movielist.model.Movie;

public class MovieCard extends ConstraintLayout {

    private TextView movieTitle, movieSynopsis, movieUserRating;
    private ImageView moviePoster;

    private ImageLoader imageLoader = Injection.provideImageLoader();

    private Movie movie;

    public MovieCard(Context context) {
        this(context, null, 0);
    }

    public MovieCard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MovieCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = LayoutInflater.from(context).inflate(R.layout.view_movie_card, this, true);

        movieTitle = view.findViewById(R.id.textTitle);
        movieSynopsis = view.findViewById(R.id.textSynopsis);
        movieUserRating = view.findViewById(R.id.textUserRating);
        moviePoster = view.findViewById(R.id.imageMoviePoster);
    }

    public void setMovie(Movie movie) {
        this.movie = movie;

        movieTitle.setText(movie.getTitle());
        movieSynopsis.setText(movie.getSynopsis());
        movieUserRating.setText(movie.getFormattedUserRating());

        // TODO Get correct image size
        String fullImageUrl = BuildConfig.IMAGE_URL + movie.getPoster();
        imageLoader.loadImage(fullImageUrl, moviePoster);
    }

}
