package com.diegomalone.movielist.base;

import android.app.Application;
import com.diegomalone.movielist.BuildConfig;
import timber.log.Timber;

public class MovieListApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            setupTimber();
        }
    }

    private void setupTimber() {
        Timber.plant(new Timber.DebugTree());
    }
}
