package com.soumyasethy.iamfrom.activities.new_home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.soumyasethy.iamfrom.activities.new_home.dagger.DaggerHomeComponent;
import com.soumyasethy.iamfrom.activities.new_home.dagger.HomeModule;
import com.soumyasethy.iamfrom.activities.new_home.mvp.HomePresenter;
import com.soumyasethy.iamfrom.activities.new_home.mvp.HomeView;
import com.soumyasethy.iamfrom.app.IamFromApplication;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    HomeView view;

    @Inject
    HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerHomeComponent.builder()
                .appComponent(IamFromApplication.get(this).component())
                .homeModule(new HomeModule(this))
                .build().inject(this);

        setContentView(view);
        presenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
