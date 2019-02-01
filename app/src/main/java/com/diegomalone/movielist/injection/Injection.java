package com.diegomalone.movielist.injection;

import com.diegomalone.movielist.network.MovieRestClient;
import com.diegomalone.movielist.network.ServiceFactory;

public class Injection {

    public static MovieRestClient provideService() {
        return ServiceFactory.getService();
    }
}
