package com.diegomalone.movielist.ui.list;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.diegomalone.movielist.model.Movie;

public class MovieAdapter extends PagedListAdapter<Movie, MovieAdapter.MovieViewHolder> {

    MovieAdapter(@NonNull DiffUtil.ItemCallback<Movie> diffCallback) {
        super(diffCallback);
    }

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
        holder.movieCard.setMovie(getItem(position));
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        MovieCard movieCard;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            this.movieCard = (MovieCard) itemView;
        }
    }
}
