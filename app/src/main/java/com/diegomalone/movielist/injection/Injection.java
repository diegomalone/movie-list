package com.diegomalone.movielist.injection;

import android.app.Application;
import com.diegomalone.movielist.image.GlideImageLoader;
import com.diegomalone.movielist.image.ImageLoader;
import com.diegomalone.movielist.network.MovieRestClient;
import com.diegomalone.movielist.network.ServiceFactory;

public class Injection {

    private static Application application;

    public static void setApplication(Application application) {
        Injection.application = application;
    }

    public static ImageLoader provideImageLoader() {
        return new GlideImageLoader(application);
    }

    public static MovieRestClient provideService() {
        return ServiceFactory.getService();
    }
}
