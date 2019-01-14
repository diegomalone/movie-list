package com.diegomalone.movielist.base;

import android.app.Application;
import timber.log.Timber;

public class MovieListApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        setupTimber();
    }

    private void setupTimber() {
        Timber.plant(new Timber.DebugTree());
    }
}
