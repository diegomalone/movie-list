package com.diegomalone.movielist.ui.list;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.diegomalone.movielist.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movieList = new ArrayList<>();

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieCard movieCard = new MovieCard(parent.getContext());

        movieCard.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        return new MovieViewHolder(movieCard);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        holder.movieCard.setMovie(movie);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void addMovies(List<Movie> movieList) {
        this.movieList.addAll(movieList);
        notifyDataSetChanged();
    }

    public void clearMovies() {
        this.movieList.clear();
        notifyDataSetChanged();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        MovieCard movieCard;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            this.movieCard = (MovieCard) itemView;
        }
    }
}
