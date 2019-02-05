package com.diegomalone.movielist.image;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.diegomalone.movielist.R;

public class GlideImageLoader implements ImageLoader {

    private Context context;

    public GlideImageLoader(Context context) {
        this.context = context;
    }

    @Override
    public void loadImage(String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.icon_no_poster)
                .error(R.drawable.icon_no_poster);

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }
}
