package com.soumyasethy.iamfrom.app.dagger.module;

import android.content.Context;

import com.google.gson.Gson;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.soumyasethy.iamfrom.app.dagger.AppScope;
import com.soumyasethy.iamfrom.app.network.IamFromNetwork;
import com.soumyasethy.iamfrom.ext.Constants;
import com.squareup.picasso.Picasso;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

import static com.soumyasethy.iamfrom.ext.Constants.BASE_URL;

@Module
public class NetworkModule {

    @AppScope
    @Provides
    public Cache cache(Context context) {
        return new Cache(new File(context.getCacheDir(), Constants.HTTP_CACHE_DIR),
                Constants.HTTP_CACHE_SIZE);
    }

    @AppScope
    @Provides
    public OkHttpClient okHttpClient(HttpLoggingInterceptor loggingInterceptor, Cache cache) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build();
    }

    @AppScope
    @Provides
    public HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(
                message -> Timber.d(message));
        return logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
    }

    @AppScope
    @Provides
    public Retrofit retrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @AppScope
    @Provides
    public IamFromNetwork IamFromNetwork(Retrofit retrofit) {
        return retrofit.create(IamFromNetwork.class);
    }

    @AppScope
    @Provides
    public Picasso picasso(Context context, OkHttpClient okHttpClient) {
        return new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(okHttpClient))
                .build();
    }
}
