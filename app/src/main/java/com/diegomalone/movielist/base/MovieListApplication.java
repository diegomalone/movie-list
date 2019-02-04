package com.diegomalone.movielist.base;

import android.app.Application;
import com.diegomalone.movielist.BuildConfig;
import com.diegomalone.movielist.injection.Injection;
import timber.log.Timber;

public class MovieListApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Injection.setApplication(this);

        if (BuildConfig.DEBUG) {
            setupTimber();
        }
    }

    private void setupTimber() {
        Timber.plant(new Timber.DebugTree());
    }
}
