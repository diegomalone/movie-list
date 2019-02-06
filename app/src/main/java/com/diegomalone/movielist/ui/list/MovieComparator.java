package com.diegomalone.movielist.ui.list;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import com.diegomalone.movielist.model.Movie;

public class MovieComparator extends DiffUtil.ItemCallback<Movie> {

    @Override
    public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {

        if ((!(oldItem.getTitle() == null && newItem.getTitle() == null) &&
                oldItem.getTitle() != null && !oldItem.getTitle().equals(newItem.getTitle())) ||
                (oldItem.getTitle() == null && newItem.getTitle() != null)) {
            return false;
        }

        if ((!(oldItem.getSynopsis() == null && newItem.getSynopsis() == null) &&
                oldItem.getSynopsis() != null && !oldItem.getSynopsis().equals(newItem.getSynopsis())) ||
                (oldItem.getSynopsis() == null && newItem.getSynopsis() != null)) {
            return false;
        }

        if ((!(oldItem.getPoster() == null && newItem.getPoster() == null) &&
                oldItem.getPoster() != null && !oldItem.getPoster().equals(newItem.getPoster())) ||
                (oldItem.getPoster() == null && newItem.getPoster() != null)) {
            return false;
        }

        return oldItem.getUserRating() == newItem.getUserRating();
    }
}