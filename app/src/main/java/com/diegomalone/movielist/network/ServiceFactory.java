package com.diegomalone.movielist.network;

import androidx.annotation.NonNull;
import com.diegomalone.movielist.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFactory {

    private static MovieRestClient service;

    public static MovieRestClient getService() {
        if (service == null) {
            createService();
        }

        return service;
    }

    private static void createService() {
        OkHttpClient okHttpClient = createOkHttpClient();
        Gson gson = createGson();

        Retrofit retrofit = createRetrofit(okHttpClient, gson);

        service = retrofit.create(MovieRestClient.class);
    }

    @NonNull
    private static Retrofit createRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BuildConfig.API_URL)
                .client(okHttpClient)
                .build();
    }

    @NonNull
    private static Gson createGson() {
        return new GsonBuilder()
                .setLenient()
                .create();
    }

    @NonNull
    private static OkHttpClient createOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }
}
