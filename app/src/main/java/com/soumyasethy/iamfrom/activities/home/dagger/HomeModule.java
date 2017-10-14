package com.soumyasethy.iamfrom.activities.home.dagger;

import android.app.Activity;

import com.soumyasethy.iamfrom.activities.home.mvp.HomeModel;
import com.soumyasethy.iamfrom.activities.home.mvp.HomePresenter;
import com.soumyasethy.iamfrom.activities.home.mvp.HomeView;
import com.soumyasethy.iamfrom.app.network.IamFromNetwork;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {

    private final Activity activity;

    public HomeModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @HomeScope
    public HomeView view() {
        return new HomeView(activity);
    }

    @Provides
    @HomeScope
    public HomeModel model(IamFromNetwork iamFromNetwork) {
        return new HomeModel(activity, iamFromNetwork);
    }

    @Provides
    @HomeScope
    public HomePresenter homePresenter(HomeView homeView, HomeModel model) {
        return new HomePresenter(homeView, model);
    }

}
