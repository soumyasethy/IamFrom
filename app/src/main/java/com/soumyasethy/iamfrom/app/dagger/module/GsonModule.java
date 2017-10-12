package com.soumyasethy.iamfrom.app.dagger.module;

import com.fatboyindustrial.gsonjodatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.soumyasethy.iamfrom.app.dagger.AppScope;

import dagger.Module;
import dagger.Provides;

@Module
public class GsonModule {

    @AppScope
    @Provides
    public Gson context() {
        return Converters.registerAll(new GsonBuilder())
                //.registerTypeAdapterFactory(IamFromAdapterFactory.create())
                .create();
    }
}
