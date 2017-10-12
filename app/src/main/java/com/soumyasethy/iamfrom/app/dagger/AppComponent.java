package com.soumyasethy.iamfrom.app.dagger;

import android.content.Context;

import com.soumyasethy.iamfrom.app.dagger.module.AppModule;
import com.soumyasethy.iamfrom.app.dagger.module.GsonModule;
import com.soumyasethy.iamfrom.app.dagger.module.NetworkModule;
import com.soumyasethy.iamfrom.app.network.IamFromNetwork;
import com.squareup.picasso.Picasso;

import dagger.Component;
import okhttp3.OkHttpClient;

@AppScope
@Component(modules = {AppModule.class, NetworkModule.class, GsonModule.class})
public interface AppComponent {

    Context context();

    OkHttpClient okhttpClient();

    IamFromNetwork IamFromNetwork();

    Picasso picasso();

}
